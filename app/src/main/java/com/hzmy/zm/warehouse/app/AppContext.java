package com.hzmy.zm.warehouse.app;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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

}
