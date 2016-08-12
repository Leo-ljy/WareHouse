package com.hzmy.zm.warehouse.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.hzmy.zm.warehouse.R;
import com.hzmy.zm.warehouse.adapter.ListViewGoodsAdapter;
import com.hzmy.zm.warehouse.adapter.drop_down_menu.ConstellationAdapter;
import com.hzmy.zm.warehouse.adapter.drop_down_menu.GirdDropDownAdapter;
import com.hzmy.zm.warehouse.adapter.drop_down_menu.ListDropDownAdapter;
import com.hzmy.zm.warehouse.bean.GoodsEntity;
import com.hzmy.zm.warehouse.bean.drop_down.FilterData;
import com.hzmy.zm.warehouse.bean.drop_down.FilterEntity;
import com.hzmy.zm.warehouse.bean.drop_down.FilterTwoEntity;
import com.hzmy.zm.warehouse.constant.Urls;
import com.hzmy.zm.warehouse.third_party_libs.volley_gson_okhttp.manage.VolleyManager;
import com.hzmy.zm.warehouse.utils.DensityUtil;
import com.hzmy.zm.warehouse.utils.LogUtils;
import com.hzmy.zm.warehouse.utils.ModelUtil;
import com.hzmy.zm.warehouse.widgets.drop_down.FilterView;
import com.hzmy.zm.warehouse.widgets.drop_down_menu.DropDownMenu;
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
    private Activity mActivity;
    private List<GoodsEntity> listDatas = new ArrayList<>();
    private static String TAG = "GoodsActivity";
    private ListViewGoodsAdapter lv_adapter;

    private int start = 0;
    private int count = 15;

    //---------------Drop Down Menu开始---------------------
    @Bind(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;

    private String headers[] = {"城市", "年龄", "性别", "星座"};
    private List<View> popupViews = new ArrayList<>();

    private GirdDropDownAdapter cityAdapter;
    private ListDropDownAdapter ageAdapter;
    private ListDropDownAdapter sexAdapter;
    private ConstellationAdapter constellationAdapter;

    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京",
                              "杭州"};
    private String ages[] = {"不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};
    private String sexs[] = {"不限", "男", "女"};
    private String constellations[] = {"不限", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座",
                                       "射手座", "摩羯座", "水瓶座", "双鱼座"};

    private int constellationPosition = 0;
    //-----------------Drop Down Menu 结束-----------------------

    //-----------------drop down 开始-------------------
//    @Bind(R.id.fv_top_filter)
    FilterView fvTopFilter;
    private FilterData filterData; // 筛选数据

    private int mScreenHeight; // 屏幕高度

    //-----------------drop down 结束 -----------------------------

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
        this.mActivity = this;
        toolbar.setOnMenuItemClickListener(this);

        initDropDownMenu();
//        initDropDown();
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
        mPtrFrameLayout.setPtrHandler(new PtrHandler()
        {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header)
            {
                // here check list view, not content.
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mListView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame)
            {
                //实现下拉刷新的功能
                Log.i("test", "-----onRefreshBegin-----");
                mPtrFrameLayout.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        start = 0;
                        refreshData(start, count);
                    }
                }, 500);
            }
        });
        //设置延时自动刷新数据
        mPtrFrameLayout.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                mPtrFrameLayout.autoRefresh(false);
            }
        }, 200);
        //4.加载更多的组件
        mLoadMoreListViewContainer = (LoadMoreListViewContainer) findViewById(R.id.load_more_list_view_container);
        mLoadMoreListViewContainer.setAutoLoadMore(true);//设置是否自动加载更多
        mLoadMoreListViewContainer.useDefaultHeader();
        //5.添加加载更多的事件监听
        mLoadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler()
        {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer)
            {
                //模拟加载更多的业务处理
                mLoadMoreListViewContainer.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        start++;
                        loadMoreData(start * count, count);
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

        VolleyManager.newInstance().GsonPostRequest(TAG, map, Urls.goodsJsonUrl, GoodsEntity[].class, new Response.Listener<GoodsEntity[]>()
        {
            @Override
            public void onResponse(GoodsEntity[] response)
            {
                listDatas.clear();
                List<GoodsEntity> tempList = Arrays.asList(response);
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

    public void loadMoreData(int start, final int count)
    {

        Map<String, String> map = new HashMap<String, String>();
        map.put("startPage", String.valueOf(start));
        map.put("password", String.valueOf(count));

        VolleyManager.newInstance().GsonPostRequest(TAG, map, Urls.goodsJsonUrl, GoodsEntity[].class, new Response.Listener<GoodsEntity[]>()
        {
            @Override
            public void onResponse(GoodsEntity[] response)
            {
                List<GoodsEntity> tempList = Arrays.asList(response);
                listDatas.addAll(tempList);

                if (tempList.size() > count)
                {
//                          mLoadMoreListViewContainer.loadMoreFinish(true, false);
                    //以下是加载失败的情节
                    int errorCode = 0;
                    String errorMessage = "加载失败，点击加载更多";
                    mLoadMoreListViewContainer.loadMoreError(errorCode, errorMessage);
                } else
                {
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

    //--------------------drop down menu 开始------------------------
    public void initDropDownMenu()
    {

        //init city menu
        final ListView cityView = new ListView(this);
        cityAdapter = new GirdDropDownAdapter(this, Arrays.asList(citys));
        cityView.setDividerHeight(0);
        cityView.setAdapter(cityAdapter);

        //init age menu
        final ListView ageView = new ListView(this);
        ageView.setDividerHeight(0);
        ageAdapter = new ListDropDownAdapter(this, Arrays.asList(ages));
        ageView.setAdapter(ageAdapter);

        //init sex menu
        final ListView sexView = new ListView(this);
        sexView.setDividerHeight(0);
        sexAdapter = new ListDropDownAdapter(this, Arrays.asList(sexs));
        sexView.setAdapter(sexAdapter);

        //init constellation
        final View constellationView = getLayoutInflater().inflate(R.layout.custom_layout, null);
        GridView constellation = ButterKnife.findById(constellationView, R.id.constellation);
        constellationAdapter = new ConstellationAdapter(this, Arrays.asList(constellations));
        constellation.setAdapter(constellationAdapter);
        TextView ok = ButterKnife.findById(constellationView, R.id.ok);
        ok.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mDropDownMenu.setTabText(constellationPosition == 0 ? headers[3]
                                                                    : constellations[constellationPosition]);
                mDropDownMenu.closeMenu();
            }
        });

        //init popupViews
        popupViews.add(cityView);
        popupViews.add(ageView);
        popupViews.add(sexView);
        popupViews.add(constellationView);

        //add item click event
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                cityAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : citys[position]);
                mDropDownMenu.closeMenu();
            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ageAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : ages[position]);
                mDropDownMenu.closeMenu();
            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                sexAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[2] : sexs[position]);
                mDropDownMenu.closeMenu();
            }
        });

        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                constellationAdapter.setCheckItem(position);
                constellationPosition = position;
            }
        });

        //init context view
        TextView contentView = new TextView(this);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            contentView.setText("内容显示区域");
        contentView.setGravity(Gravity.CENTER);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        contentView.setVisibility(View.GONE);


        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
    }


    @Override
    public void onBackPressed()
    {
        //退出activity前关闭菜单
        if (mDropDownMenu.isShowing())
        {
            mDropDownMenu.closeMenu();
        } else
        {
            super.onBackPressed();
        }

        if (!fvTopFilter.isShowing())
        {
            super.onBackPressed();
        } else
        {
            fvTopFilter.resetAllStatus();
        }
    }
