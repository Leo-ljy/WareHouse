package com.hzmy.zm.warehouse.third_party_libs.greendao.manager;

import java.util.List;

/**
 * 描述：                                                               <br>
 * 作者：        追梦                                                <br>
 * 邮箱：        1521541979@qq.com                        <br>
 * 公司：        杭州码友网络科技有限公司             <br>
 * 日期：        2016/7/11 16:41                               <br>
 */
public interface IDBManager<T>
{
    public long saveOneData(T bean);

    public void saveDataLists(final List<T> list);

    public void saveDataArr(final T[] arr);

    public void deleteOneData(T bean);

    public void deleteDataById(long id);

    public void deleteAllData();

    public List<T> loadAllData();

    public T loadOneDataById(long id);


}

