package com.itboy.dj.examtool.adapter;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
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
 * Created by Administrator on 2017/7/26.
 */

public class RedoForumAdapter extends BaseMultiItemQuickAdapter<ForumListBean.DataBean.RowsBean, BaseViewHolder> {

    public RedoForumAdapter(List<ForumListBean.DataBean.RowsBean> data) {
        super(data);
        addItemType(ForumListBean.DataBean.RowsBean.TYPE_1, R.layout.item_collect_no_data);
        addItemType(ForumListBean.DataBean.RowsBean.TYPE_2, R.layout.item_collect_data_onevidio);
        addItemType(ForumListBean.DataBean.RowsBean.TYPE_3, R.layout.item_collect_data_oneimg);
        addItemType(ForumListBean.DataBean.RowsBean.TYPE_4, R.layout.item_collect_data_twoimg);
        addItemType(ForumListBean.DataBean.RowsBean.TYPE_5, R.layout.item_collect_data_threeimg);

    }

    @Override
    protected void convert(BaseViewHolder helper, ForumListBean.DataBean.RowsBean item) {
        switch (helper.getItemViewType()) {
            case ForumListBean.DataBean.RowsBean.TYPE_1:
              //  helper.setText(R.id.collect_author, item.getUserId());
                helper.setText(R.id.collect_title, item.getTitle());
                helper.setText(R.id.collect_content, item.getTxt());
                helper.setText(R.id.time, item.getCreateDate());

                break;
            case ForumListBean.DataBean.RowsBean.TYPE_2:
               // helper.setText(R.id.collect_author, item.getUserId());
                helper.setText(R.id.collect_title, item.getTitle());
                helper.setText(R.id.collect_content, item.getTxt());
                helper.setText(R.id.time, item.getCreateDate());

                final ImageView imageView = helper.getView(R.id.forum_img);
                List<String> videoUrls = (List<String>) item.getVideoUrls();
                String videoUrl = videoUrls.get(0);
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
                        imageView.setImageBitmap(bitmap);
                    }
                });


                break;
            case ForumListBean.DataBean.RowsBean.TYPE_3:
             //   helper.setText(R.id.collect_author, item.getUserId());
                helper.setText(R.id.collect_title, item.getTitle());
                helper.setText(R.id.collect_content, item.getTxt());
                helper.setText(R.id.time, item.getCreateDate());

                final ImageView view = helper.getView(R.id.forum_img_one);
                List<String> itemImageUrls =(List<String>) item.getImageUrls();
                final String s = itemImageUrls.get(0);
            Glide.with(view.getContext()).load(s).into(view);
          /*      Glide.with(view.getContext()).load(s).asBitmap().into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        int width = resource.getWidth();
                        int high=resource.getHeight();
                        int normal=width>high?high:width;
                        Log.d("RedoForumAdapter", width + "--" + high + "--" + normal);
                        ViewGroup.LayoutParams para = view.getLayoutParams();
                        para.width = normal;
                        para.height = normal;
                        view.setLayoutParams(para);
                        Glide.with(view.getContext())
                                .load(s)
                                .dontAnimate()
                                .centerCrop()
                                .into(view);
                    }
                });*/
                break;
            case ForumListBean.DataBean.RowsBean.TYPE_4:
               // helper.setText(R.id.collect_author, item.getUserId());
                helper.setText(R.id.collect_title, item.getTitle());
                helper.setText(R.id.collect_content, item.getTxt());
                helper.setText(R.id.time, item.getCreateDate());

                List<String> itemImageUrls1 = (List<String>) item.getImageUrls();
                String imgUrl21 = itemImageUrls1.get(0);
                String imgUrl22 = itemImageUrls1.get(1);
                final ImageView imageView21 = helper.getView(R.id.forum_img21);
                Glide.with(imageView21.getContext()).load(imgUrl21).into(imageView21);
                final ImageView imageView22 = helper.getView(R.id.forum_img22);
                Glide.with(imageView22.getContext()).load(imgUrl22).into(imageView22);
                break;
            case ForumListBean.DataBean.RowsBean.TYPE_5:
            //    helper.setText(R.id.collect_author, item.getUserId());
                helper.setText(R.id.collect_title, item.getTitle());
                helper.setText(R.id.collect_content, item.getTxt());
                helper.setText(R.id.time, item.getCreateDate());
                List<String> itemImageUrls2 =(List<String>) item.getImageUrls();
                String imgUrl31 = itemImageUrls2.get(0);
                String imgUrl32 = itemImageUrls2.get(1);
                String imgUrl33 = itemImageUrls2.get(2);
                final ImageView imageView31 = helper.getView(R.id.forum_img31);
                final ImageView imageView32 = helper.getView(R.id.forum_img32);
                final ImageView imageView33 = helper.getView(R.id.forum_img33);
                Glide.with(imageView31.getContext()).load(imgUrl31).into(imageView31);
                Glide.with(imageView32.getContext()).load(imgUrl32).into(imageView32);
                Glide.with(imageView33.getContext()).load(imgUrl33).into(imageView33);

                break;


        }
    }
}
