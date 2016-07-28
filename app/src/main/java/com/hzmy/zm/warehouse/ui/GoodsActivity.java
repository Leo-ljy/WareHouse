package com.hzmy.zm.warehouse.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.hzmy.zm.warehouse.R;
import com.hzmy.zm.warehouse.utils.ToastUtils;
import com.hzmy.zm.warehouse.widgets.toolbar.ToolBarActivity;

import java.util.zip.Inflater;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述：                                                               <br>
 * 作者：        追梦                                                <br>
 * 邮箱：        1521541979@qq.com                        <br>
 * 公司：        杭州码友网络科技有限公司             <br>
 * 日期：        2016/7/27 16:51                               <br>
 */
public class GoodsActivity extends ToolBarActivity implements Toolbar.OnMenuItemClickListener
{

    @Bind(R.id.lv_goods)
    ListView lvGoods;

    private Context mContext ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        ButterKnife.bind(this);
        initView();
        initData();
    }


    /**
     * 说明：	       初始化UI                                           <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/7/28 9:23                        <br>
     */
    private void initView()
    {
        this.mContext = this;
        toolbar.setOnMenuItemClickListener(this);
    }


    /**
     * 说明：	       初始化actionBar                                           <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/7/28 9:55                        <br>
     */
/*    @Override
    public void onCreateCustomToolBar(Toolbar toolbar)
    {
        super.onCreateCustomToolBar(toolbar);
        toolbar.showOverflowMenu();
        getLayoutInflater().inflate(R.layout.toolbar_search_add, toolbar) ;
    }*/

    /**
     * 说明：	       初始化数据                          <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/7/28 9:23                        <br>
     */
    private void initData()
    {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar_goods, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_search:
                ToastUtils.show(mContext, "搜索");
                break;
            case R.id.action_add:
                ToastUtils.show(mContext, "添加");
                break;
        }
        return false;
    }
}
