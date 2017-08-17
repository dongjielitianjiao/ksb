package com.itboy.dj.examtool.modules.communion;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.actionsheet.ActionSheet;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.JsonObject;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.RxSubscribe;
import com.itboy.dj.examtool.api.bean.CardListBean;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.modules.communion.choosevideoorphoto.MediaItem;
import com.itboy.dj.examtool.modules.communion.choosevideoorphoto.MediaOptions;
import com.itboy.dj.examtool.modules.communion.choosevideoorphoto.activities.MediaPickerActivity;
import com.itboy.dj.examtool.modules.communion.photoshow.ImageShowPickerBean;
import com.itboy.dj.examtool.modules.communion.photoshow.ImageShowPickerListener;
import com.itboy.dj.examtool.modules.communion.photoshow.ImageShowPickerView;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.widget.LToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;

//发帖
public class ChooseForumTypeActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.push_my_forum)
    TextView pushMyForum; //发帖
    @BindView(R.id.right_rt)
    RelativeLayout rightRt;
    @BindView(R.id.push_forum_rec)
    RecyclerView pushForumRec; //选择类型
    @BindView(R.id.forum_title)
    EditText forumTitle; //发帖标题
    @BindView(R.id.forum_content)
    EditText forumContent; //发帖文字Neri
    @BindView(R.id.choose)
    TextView choose;
    @BindView(R.id.test_img)
    ImageView testImg;
    @BindView(R.id.it_picker_view)
    ImageShowPickerView itPickerView;

    private List<CardListBean.DataBean> dataBeanList = new ArrayList<>();

    private final int GET_PERMISSION_REQUEST = 100; //权限申请自定义码

    private String type;

    //选择本地视频或者文件
    private static final int REQUEST_MEDIA = 100;
    private List<MediaItem> mMediaSelectedList;

    //展示视频的第一帧或图片
    List<ImageBean> listPhotoOrVideo;



    //http://download.csdn.net/download/banboofly/9813072
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_choose_forum_type;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        pushForumRec.setLayoutManager(manager);
        final String token = (String) SharedPreferencesUtils.getParam(ChooseForumTypeActivity.this, "Token", "");
        RetrofitService.cardList("forum_type", token).subscribe(new Subscriber<CardListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CardListBean cardListBean) {
                final List<CardListBean.DataBean> data = cardListBean.getData();
                final ChooseForumTypeAdapter chooseForumTypeAdapter = new ChooseForumTypeAdapter(R.layout.item_forum_push_type, data);
                pushForumRec.setAdapter(chooseForumTypeAdapter);
                type = data.get(0).getName();
                pushForumRec.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        type = data.get(position).getName();
                        chooseForumTypeAdapter.setCheckItem(position);

                    }
                });

            }
        });

        listPhotoOrVideo = new ArrayList<>();
        itPickerView.setImageLoaderInterface(new Loader());
        itPickerView.setNewData(listPhotoOrVideo);
        itPickerView.setPickerListener(new ImageShowPickerListener() {
            @Override
            public void addOnClickListener(int remainNum) {
                ActionSheet.Builder builder = ActionSheet.createBuilder(ChooseForumTypeActivity.this, getSupportFragmentManager());
                builder.setCancelButtonTitle("取消")
                        .setOtherButtonTitles("拍照或视频", "相册")
                        .setCancelableOnTouchOutside(false).setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

                    }

                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        switch (index) {
                            case 0:
                                getPermissions();
                                break;
                            case 1:
                                getFileFromLocal();

                                break;
                            default:
                                break;

                        }

                    }

                }).show();
            }

            @Override
            public void picOnClickListener(List<ImageShowPickerBean> list, int position, int remainNum) {

            }

            @Override
            public void delOnClickListener(int position, int remainNum) {

            }
        });

        itPickerView.show();

        pushMyForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ImageShowPickerBean> dataList = itPickerView.getDataList();
                String titleStr = forumTitle.getText().toString();
                if (TextUtils.isEmpty(titleStr.trim())) {
                    LToast.show(context, "请设置帖子标题");
                    return;
                }
                String contentStr = forumContent.getText().toString();
                if (TextUtils.isEmpty(contentStr.trim())) {
                    LToast.show(context, "请设置帖子内容");
                    return;
                }
                //上传文件
                uploadHeadimg(dataList, token, titleStr, contentStr);
            }
        });
    }


    //上传文件
    private void uploadHeadimg(List<ImageShowPickerBean> path, String token, String title, String content) {
        final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
        map.put("forumType", RequestBody.create(null, type));
        map.put("title", RequestBody.create(null, title));
        map.put("txt", RequestBody.create(null, content));
        MultipartBody.Part body = null;
        int size = path.size();
        if (size > 0) { //有视频和图片
            for (int i = 0; i < size; i++) {
                String imageShowPickerUrl = path.get(i).getImageShowPickerUrl();
                if (imageShowPickerUrl.endsWith(".mp4")) {
                    File file = new File(imageShowPickerUrl);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    body = MultipartBody.Part.createFormData("uploadVideos", file.getName(), requestFile);
                } else {
                    File file = new File(imageShowPickerUrl);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    body = MultipartBody.Part.createFormData("uploadImages", file.getName(), requestFile);
                }
                ret(token, map, body);
            }
        } else {  //只有文字内容
            ret(token, map, null);
        }
    }


    private void ret(String access_token, Map<String, RequestBody> map, MultipartBody.Part body) {
        RetrofitService
                .unploadForumData(map, body, access_token)
                .compose(this.<JsonObject>bindToLife())
                .subscribe(new RxSubscribe<JsonObject>(context) {
                    @Override
                    protected void _onNext(JsonObject jsonObject) {
                        JSONObject jsonObject1 = null;
                        try {
                            jsonObject1 = new JSONObject(jsonObject.toString());
                            String result = jsonObject1.optString("result");
                            if ("ok".equals(result)) {
                                LToast.show(context, "上传成功");
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        LToast.show(context, message);
                    }
                });
    }


    @Override
    protected void updateViews(boolean isRefresh) {

    }


    private void getFileFromLocal() {
        MediaOptions.Builder builder = new MediaOptions.Builder();
        MediaOptions options = builder.canSelectBothPhotoVideo()
                .canSelectMultiPhoto(false).canSelectMultiVideo(false)
                .build();
        MediaPickerActivity.open(ChooseForumTypeActivity.this, REQUEST_MEDIA, options);
    }

    /**
     * 获取权限
     */
    private void getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager
                    .PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager
                            .PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager
                            .PERMISSION_GRANTED) {
                startActivityForResult(new Intent(ChooseForumTypeActivity.this, CameraActivity.class), 100);//--------------------------
            } else {
                //不具有获取权限，需要进行权限申请
                ActivityCompat.requestPermissions(ChooseForumTypeActivity.this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA}, GET_PERMISSION_REQUEST);
            }
        } else {
            startActivityForResult(new Intent(ChooseForumTypeActivity.this, CameraActivity.class), 100);//--------------------------------
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 101) { //录制图片回调
            String path = data.getStringExtra("path");
            itPickerView.addData(new ImageBean(path));
            itPickerView.setMaxNum(3);
            itPickerView.show();
        }
        if (resultCode == 102) { //录制视频回调
            String path = data.getStringExtra("path");
            //视频回调先拿到size
            int size = itPickerView.getDataList().size();
            if (size >= 1) {
                LToast.show(ChooseForumTypeActivity.this, "最多只能上传3张图片或1个视频");
            } else {
                itPickerView.addData(new ImageBean(path));
                itPickerView.setMaxNum(1);
                itPickerView.show();

            }

        }

        if (resultCode == 103) {
            Toast.makeText(this, "请检查相机权限~", Toast.LENGTH_SHORT).show();
        }
        //本地图片或者视频选择
        if (requestCode == REQUEST_MEDIA) {  //本地图片or视频
            if (resultCode == RESULT_OK) {
                mMediaSelectedList = MediaPickerActivity
                        .getMediaItemSelected(data);
                if (mMediaSelectedList != null) {
                    for (MediaItem mediaItem : mMediaSelectedList) {
                        // addImages(mediaItem);
                        if (mediaItem.getUriCropped() == null) {
                            //只会执行这个方法
                            Uri uriOrigin = mediaItem.getUriOrigin();
                            String path = getRealFilePath(this, uriOrigin); //本地选择的地址回调路径（包含视频或者图片）

                            List<ImageShowPickerBean> dataList = itPickerView.getDataList(); //获取当前已展示的数量
                            int size = dataList.size();
                            if(size==0) {
                                if(path.endsWith(".mp4")){
                                    itPickerView.addData(new ImageBean(path));
                                    itPickerView.setMaxNum(1);
                                    itPickerView.show();
                                }else {
                                    itPickerView.addData(new ImageBean(path));
                                    itPickerView.setMaxNum(3);
                                    itPickerView.show();
                                }

                            }else {
                                if(path.endsWith(".mp4")){
                                    LToast.show(context,"最多只能上传一个小视频");
                                }else {
                                    itPickerView.addData(new ImageBean(path));
                                    itPickerView.setMaxNum(3);
                                    itPickerView.show();
                                }



                            }


                        }

                    }

                } else {

                    LToast.show(context,"未选取文件");

                }
            }
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == GET_PERMISSION_REQUEST) { //录制视频或拍摄图片的权限判断
            int size = 0;
            if (grantResults.length >= 1) {
                int writeResult = grantResults[0];
                //读写内存权限
                boolean writeGranted = writeResult == PackageManager.PERMISSION_GRANTED;//读写内存权限
                if (!writeGranted) {
                    size++;
                }
                //录音权限
                int recordPermissionResult = grantResults[1];
                boolean recordPermissionGranted = recordPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!recordPermissionGranted) {
                    size++;
                }
                //相机权限
                int cameraPermissionResult = grantResults[2];
                boolean cameraPermissionGranted = cameraPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!cameraPermissionGranted) {
                    size++;
                }

                if (size == 0) {
                    startActivityForResult(new Intent(ChooseForumTypeActivity.this, CameraActivity.class), 100);//---------------------------
                } else {
                    Toast.makeText(this, "请到设置-权限管理中开启", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    /*Uri转换为字符串*/
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    //类别条目选中状态
    class ChooseForumTypeAdapter extends BaseQuickAdapter<CardListBean.DataBean, BaseViewHolder> {
        private int checkItemPosition = 0;

        public void setCheckItem(int position) {
            checkItemPosition = position;
            notifyDataSetChanged();
        }

        public ChooseForumTypeAdapter(int layoutResId, List<CardListBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CardListBean.DataBean item) {
            helper.setText(R.id.forum_tyep_choose_text, item.getName());
            int position1 = helper.getAdapterPosition();
            if (checkItemPosition != -1) {
                if (checkItemPosition == position1) {
                    helper.setTextColor(R.id.forum_tyep_choose_text, getResources().getColor(R.color.color_white));
                    helper.setBackgroundColor(R.id.forum_tyep_choose_text, getResources().getColor(R.color.exam_choose_color));
                } else {
                    helper.setTextColor(R.id.forum_tyep_choose_text, getResources().getColor(R.color.translucent2));
                    helper.setBackgroundColor(R.id.forum_tyep_choose_text, getResources().getColor(R.color.color_white));
                }
            }
        }

    }
}
