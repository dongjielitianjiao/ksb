package com.itboy.dj.examtool.adapter;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.bean.ForumListBean;
import com.itboy.dj.examtool.utils.CreatBitmap;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/20.
 */

public class ForumListAdapter extends BaseQuickAdapter<ForumListBean.DataBean.RowsBean, BaseViewHolder> {

    private View inflate;
    private View inflate2;
    private View inflate3;


    public ForumListAdapter(int layoutResId, List<ForumListBean.DataBean.RowsBean> data) {
        super(layoutResId, data);
    }

    /*item_forum*/
    @Override
    protected void convert(BaseViewHolder helper, ForumListBean.DataBean.RowsBean item) {
        helper.setText(R.id.forum_author, item.getTitle());
        helper.setText(R.id.forum_content, item.getTxt());
        LinearLayout lnImgContent = helper.getView(R.id.forum_img_content);
        LayoutInflater from = LayoutInflater.from(lnImgContent.getContext());

        //
        //对视频的判断
        List<String> videoUrls = (List<String>)item.getVideoUrls();
        if (videoUrls != null) {

      /*      if (inflate2 != null) {
                inflate2.setVisibility(View.GONE);
                lnImgContent.removeView(inflate2);
            }
            if (inflate3 != null) {
                inflate3.setVisibility(View.GONE);
                lnImgContent.removeView(inflate3);
            }*/

            inflate = from.inflate(R.layout.forum_item1_imgs, null);
            lnImgContent.addView(inflate);
            final ImageView imageViewVideo = (ImageView) inflate.findViewById(R.id.forum_img); //展示视频的第一帧默认显示
            ImageView imageVideoLogo = (ImageView) inflate.findViewById(R.id.forum_video_logo_img);
            imageVideoLogo.setVisibility(View.VISIBLE);
            String videoUrl = videoUrls.get(0);
            //   Bitmap bitmapFromVideoPath = CreatBitmap.createBitmapFromVideoPath(videoUrl, 300, 300);

            if (TextUtils.isEmpty(videoUrl) || "null".equals(videoUrl)) {

            } else {
                //截取视频第一帧很卡，在子线程去操作
                Observable<Bitmap> videoFirst = Observable.just(videoUrl).subscribeOn(Schedulers.io())
                        .map(new Func1<String, Bitmap>() {
                            @Override
                            public Bitmap call(String s) {
                                return CreatBitmap.createBitmapFromVideoPath(s, 300, 300);
                            }
                        });
                videoFirst.observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Bitmap>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        imageViewVideo.setImageBitmap(bitmap);
                    }
                });


            }
        }

        //图片处理
        List<String> imageUrls = (List<String>)item.getImageUrls();
        if (imageUrls != null) {
            int size = imageUrls.size();
            switch (size) {
                case 1:
                    String imgUrl = imageUrls.get(0);
              /*      if (inflate2 != null) {
                        inflate2.setVisibility(View.GONE);
                        lnImgContent.removeView(inflate2);
                    }
                    if (inflate3 != null) {
                        inflate3.setVisibility(View.GONE);
                        lnImgContent.removeView(inflate3);
                    }*/
                    inflate = from.inflate(R.layout.forum_item1_imgs, null);
                    lnImgContent.addView(inflate);
                    ImageView imageViewUrl = (ImageView) inflate.findViewById(R.id.forum_img);
                    ImageView imageVideoLogo = (ImageView) inflate.findViewById(R.id.forum_video_logo_img);
                    imageVideoLogo.setVisibility(View.GONE);
                    Glide.with(lnImgContent.getContext()).load(imgUrl).into(imageViewUrl);
                    break;
                case 2:
                    if (inflate != null) {
                        inflate.setVisibility(View.GONE);
                        lnImgContent.removeView(inflate);
                    }

                    String imgUrl21 = imageUrls.get(0);
                    String imgUrl22 = imageUrls.get(1);
                    inflate2 = from.inflate(R.layout.forum_item2_imgs, null);
                    lnImgContent.addView(inflate2);
                    ImageView imageView21 = (ImageView) inflate2.findViewById(R.id.forum_img21); //
                    ImageView imageView22 = (ImageView) inflate2.findViewById(R.id.forum_img22); //
                    Glide.with(lnImgContent.getContext()).load(imgUrl21).into(imageView21);
                    Glide.with(lnImgContent.getContext()).load(imgUrl22).into(imageView22);
                    break;
                case 3:
                    if (inflate != null) {
                        inflate.setVisibility(View.GONE);
                        lnImgContent.removeView(inflate);
                    }
                    if (inflate2 != null) {
                        inflate2.setVisibility(View.GONE);
                        lnImgContent.removeView(inflate2);
                    }
                    String imgUrl31 = imageUrls.get(0);
                    String imgUrl32 = imageUrls.get(1);
                    String imgUrl33 = imageUrls.get(2);
                    inflate3 = from.inflate(R.layout.forum_item3_imgs, null);
                    lnImgContent.addView(inflate3);
                    ImageView imageView31 = (ImageView) inflate3.findViewById(R.id.forum_img31); //
                    ImageView imageView32 = (ImageView) inflate3.findViewById(R.id.forum_img32); //
                    ImageView imageView33 = (ImageView) inflate3.findViewById(R.id.forum_img33); //
                    Glide.with(lnImgContent.getContext()).load(imgUrl31).into(imageView31);
                    Glide.with(lnImgContent.getContext()).load(imgUrl32).into(imageView32);
                    Glide.with(lnImgContent.getContext()).load(imgUrl33).into(imageView33);
                    break;
                default:
                    break;


            }

        }


    }
}
