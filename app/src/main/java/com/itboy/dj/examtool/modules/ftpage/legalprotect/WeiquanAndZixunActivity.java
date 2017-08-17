package com.itboy.dj.examtool.modules.ftpage.legalprotect;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.baoyz.actionsheet.ActionSheet;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.JsonObject;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.RxSubscribe;
import com.itboy.dj.examtool.modules.base.BaseActivity;
import com.itboy.dj.examtool.modules.communion.ImageBean;
import com.itboy.dj.examtool.modules.communion.Loader;
import com.itboy.dj.examtool.modules.communion.photoshow.ImageShowPickerBean;
import com.itboy.dj.examtool.modules.communion.photoshow.ImageShowPickerListener;
import com.itboy.dj.examtool.modules.communion.photoshow.ImageShowPickerView;
import com.itboy.dj.examtool.rxbus.RxBus;
import com.itboy.dj.examtool.utils.SharedPreferencesUtils;
import com.itboy.dj.examtool.widget.LToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;
import cn.finalteam.rxgalleryfinal.utils.MediaScanner;
import okhttp3.RequestBody;

import static com.itboy.dj.examtool.R.id.quest_title;

public class WeiquanAndZixunActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.right_rt)
    RelativeLayout rightRt;
    @BindView(R.id.weiquan)
    RadioButton weiquan;
    @BindView(R.id.zixun)
    RadioButton zixun;
    @BindView(R.id.question_type_rec)
    RecyclerView questionTypeRec;
    @BindView(quest_title)
    EditText questTitle;
    @BindView(R.id.quest_content)
    EditText questContent;
    @BindView(R.id.it_picker_view)
    ImageShowPickerView itPickerView;
    @BindView(R.id.radio_group_wq)
    RadioGroup radioGroupWq;
    private String weiquanOrzixun = "维权";
    private List<QustionTypeBean> qustionTypeBeen = new ArrayList<>();
    private String[] typeName = {"法律", "意外保险", "媒体", "其他"};
    private int[] typeId = {0, 1, 2, 3};
    private String type; //问题类型
    List<ImageBean> listPhotoOrVideo;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_weiquan_and_zixun;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {


        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        questionTypeRec.setLayoutManager(manager);
        int i = 4;
        QustionTypeBean qustionTypeBean = null;
        for (int i1 = 0; i1 < i; i1++) {
            qustionTypeBean = new QustionTypeBean(typeName[i1], typeId[i1]);
            qustionTypeBeen.add(qustionTypeBean);
        }
        final ChooseForumTypeAdapter chooseForumTypeAdapter = new ChooseForumTypeAdapter(R.layout.item_forum_push_type, qustionTypeBeen);
        questionTypeRec.setAdapter(chooseForumTypeAdapter);
        type = qustionTypeBeen.get(0).getTypeName();
        questionTypeRec.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                type = qustionTypeBeen.get(position).getTypeName();
                chooseForumTypeAdapter.setCheckItem(position);

            }
        });


    }

    @Override
    protected void updateViews(boolean isRefresh) {
        //维权or资讯
        radioGroupWq.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.weiquan:
                        weiquanOrzixun = "维权";
                        break;
                    case R.id.zixun:
                        weiquanOrzixun = "咨询";
                        break;
                    default:
                        break;


                }
            }
        });


        //图片处理
        listPhotoOrVideo = new ArrayList<>();
        itPickerView.setImageLoaderInterface(new Loader());
        itPickerView.setmAddLabel(R.mipmap.j_ic_tupian);
        itPickerView.setNewData(listPhotoOrVideo);
        itPickerView.setPickerListener(new ImageShowPickerListener() {
            @Override
            public void addOnClickListener(int remainNum) {
                ActionSheet.Builder builder = ActionSheet.createBuilder(WeiquanAndZixunActivity.this, getSupportFragmentManager());
                builder.setCancelButtonTitle("取消")
                        .setOtherButtonTitles("相册", "拍照")
                        .setCancelableOnTouchOutside(false).setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

                    }

                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        switch (index) {
                            case 0:
                                RxGalleryFinal
                                        .with(WeiquanAndZixunActivity.this)
                                        .image()
                                        .radio()
                                        .hideCamera()
                                        .imageLoader(ImageLoaderType.GLIDE)
                                        .subscribe(new RxBusResultSubscriber<ImageRadioResultEvent>() {
                                            @Override
                                            protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                                                String s = imageRadioResultEvent.getResult().getOriginalPath();
                                                itPickerView.addData(new ImageBean(s));
                                            }
                                        })
                                        .openGallery();

                                break;
                            case 1:
                                RxGalleryFinalApi.openZKCamera(WeiquanAndZixunActivity.this);

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


        //上传维权信息
        final String token = (String) SharedPreferencesUtils.getParam(WeiquanAndZixunActivity.this, "Token", "");
        rightRt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = questTitle.getText().toString();
                String content = questContent.getText().toString();
                final Map<String, RequestBody> map = new HashMap<String, RequestBody>();
                map.put("weiquanMode", RequestBody.create(null, weiquanOrzixun));
                map.put("weiquanType", RequestBody.create(null, type));
                map.put("title", RequestBody.create(null, title));
                map.put("txt", RequestBody.create(null, content));
                RetrofitService.upWeiquan(map, token).subscribe(new RxSubscribe<JsonObject>(WeiquanAndZixunActivity.this) {
                    @Override
                    protected void _onNext(JsonObject jsonObject) {
                        try {
                            JSONObject jsonObject1 = new JSONObject(jsonObject.toString());
                            if ("ok".equals(jsonObject1.getString("result"))) {
                                LToast.show(context, "修改成功");
                                switch (type) {
                                    case "法律":
                                        RxBus.getDefault().postSticky(new TyPeBean("0"));
                                        break;
                                    case "意外保险":
                                        RxBus.getDefault().postSticky(new TyPeBean("1"));
                                        break;
                                    case "媒体":
                                        RxBus.getDefault().postSticky(new TyPeBean("2"));
                                        break;
                                    default:
                                        break;
                                }

                                finish();
                            } else {
                                JSONObject jsonObject2 = new JSONObject(jsonObject.toString());
                                JSONObject jsonObject11 = jsonObject2.optJSONObject("error");
                                String errorText = jsonObject11.optString("errorText");
                                LToast.show(context, errorText);

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
        });


    }


    //直接拍照的回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RxGalleryFinalApi.TAKE_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Log.d("WeiquanAndZixunActivity", RxGalleryFinalApi.fileImagePath.getPath());
            itPickerView.addData(new ImageBean(RxGalleryFinalApi.fileImagePath.getPath()));

            //刷新相册数据库
            RxGalleryFinalApi.openZKCameraForResult(WeiquanAndZixunActivity.this, new MediaScanner.ScanCallback() {
                @Override
                public void onScanCompleted(String[] strings) {
                    //   Log.d("PersonalActivity", strings[0]);
                }
            });
        }


    }


    class ChooseForumTypeAdapter extends BaseQuickAdapter<QustionTypeBean, BaseViewHolder> {
        private int checkItemPosition = 0;

        public void setCheckItem(int position) {
            checkItemPosition = position;
            notifyDataSetChanged();
        }

        public ChooseForumTypeAdapter(int layoutResId, List<QustionTypeBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, QustionTypeBean item) {
            helper.setText(R.id.forum_tyep_choose_text, item.getTypeName());
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
