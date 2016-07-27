package com.hzmy.zm.warehouse.third_party_libs.greendao.manager;

import android.content.Context;

import com.hzmy.zm.warehouse.app.AppContext;
import com.hzmy.zm.warehouse.third_party_libs.greendao.beans.NoteDao;

/**
 * @author 追梦
 * @email 1521541979@qq.com
 * @description 对所有数据库进行统一管理
 */
public class DBManager
{
    //	private static String	TAG	= DBManager.class.getSimpleName();
    private static DBManager instance;
    private static Context appContext;
    private DaoSession daoSession;

    //各个数据表
    private NoteDao noteDao;

    public static DBManager getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new DBManager();
            if (appContext == null)
            {
                appContext = context.getApplicationContext();
            }
            instance.daoSession = AppContext.getDaoSession(appContext);


            //处理各个数据表
            instance.noteDao = instance.daoSession.getNoteDao();


        }
        return instance;
    }


    // ---------------------------------------删除----------------------------------------

    /**
     * @author 追梦
     * @email 1521541979@qq.com
     * @description 删除NoteData表
     */
    public void dropNoteDataTable()
    {
        NoteDao.dropTable(daoSession.getDatabase(), true);
    }


    // -------------------------------------创建表格----------------------------------------------

    /**
     * @author 追梦
     * @email 1521541979@qq.com
     * @description 创建所有表
     */
    public void createAllTable()
    {
        NoteDao.createTable(daoSession.getDatabase(), true);
    }



    /**
     * 说明：	       获得Note的数据管理器                                            <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/7/11 16:56                        <br>
     */
    public NoteDataManager getNoteDataManager()
    {
        NoteDataManager manager = new NoteDataManager(noteDao, appContext);
        return manager;
    }


}
