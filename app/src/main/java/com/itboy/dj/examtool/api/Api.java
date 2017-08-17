package com.itboy.dj.examtool.api;

import com.google.gson.JsonObject;
import com.itboy.dj.examtool.api.bean.Basebean;
import com.itboy.dj.examtool.api.bean.CardListBean;
import com.itboy.dj.examtool.api.bean.CfBean;
import com.itboy.dj.examtool.api.bean.CourseListBean;
import com.itboy.dj.examtool.api.bean.EarnBean;
import com.itboy.dj.examtool.api.bean.ExamChooseSubject;
import com.itboy.dj.examtool.api.bean.ExamMsgShow;
import com.itboy.dj.examtool.api.bean.ExamPaper;
import com.itboy.dj.examtool.api.bean.ForumListBean;
import com.itboy.dj.examtool.api.bean.KaoPeiDetail;
import com.itboy.dj.examtool.api.bean.KaopeiFirstBean;
import com.itboy.dj.examtool.api.bean.LoginBean;
import com.itboy.dj.examtool.api.bean.MegagameCommentListBean;
import com.itboy.dj.examtool.api.bean.MsgBean;
import com.itboy.dj.examtool.api.bean.MyCollecBean;
import com.itboy.dj.examtool.api.bean.NewsBean;
import com.itboy.dj.examtool.api.bean.NightSchoolBean;
import com.itboy.dj.examtool.api.bean.NightSchoolCourseDetailBean;
import com.itboy.dj.examtool.api.bean.NightSchoolCourseRangeList;
import com.itboy.dj.examtool.api.bean.Noticebean;
import com.itboy.dj.examtool.api.bean.ProfessionalBean;
import com.itboy.dj.examtool.api.bean.ServiceCenterBean;
import com.itboy.dj.examtool.api.bean.StudyCommandBean;
import com.itboy.dj.examtool.api.bean.StudyUnCommandBean;
import com.itboy.dj.examtool.modules.ftpage.exam.ErrorReviewBean;
import com.itboy.dj.examtool.modules.ftpage.exam.ExamResult;
import com.itboy.dj.examtool.modules.ftpage.exam.ExamTypebean;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/29.
 */

public interface Api {

    //验证码
    @FormUrlEncoded
    @POST("sms/sendCode")
    Observable<JsonObject> getCode(@Field("mobile") String mobile, @Field("type") String type, @Query("access_token") String access_token);

    //验证码测试
    @FormUrlEncoded
    @POST("login/register-sendSms")
    Observable<BaseModel<Basebean>> getCodeTest(@Field("mobile") String mobile);

    //注册点击下一步
    @FormUrlEncoded
    @POST("login/register")
    Observable<JsonObject> nextStep(@Field("mobile") String mobile, @Field("code") String code, @Field("inviteCode") String inviteCode);

    //注册点击下一步
    @FormUrlEncoded
    @POST("login/register-pwd")
    Observable<JsonObject> setPwd(@Field("mobile") String mobile, @Field("password") String pwd);

    //登录
    @FormUrlEncoded
    @POST("login/login")
    Observable<LoginBean> login(@Field("mobile") String mobile, @Field("password") String password);


    // 忘记密吗获取验证码
    //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/sms/checkCode/login-forget
    @FormUrlEncoded
    @POST("sms/checkCode/login-forget")
    Observable<JsonObject> loginForgetPwd(@Field("mobile") String mobile, @Field("code") String code);

    //重新设置密码
    //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/login/forget
    @FormUrlEncoded
    @POST("login/forget")
    Observable<JsonObject> loginForgetSetNewPwd(@Field("mobile") String mobile, @Field("code") String code, @Field("password") String password);

    //上传头像
    @Multipart
    @POST("user/info/upload-img")
    Observable<JsonObject> uploadHeadImg(@PartMap Map<String, RequestBody> map, @Field("access_token") String access_token);

    //上传头像
    @Multipart
    @POST("user/info/upload-img")
    Observable<JsonObject> uploadHeadImg1(@Part("description") RequestBody description, @Part MultipartBody.Part file, @Query("access_token") String access_token);

