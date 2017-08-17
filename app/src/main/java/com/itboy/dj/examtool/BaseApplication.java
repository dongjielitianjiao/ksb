package com.itboy.dj.examtool;

import android.content.Context;
import android.content.IntentFilter;

import com.antfortune.freeline.FreelineCore;
import com.itboy.dj.examtool.api.RetrofitService;
import com.itboy.dj.examtool.injector.conponents.ApplicationComponent;
import com.itboy.dj.examtool.injector.conponents.DaggerApplicationComponent;
import com.itboy.dj.examtool.injector.modules.ApplicationModule;
import com.itboy.dj.examtool.local.DaoMaster;
import com.itboy.dj.examtool.local.DaoSession;
import com.itboy.dj.examtool.rxbus.RxBus;
import com.itboy.dj.examtool.utils.GreenDaoManager;
import com.itboy.dj.examtool.utils.ToastUtils;
import com.itboy.dj.examtool.utils.networkDeal.NetworkConnectChangedReceiver;
import com.mob.MobApplication;

import org.greenrobot.greendao.database.Database;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Administrator on 2017/3/22.
 */

public class BaseApplication extends MobApplication {
    private static Context sContext;
    private static ApplicationComponent sAppComponent;
    private RxBus mRxBus = new RxBus();
    public static BaseApplication app;
    private DaoSession mDaoSession;

    private NetworkConnectChangedReceiver mNetworkConnectChangedReceiver;
    //表示是否连接
    public boolean isConnected;
    //    表示是否是移动网络
    public boolean isMobile;
    //    表示是否是WiFi
    public boolean isWifi;
    //    表示WiFi开关是否打开
    public boolean isEnablaWifi;
    //    表示移动网络数据是否打开
    public boolean isEnableMobile;
    //Dao表名
    private static final String DB_NAME = "my-db";

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = getApplicationContext();
        app = this;
        initJpush();//极光推送集成
     //   setupLeakCanary(); //初始化内存泄漏检测
        _initInjector();//提供全局的某些单例
        initReceiver(); //广播初始化（用于网络检查）
        _initConfig();//网络初始化
        GreenDaoManager.getInstance(); //初始化数据库
        FreelineCore.init(this);

    }

    private void initReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filter.addAction("android.net.wifi.STATE_CHANGE");
        mNetworkConnectChangedReceiver = new NetworkConnectChangedReceiver();
        registerReceiver(mNetworkConnectChangedReceiver, filter);
    }

    /*初始化推送*/
    private void initJpush() {
        //初始化sdk
        JPushInterface.setDebugMode(true);//正式版的时候设置false，关闭调试
        JPushInterface.init(app);
        //建议添加tag标签，发送消息的之后就可以指定tag标签来发送了
/*        String token = (String) SharedPreferencesUtils.getParam(getContext(), "MovePhone", "");
        Set<String> set = new HashSet<>();
        set.add(token);//名字任意，可多添加几个
        JPushInterface.setTags(this, set, null);//设置标签*/


    }

    /*初始化数据库*/
    private void initDb() {
        /*   DaoMaster.DevOpenHelper devOpenHelper = new
                    DaoMaster.DevOpenHelper(MyApplication.getContext(), "user1-db", null);//此处为自己需要处理的表
            mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();*/

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(app, DB_NAME);
        Database database = helper.getWritableDb();
        mDaoSession = new DaoMaster(database).newSession();
    }


    public static Context getContext() {
        return sContext;
    }

    public static ApplicationComponent getAppComponent() {
        return sAppComponent;
    }

    /**
     * 初始化注射器
     */
    private void _initInjector() {
        // 这里不做注入操作，只提供一些全局单例数据
        sAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, mDaoSession, mRxBus))
                .build();
    }


    /**
     * 初始化配置
     */
    private void _initConfig() {
        RetrofitService.init();
        ToastUtils.init(app);
    }

    public static BaseApplication getInstance() {
        return app;
    }

    public boolean isConnected() {
        return isWifi || isMobile;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public boolean isMobile() {
        return isMobile;
    }

    public void setMobile(boolean mobile) {
        isMobile = mobile;
    }

    public boolean isWifi() {
        return isWifi;
    }

    public void setWifi(boolean wifi) {
        isWifi = wifi;
    }

    public boolean isEnablaWifi() {
        return isEnablaWifi;
    }

    public void setEnablaWifi(boolean enablaWifi) {
        isEnablaWifi = enablaWifi;
    }

    public boolean isEnableMobile() {
        return isEnableMobile;
    }

    public void setEnableMobile(boolean enableMobile) {
        isEnableMobile = enableMobile;
    }

}
