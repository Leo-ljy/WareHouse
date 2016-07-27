package com.hzmy.zm.warehouse.third_party_libs.greendao.manager;

/**
 * 描述：                                                               <br>
 * 作者：        追梦                                                <br>
 * 邮箱：        1521541979@qq.com                        <br>
 * 公司：        杭州码友网络科技有限公司             <br>
 * 日期：        2016/7/11 16:48                               <br>
 */

import android.content.Context;

import com.hzmy.zm.warehouse.app.AppContext;
import com.hzmy.zm.warehouse.third_party_libs.greendao.beans.Note;
import com.hzmy.zm.warehouse.third_party_libs.greendao.beans.NoteDao;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * @author 追梦
 * @email 1521541979@qq.com
 * @description 管理操作页面数据
 */
public class NoteDataManager implements IDBManager<Note>
{
    private NoteDao dataDao;
    private Context appContext;

    public NoteDataManager(NoteDao t, Context context)
    {
        dataDao = t;
        this.appContext = context;
    }

    public NoteDataManager(NoteDao t)
    {
        dataDao = t;
    }

    @Override
    public long saveOneData(Note bean)
    {
        return dataDao.insertOrReplace(bean);
    }

    @Override
    public void saveDataLists(final List<Note> list)
    {
        if (list == null || list.isEmpty()) { return; }
        dataDao.getSession().runInTx(new Runnable()
        {

            @Override
            public void run()
            {
                for (int i = 0; i < list.size(); i++)
                {
                    Note m = list.get(i);
                    dataDao.insertOrReplace(m);
                }
            }
        });
    }

    @Override
    public void saveDataArr(final Note[] arr)
    {
        if (arr == null || arr.length == 0) { return; }
        dataDao.getSession().runInTx(new Runnable()
        {

            @Override
            public void run()
            {
                for (int i = 0; i < arr.length; i++)
                {
                    Note m = arr[i];
                    dataDao.insertOrReplace(m);
                }
            }
        });
    }

    @Override
    public void deleteOneData(Note bean)
    {
        dataDao.delete(bean);
    }

    @Override
    public void deleteDataById(long id)
    {
        dataDao.deleteByKey(id);
    }

    /**
     * @param list
     * @author 追梦
     * @email 1521541979@qq.com
     * @description 删除文件
     */
    public void deleteDataList(List<Note> list)
    {
        for (int i = 0; i < list.size(); i++)
        {
            Note m = list.get(i);
            dataDao.deleteByKey(m.getId());
        }
    }


    @Override
    public void deleteAllData()
    {
        dataDao.deleteAll();
    }

    @Override
    public List<Note> loadAllData()
    {
        return dataDao.loadAll();
    }

    @Override
    public Note loadOneDataById(long id)
    {
        return dataDao.load(id);
    }


    /**
     * @param uid
     * @return
     * @author 追梦
     * @email 1521541979@qq.com
     * @description 根据tagName来查询符合条件的 （比较难的查询可以使用QueryBuilder来查询）
     */
    public List<Note> loadListByIds(long uid, String text)
    {
        if (appContext != null){
            AppContext.getDaoSession(appContext).clear();
            QueryBuilder<Note> qb = dataDao.queryBuilder();
            qb.where(NoteDao.Properties.Id.eq(uid), NoteDao.Properties.Text.eq(text));
            return qb.list();// 返回多条数据
        } else {
            return  null;
        }

    }


}