//--------------------drop down menu 结束------------------------

    //--------------------drop down 开始------------------------
    private void initDropDown()
    {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.header_filter_layout, mListView, false);
//        mListView.addHeaderView(view);
        initData2();
    }

    private void initData2()
    {
        mScreenHeight = DensityUtil.getWindowHeight(this);
        // ListView数据

        // 筛选数据
        filterData = new FilterData();
        filterData.setCategory(ModelUtil.getCategoryData());
        filterData.setSorts(ModelUtil.getSortData());
        filterData.setFilters(ModelUtil.getFilterData());

        // 设置筛选数据
        fvTopFilter.setFilterData(mActivity, filterData);
//        fvTopFilter.showFilterLayout(0);

        // 分类Item点击
        fvTopFilter.setOnItemCategoryClickListener(new FilterView.OnItemCategoryClickListener()
        {
            @Override
            public void onItemCategoryClick(FilterTwoEntity entity)
            {
//                fillAdapter(ModelUtil.getCategoryTravelingData(entity));
            }
        });

        // 排序Item点击
        fvTopFilter.setOnItemSortClickListener(new FilterView.OnItemSortClickListener()
        {
            @Override
            public void onItemSortClick(FilterEntity entity)
            {
//                fillAdapter(ModelUtil.getSortTravelingData(entity));
            }
        });

        // 筛选Item点击
        fvTopFilter.setOnItemFilterClickListener(new FilterView.OnItemFilterClickListener()
        {
            @Override
            public void onItemFilterClick(FilterEntity entity)
            {
//                fillAdapter(ModelUtil.getFilterTravelingData(entity));
            }
        });
    }

    // 填充数据
    private void fillAdapter(List<GoodsEntity> list)
    {
        if (list == null || list.size() == 0)
        {
            int height =
                    mScreenHeight - DensityUtil.dip2px(mContext, 95); // 95 = 标题栏高度 ＋ FilterView的高度
//            lv_adapter.setData(ModelUtil.getNoDataEntity(height));
        } else
        {
            lv_adapter.setData(list);
        }
        lv_adapter.notifyDataSetChanged();
    }

    //--------------------drop down 结束------------------------
}