    //论坛上传数据（包含图片和文字）
    @Multipart
    @POST("wq/forum/create")
    Observable<JsonObject> unploadForumData(@PartMap Map<String, RequestBody> map, @Part MultipartBody.Part file, @Query("access_token") String access_token);


    //banner（新闻）
    @Multipart
    @POST("content/{channelPath}")
    Observable<NewsBean> getNews1(@PartMap Map<String, RequestBody> map, @Path("channelPath") String channelPath, @Query("access_token") String access_token);


    //新闻
    @FormUrlEncoded
    @POST("content/{channelPath}")
    Observable<NewsBean> getNews(@Field("pageNumber") String pageNumber, @Field("pageSize") String pageSize, @Path("channelPath") String channelPath, @Query("access_token") String access_token);
    //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/notice/exam
    @Multipart
    @POST("notice/exam")
    Observable<Noticebean> getBaokao(@PartMap Map<String, RequestBody> map, @Query("access_token") String access_token);


    //报名选择职业工种  http://demo.kspx.ccla.com.cn/kspx-cgi/api/data/work-all
    @POST("data/work-all/{type}")
    Observable<ProfessionalBean> getPrfessional(@Path("type") String path, @Query("access_token") String access_token);

    //考试模块
    //考培信息
    // http://demo.kspx.ccla.com.cn/kspx-cgi/api/exam/list-register
    @Multipart
    @POST("exam/list-register")
    Observable<KaopeiFirstBean> getKaopei(@PartMap Map<String, RequestBody> map, @Query("access_token") String access_token);

    //考培测试
    @FormUrlEncoded
    @POST("exam/list-register")
    Observable<JsonObject> testKaopei(@Field("professionId") String professionId, @Field("workId") String workId, @Field("rankId") String rankId, @Query("access_token") String access_token);

    //考培二级
    // http://demo.kspx.ccla.com.cn/kspx-cgi/api/exam/{examId}
    @POST("exam/{examId}")
    Observable<KaoPeiDetail> getKaoPerDetail(@Path("examId") String examId, @Query("access_token") String access_token);

    //立即报名
    //  Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/exam/register
    @Multipart
    @POST("exam/register")
    Observable<JsonObject> baomingRight(@PartMap Map<String, RequestBody> map, @Query("access_token") String access_token);


    /*http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/course/list
    课程列表(非推荐课程)*/
    @Multipart
    @POST("user/course/list")
    Observable<StudyUnCommandBean> unCommandCourse(@PartMap Map<String, RequestBody> map, @Query("access_token") String access_token);


    //http://demo.kspx.ccla.com.cn/kspx-cgi/api/course/exam/list-recommended
    //推荐课程
    @Multipart
    @POST("course/exam/list-recommended")
    Observable<StudyCommandBean> CommandCourse(@PartMap Map<String, RequestBody> map, @Query("access_token") String access_token);

    //ttp://demo.kspx.ccla.com.cn/kspx-cgi/api/user/exam/list-online
    //所有可选的考试列表
    @POST("user/exam/list-online")
    Observable<ExamChooseSubject> getExamList(@Query("access_token") String access_token);

    //考试详情及考试成绩
    //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/exam/{examId}
    @POST("user/exam/{examId}")
    Observable<ExamMsgShow> getExamDeatailAndExamScore(@Path("examId") String examId, @Query("access_token") String access_token);


    //课程列表(学习详情的课程列表)
    //

    //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/course/exam/{courseId} //推荐
    @POST("course/exam/{courseId}")
    Observable<CourseListBean> getCouresList(@Path("courseId") String userCourseId, @Query("access_token") String access_token);

    //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/course/{userCourseId} 非推荐 user/course/{userCourseId}
    @POST(" user/course/{userCourseId}")
    Observable<CourseListBean> getUnCommandCouresList(@Path("userCourseId") String userCourseId, @Query("access_token") String access_token);



        //视频播放过程中，暂停，播放完成，都要请求
    //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/course/study
    @Multipart
    @POST("user/course/study")
    Observable<JsonObject> studyProcess(@PartMap Map<String, RequestBody> map, @Query("access_token") String access_token);

