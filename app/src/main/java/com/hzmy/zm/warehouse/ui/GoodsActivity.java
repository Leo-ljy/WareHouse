package com.hzmy.zm.warehouse.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.hzmy.zm.warehouse.R;
import com.hzmy.zm.warehouse.adapter.ListViewGoodsAdapter;
import com.hzmy.zm.warehouse.bean.Goods;
import com.hzmy.zm.warehouse.config.Urls;
import com.hzmy.zm.warehouse.third_party_libs.volley_gson_okhttp.manage.VolleyManager;
import com.hzmy.zm.warehouse.utils.LogUtils;
import com.hzmy.zm.warehouse.widgets.toolbar.ToolBarActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreHandler;
import in.srain.cube.views.loadmore.LoadMoreListViewContainer;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

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
    ListView mListView;
    @Bind(R.id.load_more_list_view_container)
    LoadMoreListViewContainer mLoadMoreListViewContainer;
    @Bind(R.id.load_more_list_view_ptr_frame)
    PtrFrameLayout mPtrFrameLayout;

    private Context mContext;
    private List<Goods> listDatas = new ArrayList<>();
    private static String TAG = "GoodsActivity";
    private ListViewGoodsAdapter lv_adapter;

    private int start = 0;
    private int count = 15;

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
        //-----------------------------------------------分割线----------------------------------------


        // 1. 为listview的创建一个headerview,注意，如果不加会影响到加载的footview的显示！
        View headerMarginView = new View(this);
        headerMarginView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LocalDisplay.dp2px(20)));
        mListView.addHeaderView(headerMarginView);


        //2.绑定数据
        lv_adapter = new ListViewGoodsAdapter(mContext, listDatas);
        mListView.setAdapter(lv_adapter);

        //3.设置下拉刷新组件和事件监听
        mPtrFrameLayout = (PtrFrameLayout) findViewById(R.id.load_more_list_view_ptr_frame);
        mPtrFrameLayout.setLoadingMinTime(1000);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // here check list view, not content.
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mListView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //实现下拉刷新的功能
                Log.i("test", "-----onRefreshBegin-----");
                mPtrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        start = 0;
                       refreshData(start, count);
                    }
                }, 500);
            }
        });
        //设置延时自动刷新数据
        mPtrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrameLayout.autoRefresh(false);
            }
        }, 200);
        //4.加载更多的组件
        mLoadMoreListViewContainer = (LoadMoreListViewContainer) findViewById(R.id.load_more_list_view_container);
        mLoadMoreListViewContainer.setAutoLoadMore(true);//设置是否自动加载更多
        mLoadMoreListViewContainer.useDefaultHeader();
        //5.添加加载更多的事件监听
        mLoadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
                //模拟加载更多的业务处理
                mLoadMoreListViewContainer.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        start++;
                        loadMoreData(start * count,count);
                    }
                }, 1000);
            }
        });


    }

    public void refreshData(int start, int count)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("startPage", String.valueOf(start));
        map.put("password", String.valueOf(count));

        VolleyManager.newInstance().GsonPostRequest(TAG, map, Urls.goodsJsonUrl, Goods[].class, new Response.Listener<Goods[]>()
        {
            @Override
            public void onResponse(Goods[] response)
            {
                listDatas.clear();
                List<Goods> tempList = Arrays.asList(response);
                listDatas.addAll(tempList);
                LogUtils.d(TAG, "数量：" + tempList);
                mPtrFrameLayout.refreshComplete();
                //第一个参数是：数据是否为空；第二个参数是：是否还有更多数据
                mLoadMoreListViewContainer.loadMoreFinish(false, true);
                lv_adapter.notifyDataSetChanged();

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

    public void loadMoreData(int start, final int count){

        Map<String, String> map = new HashMap<String, String>();
        map.put("startPage", String.valueOf(start));
        map.put("password", String.valueOf(count));

        VolleyManager.newInstance().GsonPostRequest(TAG, map, Urls.goodsJsonUrl, Goods[].class, new Response.Listener<Goods[]>()
        {
            @Override
            public void onResponse(Goods[] response)
            {
                List<Goods> tempList = Arrays.asList(response);
                listDatas.addAll(tempList);

                if (tempList.size() > count) {
//                          mLoadMoreListViewContainer.loadMoreFinish(true, false);
                    //以下是加载失败的情节
                    int errorCode = 0;
                    String errorMessage = "加载失败，点击加载更多";
                    mLoadMoreListViewContainer.loadMoreError(errorCode, errorMessage);
                } else{
                    mLoadMoreListViewContainer.loadMoreFinish(false, true);
                }
                lv_adapter.notifyDataSetChanged();

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
        Intent intent = null;
        switch (item.getItemId())
        {
            default:
            case R.id.action_search:
                intent = new Intent(mContext, SearchActivity.class);
                break;
            case R.id.action_add:
                intent = new Intent(mContext, SearchActivity.class);
                break;
        }
        startActivity(intent);
        return false;
    }


}
