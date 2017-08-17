package com.itboy.dj.examtool.modules.study;

import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.api.bean.StudyBeanTest;
import com.itboy.dj.examtool.api.bean.StudyCommandBean;
import com.itboy.dj.examtool.api.bean.StudyUnCommandBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/4/7.
 */

public class StudyPresenterCompl implements StudyPresenter {
    private StudyDataView studyDataView;
    private List<StudyBeanTest> studyBeanTests = new ArrayList<>();

    public StudyPresenterCompl(StudyDataView studyDataView) {
        this.studyDataView = studyDataView;
    }

    //非推荐部分
    @Override
    public void videoListUnCommand(Map<String, RequestBody> map, String token) {
        RetrofitService
                .unCommandCourse(map, token)
                .compose(studyDataView.<StudyUnCommandBean>bindToLife())
                .subscribe(new Subscriber<StudyUnCommandBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(StudyUnCommandBean studyUnCommandBean) {
                        // Log.d("StudyPresenterCompl", "studyUnCommandBean.getData().getRows().size():" + studyUnCommandBean.getData().getRows().size());

                        List<StudyUnCommandBean.DataBean.RowsBean> rows = studyUnCommandBean.getData().getRows();
                        studyDataView.loadVideoListUnCom(rows);

                    }
                });

    }

    //推荐部分
    @Override
    public void videoListCommand(Map<String, RequestBody> map, String token) {
        //initvideoListSourse();
        //  studyDataView.loadVideoListCom(studyBeanTests);
/*{"result":"ok","error":{"errorCode":0,"errorText":"success"},"data":[{"userCourseId":null,"userId":7374,"courseId":48,"examId":54,"courseStudyStatus":null,"courseName":"钢筋工高级工考前培训课程","examName":"2016年度钢筋工高级工考试","coursePicture":null,"viewCount":2,"studyChapterName":null,"doneChapterRate":null},{"userCourseId":null,"userId":7374,"courseId":47,"examId":64,"courseStudyStatus":null,"courseName":"古建油漆工高级工考前培训课程","examName":"2016年度古建油漆工高级工考试(有培训)","coursePicture":"http://img.kspx.ccla.com.cn/server/upload/image/2016/06/14/1465885067812008796.jpg","viewCount":2,"studyChapterName":null,"doneChapterRate":null}]}*/
        RetrofitService
                .CommandCourse(map, token)
                .compose(studyDataView.<StudyCommandBean>bindToLife()).subscribe(new Subscriber<StudyCommandBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(StudyCommandBean jsonObject) {
                List<StudyCommandBean.DataBean> data = jsonObject.getData();
                studyDataView.loadVideoListCom(data);
            }
        });


    }



    //初始化本地测试
    private void initvideoListSourse() {
        final String[] imgUrls = {"http://pic15.nipic.com/20110731/8022110_162804602317_2.jpg", "http://www.taopic.com/uploads/allimg/121009/240425-12100914164636.jpg"
                , "http://www.taopic.com/uploads/allimg/121009/240425-12100914164636.jpg", "http://img02.tooopen.com/downs//images/2010/9/16/sy_2010091620583620405.jpg"
                , "http://pic38.nipic.com/20140228/5571398_215900721128_2.jpg", "http://5.26923.com/download/pic/000/288/ed06bdc51d6a83df5919dcea5d261707.jpg",
                "http://t1.niutuku.com/960/10/10-192927.jpg"};
        final int[] momentProgress = {30, 20, 50, 40, 60, 70, 20};
        final String[] watchPeoples = {"1000人", "500人", "200人", "700人", "500人", "200人", "700人"};
        final String[] titles = {"正在学习1", "正在学习2", "正在学习1", "正在学习2", "正在学习1", "正在学习2", "正在学习1"};
        final String[] video_titles = {"木工高级课程", "电工高级课程", "水工高级课程", "铁工高级课程", "金工高级课程", "小工高级课程", "刚工高级课程"};
        final String[] play_urls = {"http://baobab.wdjcdn.com/14564977406580.mp4", "http://baobab.wdjcdn.com/14564977406580.mp4"
                , "http://baobab.wdjcdn.com/14564977406580.mp4", "http://baobab.wdjcdn.com/14564977406580.mp4"
                , "http://baobab.wdjcdn.com/14564977406580.mp4", "http://baobab.wdjcdn.com/14564977406580.mp4"
                , "http://baobab.wdjcdn.com/14564977406580.mp4"};
        StudyBeanTest studyBeanTest = null;
        for (int i = 0; i < 7; i++) {
            studyBeanTest = new StudyBeanTest();
            studyBeanTest.setImgUrl(imgUrls[i]);
            studyBeanTest.setMomentProgress(momentProgress[i]);
            studyBeanTest.setWatchPeople(watchPeoples[i]);
            studyBeanTest.setTitle(titles[i]);
            studyBeanTest.setVideo_title(video_titles[i]);
            studyBeanTest.setPlayUrl(play_urls[i]);
            studyBeanTests.add(studyBeanTest);
        }


    }

    @Override
    public void getData(boolean isRefresh) {

    }

    @Override
    public void getMoreData() {

    }


}