    //学习完成
    //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/course/study-finish
    @Multipart
    @POST("user/course/study-finish")
    Observable<JsonObject> studyFinish(@PartMap Map<String, RequestBody> map, @Query("access_token") String access_token);

    //获取试卷
    //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/exam/exam-pager
    @Multipart
    @POST("user/exam/exam-pager")
    Observable<ExamPaper> getExamPaper(@PartMap Map<String, RequestBody> map, @Query("access_token") String access_token);

    //新的获得试卷（后来增加了多选和填空,重新写一遍逻辑）
    @Multipart
    @POST("user/exam/exam-pager")
    Observable<ExamTypebean> getExamPaper1(@PartMap Map<String, RequestBody> map, @Query("access_token") String access_token);
    //上传答案
    // Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/exam/exam-pager/submit
// @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @Multipart
    @POST("user/exam/exam-pager/submit")
    Observable<ExamResult> postAnswer(@PartMap Map<String, RequestBody> map, @Query("access_token") String access_token);


/*    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @FormUrlEncoded
    @POST("user/exam/exam-pager/submit")
    Observable<JsonObject> postAnswer1(@Field("examId") String examId, @Field("paperId") String paperId,RequestBody body, @Query("access_token") String access_token);*/

   //拿到错题
    //  Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/exam/exam-pager/reply-record
  @FormUrlEncoded
  @POST("user/exam/exam-pager/reply-record")
  Observable<ErrorReviewBean> getErrorExamPaper(@Field("examId") String examId, @Field("examCourseId") String examCourseId, @Query("access_token") String access_token);

    //夜校列表
    @Multipart
    @POST("wq/yexiao/list")
    Observable<NightSchoolBean> nightSchoolList(@PartMap Map<String, RequestBody> map, @Query("access_token") String access_token);

    //夜校详情
    @POST("wq/yexiao/{courseId}")
    Observable<NightSchoolCourseDetailBean> NightSchoolDetail(@Path("courseId") String courseId, @Query("access_token") String access_token);

    //夜校的课程安排
    @POST("wq/yexiao/{courseId}-catalogs")
    Observable<NightSchoolCourseRangeList> NightSchoolCourseRangeList(@Path("courseId") String courseId, @Query("access_token") String access_token);

    //http://demo.kspx.ccla.com.cn/kspx-cgi/api/data/{type}-dictionary
    //论坛的标题栏
    @POST("data/{type}-dictionary")
    Observable<CardListBean> cardList(@Path("type") String type, @Query("access_token") String access_token);

    //标题栏对应的每个type,需要传的字段(论坛列表)
  /*  @Multipart
    @POST("wq/forum/list")
    Observable<ForumListBean> ForumEveryType(@PartMap Map<String, RequestBody> map, @Query("access_token") String access_token);*/
    @FormUrlEncoded
    @POST("wq/forum/list")
    Observable<ForumListBean> ForumEveryType(@Field("pageNumber") String pageNumber, @Field("pageSize") String pageSize, @Field("type") String type,  @Query("access_token") String access_token);

    /*个人中心模块*/
    //我的消息
    //http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/message/{messageType}-list
    @FormUrlEncoded
    @POST("user/message/{messageType}-list")
    Observable<MsgBean> messages(@Field("pageNumber") String pageNumber, @Field("pageSize") String pageSize, @Path("messageType") String messageType, @Query("access_token") String access_token);

    //消息详情
    //  Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/message/{messageId}
    @POST("user/message/{messageId}")
    Observable<JsonObject> MsgDetail(@Path("messageId")String messageId,  @Query("access_token") String access_token);
   //删除消息
    // Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/message/{messageId}-store
   @POST("user/message/{messageId}-store")
   Observable<JsonObject> deleteMsg(@Path("messageId")String messageId,  @Query("access_token") String access_token);
    //实名认证
    // Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/real-auth
    @Multipart
    @POST("user/real-auth")
    Observable<JsonObject> realName(@PartMap Map<String, RequestBody> map, @Query("access_token") String access_token);

    //修改工作地址
    //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/info/update-jobAddress
    @FormUrlEncoded
    @POST("user/info/update-jobAddress")
    Observable<JsonObject> changeAdr(@Field("address") String address, @Query("access_token") String access_token);


