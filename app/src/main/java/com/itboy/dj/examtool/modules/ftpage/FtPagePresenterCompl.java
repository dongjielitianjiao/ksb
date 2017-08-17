package com.itboy.dj.examtool.modules.ftpage;

import com.itboy.dj.examtool.R;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.bean.LocalImgTitle;
import com.itboy.dj.examtool.api.bean.NewsBean;
import com.itboy.dj.examtool.api.bean.Noticebean;
import com.itboy.dj.examtool.modules.ftpage.NightSchool.WorkerNightSchoolActivity;
import com.itboy.dj.examtool.modules.ftpage.apply.ApplyChooseProfessionActivity;
import com.itboy.dj.examtool.modules.ftpage.communstudy.CommunStudyActivity;
import com.itboy.dj.examtool.modules.ftpage.exam.TestSubjectActivity;
import com.itboy.dj.examtool.modules.ftpage.getcertificate.GetCertifStepOneActivity;
import com.itboy.dj.examtool.modules.ftpage.legalprotect.LegalLightProjectActivity;
import com.itboy.dj.examtool.modules.ftpage.share.ShareCodeActivity;
import com.itboy.dj.examtool.modules.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/3/31.
 */

public class FtPagePresenterCompl implements FtPagePresenter {
    private FtPageView ftPageView;

    public FtPagePresenterCompl(FtPageView ftPageView) {
        this.ftPageView = ftPageView;
    }


    //banner
    @Override
    public void getData(boolean isRefresh) {

    }

    //热门资讯
    @Override
    public void getMoreData() {


    }


    @Override
    public void bannerGetNews(Map<String, RequestBody> map, String type, String token) {
/*{"result":"ok","error":{"errorCode":-1,"errorText":null},"data":[{"contentId":157,"title":"点点滴滴","shortTitle":null,"description":null,"titleImg":"http://img.kspx.ccla.com.cn/server/upload/image/2017/04/20/1492669938780095967.png","releaseDate":"2017-04-20","views":0,"text":"<p>大啊啊啊啊啊啊啊啊啊</p>","linkUrl":null},{"contentId":154,"title":"考事宝APP上线了","shortTitle":null,"description":null,"titleImg":"http://img.kspx.ccla.com.cn/server/upload/image/2017/04/20/1492661750105018106.png","releaseDate":"2017-04-19","views":0,"text":"<p>考事宝APP上线了考事宝APP上线了考事宝APP上线了考事宝APP上线了考事宝APP上线了</p>","linkUrl":null}]}*/
        RetrofitService.getNews1(map, type, token)
                .compose(ftPageView.<NewsBean>bindToLife())
                .subscribe(new Subscriber<NewsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewsBean jsonObject) {
                        ftPageView.loadBanner(jsonObject);
                    }
                });




    }



    private List<LocalImgTitle> localImgTitles = new ArrayList<>();

    //初始化首页8个点击事件
    private void initLocal() {
        final Class<?>[] ACTIVITY = {ApplyChooseProfessionActivity.class, LoginActivity.class, TestSubjectActivity.class, GetCertifStepOneActivity.class, WorkerNightSchoolActivity.class, CommunStudyActivity.class, LegalLightProjectActivity.class, ShareCodeActivity.class};
        final String[] TITLE = {"报名", "学习", "考试", "领证", "工人夜校", "交流学习", "我要维权", "邀请工友"};
        final int[] IMG = {R.mipmap.a_ic_baomin, R.mipmap.a_ic_xuexi, R.mipmap.a_ic_kaoshi, R.mipmap.a_ic_lingzheng, R.mipmap.a_ic_gongrenyexiao, R.mipmap.a_ic_jiaoliuxuexi, R.mipmap.a_ic_woyaoweiquan, R.mipmap.a_ic_yaoqinggongyou};
        final int[] SHOWLOADING = {0, 1, 2, 3, 4, 5, 6, 7};
        LocalImgTitle localImgTitle = null;
        for (int i = 0; i < TITLE.length; i++) {
            localImgTitle = new LocalImgTitle(TITLE[i], IMG[i], ACTIVITY[i], SHOWLOADING[i]);
            localImgTitles.add(localImgTitle);
        }

    }

    @Override
    public void LocalItem() {
        initLocal();
        ftPageView.loadEightBtn(localImgTitles);
    }





    @Override
    public void hotGetNews(Map<String, RequestBody> map, String type, String token) {
/*        RetrofitService.getNews(map, type, token)
                .compose(ftPageView.<NewsBean>bindToLife())
                .flatMap(new Func1<NewsBean, Observable<NewsBean.DataBean>>() {

                    @Override
                    public Observable<NewsBean.DataBean> call(NewsBean newsBean) {
                        return Observable.from(newsBean.getData());
                    }
                })
                .toList().subscribe(new Subscriber<List<NewsBean.DataBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<NewsBean.DataBean> dataBeen) {
                ftPageView.loadHotNews(dataBeen);

            }
        });*/

        RetrofitService.getBaokao(map,token).compose(ftPageView.<Noticebean>bindToLife())
               .flatMap(new Func1<Noticebean, Observable<Noticebean.DataBean>>() {

            @Override
            public Observable<Noticebean.DataBean> call(Noticebean newsBean) {
                return Observable.from(newsBean.getData());
            }
        })
                .toList().subscribe(new Subscriber<List<Noticebean.DataBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Noticebean.DataBean> dataBeen) {
                        ftPageView.loadHotNews(dataBeen);

                    }
                });

    }


}
