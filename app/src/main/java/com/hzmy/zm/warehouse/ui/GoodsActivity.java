package com.hzmy.zm.warehouse.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.hzmy.zm.warehouse.R;
import com.hzmy.zm.warehouse.adapter.ListViewGoodsAdapter;
import com.hzmy.zm.warehouse.bean.Goods;
import com.hzmy.zm.warehouse.config.Urls;
import com.hzmy.zm.warehouse.third_party_libs.volley_gson_okhttp.manage.VolleyManager;
import com.hzmy.zm.warehouse.utils.LogUtils;
import com.hzmy.zm.warehouse.utils.ToastUtils;
import com.hzmy.zm.warehouse.widgets.toolbar.ToolBarActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private List<Goods> lists;
    private static  String TAG = "GoodsActivity";
    private ListViewGoodsAdapter lv_adapter;
    private Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case  0:
                    //获取网络数据成功
                    break;
                case 1:
                    //获取网络数据失败
                    break;


            }
        }
    };

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
     * 说明：	       初始化数据                          <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/7/28 9:23                        <br>
     */
    private void initData()
    {
        lists = new ArrayList<>();
        VolleyManager.newInstance().GsonGetRequest(TAG, Urls.goodsJsonUrl, Goods[].class, new Response.Listener<Goods[]>()
        {
            @Override
            public void onResponse(Goods[] response)
            {

                lists = Arrays.asList(response);
                LogUtils.d(TAG,  "个数：" + lists.size());
                LogUtils.d(TAG, "开始适配");
                lv_adapter = new ListViewGoodsAdapter(mContext, lists);
                lvGoods.setAdapter(lv_adapter);

//                Message msg = new Message();
//                msg.what = 0;
//                mHandler.sendMessage(msg);

//                for (int i = 0; i < lists.size(); i++)
//                {
//                    Goods temp = lists.get(i);
//                    LogUtils.d(TAG, "goodName: " + temp.getGoodsName());
//                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                LogUtils.e(TAG, "加载商品列表出错" + error.getMessage().toString());
            }
        });



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