    //工作状态
    //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/info/update-jobStatus status
    @FormUrlEncoded
    @POST("user/info/update-jobStatus")
    Observable<JsonObject> isOnWork(@Field("status") boolean address, @Query("access_token") String access_token);

    //修改职业
    //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/info/update-work
    @FormUrlEncoded
    @POST("user/info/update-work")
    Observable<JsonObject> changeProfessional(@Field("work") String work, @Field("rank") String rank, @Query("access_token") String access_token);

    //提意见
    //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/feedback/add
    @FormUrlEncoded
    @POST("user/feedback/add")
    Observable<JsonObject> uploadSuggtion(@Field("text") String text, @Field("contact") String contact,@Query("access_token") String access_token);

    //修改昵称
    // Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/info/update-{type}
 /*realname//姓名, idcard//身份证号,  sex//性别（1男0女） ,  phone//电话号码, email//电子邮箱, qq //QQ号码, nikename//昵称*/
    @FormUrlEncoded
    @POST("user/info/update-{type}")
    Observable<JsonObject> changeNickName(@Path("type") String type, @Field("value") String value, @Query("access_token") String access_token);

    //修改绑定的手机号
    //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/info/change-mobile
    @FormUrlEncoded
    @POST("user/info/change-mobile")
    Observable<JsonObject> changePhone(@Field("mobile") String mobile, @Field("code") String code,@Field("password") String password, @Query("access_token") String access_token);


    //我的收藏
    //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/store/{type}-list
    @FormUrlEncoded
    @POST("user/store/{type}-list")
    Observable<MyCollecBean> getCollect(@Field("pageNumber") String pageNumber, @Field("pageSize") String pageSize, @Path("type") String type, @Query("access_token") String access_token);

    //邀请码
    //  Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/invite/bindCode
    @FormUrlEncoded
    @POST("user/invite/bindCode")
    Observable<JsonObject> inviteCode(@Field("checkCode") String checkCode, @Query("access_token") String access_token);


    //上传维权信息
    //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/wq/weiquan/create
    @Multipart
    @POST("wq/weiquan/create")
    Observable<JsonObject> upWeiquan(@PartMap Map<String, RequestBody> map, @Query("access_token") String access_token);


    //全部证书
    //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/certificate/list-all
    //正确地址：Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/order/certificate/list
    @FormUrlEncoded
    @POST("user/order/certificate/list") //CfBean
    Observable<CfBean> getAllCf(@Field("certificateStatus") String certificateStatus, @Query("access_token") String access_token);

    //设置推送别名
    //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/bind/jpush
    @FormUrlEncoded
    @POST("user/bind/jpush")
    Observable<JsonObject> limitPush(@Field("registrationId") String registrationId,@Query("access_token") String access_token);

//客服中心
    //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/content/kfzx
    @POST("content/kfzx")
    Observable<ServiceCenterBean> getService(@Query("access_token") String access_token);

    //大赛列表
  //  Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/wq/dasai/{dasaiId}-comments

    @POST("wq/dasai/{dasaiId}-comments")
    Observable<MegagameCommentListBean> getCommentList(@Path("dasaiId") String dasaiId, @Query("access_token") String access_token);

    //发布评论
    //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/wq/dasai/{dasaiId}-comment
    @FormUrlEncoded
    @POST("wq/dasai/{dasaiId}-comment")
    Observable<JsonObject> pushComment(@Path("dasaiId") String dasaiId,@Field("txt") String txt, @Query("access_token") String access_token);
    //s
//Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/wallet/incomeList
    //收益列表
    @POST("user/wallet/incomeList")
    Observable<EarnBean> getEarning(@Query("access_token") String access_token);
    //我的金额
    //Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/wallet/money
    @POST("user/wallet/money")
    Observable<JsonObject> myMoeny(@Query("access_token") String access_token);

    //提现记录Url：http://demo.kspx.ccla.com.cn/kspx-cgi/api/user/wallet/tixian-list
    @POST("user/wallet/tixian-list")
    Observable<JsonObject> beRealMoneyRecord(@Query("access_token") String access_token);
}
