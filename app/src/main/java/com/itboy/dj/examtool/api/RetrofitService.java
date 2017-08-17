package com.itboy.dj.examtool.api;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonObject;
import com.itboy.dj.examtool.BaseApplication;
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
import com.itboy.dj.examtool.utils.NetUtil;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by long on 2016/8/22.
 * 整个网络通信服务的启动控制，必须先调用初始化函数才能正常使用网络通信接口
 */
public class RetrofitService {

    private static final String HEAD_LINE_NEWS = "T1348647909107";

    //设缓存有效期为1天
    static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置
    //(假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)
    static final String CACHE_CONTROL_NETWORK = "Cache-Control: public, max-age=3600";
    // 避免出现 HTTP 403 Forbidden，参考：http://stackoverflow.com/questions/13670692/403-forbidden-with-java-but-not-web-browser
    static final String AVOID_HTTP403_FORBIDDEN = "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";

    private static final String NEWS_HOST = "http://c.3g.163.com/";
    private static final String WELFARE_HOST = "http://gank.io/";
    private static Api sApiService;


    // 递增页码
    private static final int INCREASE_PAGE = 20;


    private RetrofitService() {
        throw new AssertionError();
    }

    /*

    /
     */

    /**
     * 初始化网络通信服务
     */
    public static void init() {
        //打印返回数据
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        if (TextUtils.isEmpty(message)) return;
                        String s = message.substring(0, 1);
                        //如果收到想响应是json才打印
                        if ("{".equals(s) || "[".equals(s)) {
                            if (message.length() > 3900) {
                                for (int i = 0; i < message.length(); i += 3900) {
                                    if (i + 3900 < message.length()) {
                                        Logger.e("收到响应:" + message.substring(i, i + 3900));
                                    } else {
                                        Logger.e("收到响应:" + message.substring(i, message.length()));
                                    }
                                }
                            } else {
                                Logger.e("收到响应:" + message);
                            }
                        }
                    }
                });

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(sLoggingInterceptor)   //用于打印网络请求地址
                .addInterceptor(logging) //打印返回数据
                .addInterceptor(sRewriteCacheControlInterceptor)
                //    .addNetworkInterceptor(sRewriteCacheControlInterceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build();
        sApiService = retrofit.create(Api.class);


    }

    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private static final Interceptor sRewriteCacheControlInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isNetworkAvailable(BaseApplication.getContext())) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();

            }
            Response originalResponse = chain.proceed(request);

            if (NetUtil.isNetworkAvailable(BaseApplication.getContext())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, " + CACHE_CONTROL_CACHE)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };


    /**
     * 打印返回的请求地址
     */
    private static final Interceptor sLoggingInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request request = chain.request();
            Buffer requestBuffer = new Buffer();
            if (request.body() != null) {
                request.body().writeTo(requestBuffer);
            } else {
                Logger.d("LogTAG", "request.body() == null");
            }
            //打印url信息
            Log.d("RetrofitService", request.url() + (request.body() != null ? "?" + _parseParams(request.body(), requestBuffer) : ""));
            //Logger.w(request.url() + (request.body() != null ? "?" + _parseParams(request.body(), requestBuffer) : ""));
            final Response response = chain.proceed(request);

            return response;
        }
    };

    @NonNull
    private static String _parseParams(RequestBody body, Buffer requestBuffer) throws UnsupportedEncodingException {
        if (body.contentType() != null && !body.contentType().toString().contains("multipart")) {
            return URLDecoder.decode(requestBuffer.readUtf8(), "UTF-8");
        }
        return "null";
    }


    /**
     * 自定义日志输出(打印json 数据)
     */


