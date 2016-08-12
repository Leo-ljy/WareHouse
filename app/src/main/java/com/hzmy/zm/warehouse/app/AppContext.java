package com.hzmy.zm.warehouse.app;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.callback.InitResultCallback;
import com.alibaba.wxlib.util.SysUtil;
import com.hzmy.zm.warehouse.third_party_libs.a_li_yun_wang.helper.InitHelper;
import com.hzmy.zm.warehouse.third_party_libs.greendao.manager.DaoMaster;
import com.hzmy.zm.warehouse.third_party_libs.greendao.manager.DaoSession;

/**
 * 描述：                                                               <br>
 * 作者：        追梦                                                <br>
 * 邮箱：        1521541979@qq.com                        <br>
 * 公司：        杭州码友网络科技有限公司             <br>
 * 日期：        2016/7/11 16:30                               <br>
 */
public class AppContext extends Application
{

    private static AppContext				instance;


    /**
     * APP名
     */
    public static final String				APPNAME							= "GT250";

    /**
     * SharedPreferences的名称
     */
    public static final String				SHAREDPREFERENCESNAME_STRING	= APPNAME;

    public static final String TAG = "App";

    //-------------------GreenDao  相关--------------------------

    /**
     * 数据库名，表名是自动被创建的
     */
    public static final String	 DB_NAME							= APPNAME + ".db";

    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    public static  SQLiteDatabase db;
    private static Context sContext;;


    //阿里云旺
    private static final  String ALIYUNWANGAPPKEY = "23015524";


    /**
     * 说明：	       获取单例                                            <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/7/11 16:35                        <br>
     */
    public static Context  getInstance()
    {
        return instance;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
//        Fresco.initialize(this);
        initAli();
    }




    //-------------------GreenDao  相关--------------------------

    /**
     * 说明：	                                                   <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/7/11 16:56                        <br>
     */  
    public static DaoMaster getDaoMaster(Context context)
    {
        if (daoMaster == null)
        {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    public static DaoSession getDaoSession(Context context)
    {
        if (daoSession == null)
        {
            if (daoMaster == null)
            {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    public static SQLiteDatabase getSQLDatebase(Context context)
    {
        if (daoSession == null)
        {
            if (daoMaster == null)
            {
                daoMaster = getDaoMaster(context);
            }
            db = daoMaster.getDatabase();
        }
        return db;
    }


    public static String getSharedpreferencesnameString()
    {
        return SHAREDPREFERENCESNAME_STRING;
    }

    //----------------------阿里云旺相关 开始--------------------------------

    /**
     * 说明：	      初始化阿里云旺                                             <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/8/11 14:44                        <br>
     */
    private void initAli()
    {


        //todo Application.onCreate中，首先执行这部分代码，以下代码固定在此处，不要改动，这里return是为了退出Application.onCreate！！！
        if(mustRunFirstInsideApplicationOnCreate()){
            //todo 如果在":TCMSSevice"进程中，无需进行openIM和app业务的初始化，以节省内存
            return;
        }

        //初始化云旺SDK
        InitHelper.initYWSDK(this);

        //初始化反馈功能(未使用反馈功能的用户无需调用该初始化)

//        InitHelper.initFeedBack(this);

        //初始化多媒体SDK，小视频和阅后即焚功能需要使用多媒体SDK
//        AlibabaSDK.asyncInit(this, new InitResultCallback() {
//            @Override
//            public void onSuccess() {
//                Log.e(TAG, "-----initTaeSDK----onSuccess()-------" );
//                MediaService mediaService = AlibabaSDK.getService(MediaService.class);
//                mediaService.enableHttpDNS(); //果用户为了避免域名劫持，可以启用HttpDNS
//                mediaService.enableLog(); //在调试时，可以打印日志。正式上线前可以关闭
//            }
//
//            @Override
//            public void onFailure(int code, String msg) {
//                Log.e(TAG, "-------onFailure----msg:" + msg + "  code:" + code);
//            }
//        });

    }

    private boolean mustRunFirstInsideApplicationOnCreate() {
        //必须的初始化
        SysUtil.setApplication(this);
        sContext = getApplicationContext();
        return SysUtil.isTCMSServiceProcess(sContext);
    }

    public static Context getContext(){
        return sContext;
    }

    public static String getALIYUNWANGAPPKEY()
    {
        return ALIYUNWANGAPPKEY;
    }

    //----------------------阿里云旺相关 结束--------------------------------
}