//数据请求

    //验证码
    public static Observable<JsonObject> getCode(String mobile, String type, String token) {
        return sApiService.getCode(mobile, type, token).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //验证码测试
    public static Observable<Basebean> getCodeTest(String mobile) {

        return sApiService.getCodeTest(mobile).compose(NetRxHelper.<Basebean>handleResult());
    }


    //注册下一步
    public static Observable<JsonObject> nextStep(String mobile, String code, String inviteCode) {
        return sApiService.nextStep(mobile, code, inviteCode)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //设置密码


    public static Observable<JsonObject> setPwd(String mobile, String pwd) {
        return sApiService.setPwd(mobile, pwd)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //登录
    public static Observable<LoginBean> login(String mobile, String password) {
        return sApiService.login(mobile, password)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    //找回密码1
    public static Observable<JsonObject> loginForgetPwd(String mobile, String code) {
        return sApiService.loginForgetPwd(mobile, code)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //找回密码2
    public static Observable<JsonObject> loginForgetSetNewPwd(String mobile, String code, String password) {
        return sApiService.loginForgetSetNewPwd(mobile, code, password)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //上传头像不需要参数
    public static Observable<JsonObject> uploadHeadImg(Map<String, RequestBody> map, String token_url) {
        return sApiService.uploadHeadImg(map, token_url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //上传头像
    public static Observable<JsonObject> uploadHeadImg1(RequestBody description, MultipartBody.Part file, String token_url) {
        return sApiService.uploadHeadImg1(description, file, token_url).
                subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<JsonObject> unploadForumData(Map<String, RequestBody> map, MultipartBody.Part file, String token_url) {
        return sApiService.unploadForumData(map, file, token_url).
                subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    //新闻
    public static Observable<NewsBean> getNews1(Map<String, RequestBody> map ,String  newSpath, String token_url) {
        return sApiService.getNews1(map, newSpath, token_url)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //新闻
    public static Observable<NewsBean> getNews( String pageNumber, String pageSize, String newSpath, String token_url) {
        return sApiService.getNews(pageNumber,pageSize, newSpath, token_url)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //选择专业
    public static Observable<ProfessionalBean> getPrfessional(String path, String access_token) {
        return sApiService.getPrfessional(path, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<KaopeiFirstBean> getKaopei(Map<String, RequestBody> map, String access_token) {
        return sApiService.getKaopei(map, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<JsonObject> testKaopei(String fisrt, String second, String three, String access_token) {
        return sApiService.testKaopei(fisrt, second, three, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<KaoPeiDetail> getKaoPerDetail(String path, String access_token) {
        return sApiService.getKaoPerDetail(path, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //立即报名
    public static Observable<JsonObject> baomingRight(Map<String, RequestBody> map, String access_token) {
        return sApiService.baomingRight(map, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //课程列表非推荐课程
    public static Observable<StudyUnCommandBean> unCommandCourse(Map<String, RequestBody> map, String access_token) {
        return sApiService.unCommandCourse(map, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //推荐课程
    public static Observable<StudyCommandBean> CommandCourse(Map<String, RequestBody> map, String access_token) {
        return sApiService.CommandCourse(map, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //所有的考试列表
    public static Observable<ExamChooseSubject> getExamList(String access_token) {
        return sApiService.getExamList(access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<ExamMsgShow> getExamDeatailAndExamScore(String examId, String access_token) {
        return sApiService.getExamDeatailAndExamScore(examId, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //课程列表(推荐)
    public static Observable<CourseListBean> getCouresList(String CourseId, String access_token) {
        return sApiService.getCouresList(CourseId, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //课程列表（非推荐）
    public static Observable<CourseListBean> getUnCommandCouresList(String userCourseId, String access_token) {
        return sApiService.getUnCommandCouresList(userCourseId, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //学习完成
    public static Observable<JsonObject> studyFinish(Map<String, RequestBody> map, String access_token) {
        return sApiService.studyFinish(map, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //学习记录过程
    public static Observable<JsonObject> studyProcess(Map<String, RequestBody> map, String access_token) {
        return sApiService.studyProcess(map, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<ExamPaper> getExamPaper(Map<String, RequestBody> map, String access_token) {
        return sApiService.getExamPaper(map, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<ExamTypebean> getExamPaper1(Map<String, RequestBody> map, String access_token) {
        return sApiService.getExamPaper1(map, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<ExamResult> postAnswer(Map<String, RequestBody> map, String access_token) {
        return sApiService.postAnswer(map, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<ErrorReviewBean> getErrorExamPaper(String examId, String examCourseId, String token) {
        return sApiService.getErrorExamPaper(examId, examCourseId, token)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    ///////
    public static Observable<NightSchoolBean> nightSchoolList(Map<String, RequestBody> map, String access_token) {
        return sApiService.nightSchoolList(map, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<NightSchoolCourseDetailBean> NightSchoolDetail(String courseId, String access_token) {
        return sApiService.NightSchoolDetail(courseId, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static Observable<NightSchoolCourseRangeList> NightSchoolCourseRangeList(String courseId, String access_token) {
        return sApiService.NightSchoolCourseRangeList(courseId, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static Observable<CardListBean> cardList(String type, String access_token) {
        return sApiService.cardList(type, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());

    }
/*
    public static Observable<ForumListBean> ForumEveryType(Map<String, RequestBody> map, String access_token) {
        return sApiService.ForumEveryType(map, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }*/


    public static Observable<ForumListBean> ForumEveryType(String pageNumber, String pageSize, String type, String access_token) {
        return sApiService.ForumEveryType(pageNumber,pageSize,type, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<MsgBean> messages(String pageNumber, String pageSize, String messageType, String access_token) {
        return sApiService.messages(pageNumber,pageSize,messageType, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<JsonObject> MsgDetail(String messageId, String access_token) {
        return sApiService.MsgDetail(messageId, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<JsonObject> deleteMsg(String messageId,String access_token) {
        return sApiService.deleteMsg(messageId, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<Noticebean> getBaokao(Map<String, RequestBody> map, String access_token) {
        return sApiService.getBaokao(map, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

/*    public static Observable<JsonObject> postAnswer1(String examid,String paperid,RequestBody body, String access_token) {
        return sApiService.postAnswer1(examid,paperid,body, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }*/


    public static Observable<JsonObject> realName(Map<String, RequestBody> map, String access_token) {
        return sApiService.realName(map, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<JsonObject> changeAdr(String adress, String token) {
        return sApiService.changeAdr(adress, token).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<JsonObject> isOnWork(boolean iswork, String token) {
        return sApiService.isOnWork(iswork, token).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<JsonObject> changeProfessional(String work, String rank, String token) {
        return sApiService.changeProfessional(work, rank, token).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<JsonObject> uploadSuggtion(String text, String contact, String token) {
        return sApiService.uploadSuggtion(text, contact, token).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<JsonObject> changeNickName(String type, String value, String token) {
        return sApiService.changeNickName(type, value, token).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<MyCollecBean> getCollect(String pageNumber, String pageSize, String type, String token) {
        return sApiService.getCollect(pageNumber,pageSize, type, token).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<JsonObject> changePhone(String mobile, String code, String pwd, String token) {
        return sApiService.changePhone(mobile, code, pwd, token).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<JsonObject> inviteCode(String inviteCode, String token) {
        return sApiService.inviteCode(inviteCode, token).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static Observable<JsonObject> upWeiquan(Map<String, RequestBody> map, String access_token) {
        return sApiService.upWeiquan(map, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<CfBean> getAllCf(String certificateStatus, String access_token) {
        return sApiService.getAllCf(certificateStatus, access_token)
                .subscribeOn(Schedulers.io()) //控制网络请求
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<JsonObject> limitPush(String registrationId, String token) {
        return sApiService.limitPush(registrationId, token).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static Observable<ServiceCenterBean> getService(String token) {
        return sApiService.getService(token).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());

    }


    public static Observable<MegagameCommentListBean> getCommentList(String dasaiId, String token) {
        return sApiService.getCommentList(dasaiId,token).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static Observable<JsonObject> pushComment(String dasaiId,String txt, String token) {
        return sApiService.pushComment(dasaiId,txt,token).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static Observable<EarnBean> getEarning(String token) {
        return sApiService.getEarning(token).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static Observable<JsonObject> myMoeny(String token) {
        return sApiService.myMoeny(token).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static Observable<JsonObject> beRealMoneyRecord(String token) {
        return sApiService.beRealMoneyRecord(token).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static class FlatMapTransformer<T> implements Observable.Transformer<Map<String, List<T>>, T> {

        private String mMapKey;

        public FlatMapTransformer<T> setMapKey(String mapKey) {
            mMapKey = mapKey;
            return this;
        }

        @Override
        public Observable<T> call(Observable<Map<String, List<T>>> mapObservable) {
            return mapObservable.flatMap(new Func1<Map<String, List<T>>, Observable<T>>() {
                @Override
                public Observable<T> call(Map<String, List<T>> stringListMap) {
                    if (TextUtils.isEmpty(mMapKey)) {
                        return Observable.error(new Throwable("Map Key is empty"));
                    }
                    return Observable.from(stringListMap.get(mMapKey));
                }
            }).subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }


}
