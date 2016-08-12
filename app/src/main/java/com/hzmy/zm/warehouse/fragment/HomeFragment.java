package com.hzmy.zm.warehouse.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.mobileim.YWIMKit;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.hzmy.zm.warehouse.R;
import com.hzmy.zm.warehouse.adapter.GridHomeAdapter;
import com.hzmy.zm.warehouse.third_party_libs.a_li_yun_wang.helper.LoginSampleHelper;
import com.hzmy.zm.warehouse.third_party_libs.convenient_banner.NetworkImageHolderView;
import com.hzmy.zm.warehouse.ui.GoodsActivity;
import com.hzmy.zm.warehouse.ui.MainActivity;
import com.hzmy.zm.warehouse.utils.LogUtils;
import com.hzmy.zm.warehouse.utils.ToastUtils;

import java.util.Arrays;
import java.util.List;
import java.util.jar.Manifest;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 说明：	                                                   <br>
 * 作者：         追梦                                     <br>
 * 邮箱：        1521541979@qq.com              <br>
 * 公司：        杭州码友网络科技有限公司   <br>
 * 日期：        2016/7/25 13:10                        <br>
 */
public class HomeFragment extends BaseFragment implements AdapterView.OnItemClickListener, OnItemClickListener
{

    @Bind(R.id.btn_left)
    Button btnLeft;
    @Bind(R.id.tv_middle)
    TextView tvMiddle;
    @Bind(R.id.btn_right)
    Button btnRight;
    @Bind(R.id.ly_top_bar)
    RelativeLayout lyTopBar;
    @Bind(R.id.gv_home)
    GridView gvHome;
    @Bind(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    private View homeFragmentView;

    private List<String> networkImages;
    private String[] images = {
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };

    private static  String TAG =  "HomeFragment";
    private YWIMKit mIMKit;

    @Override
    protected View initView()
    {
        homeFragmentView = View.inflate(mAppContext, R.layout.fragment_home, null);
        ButterKnife.bind(this, homeFragmentView);

        initConvenient();
        initAliYW();

        return homeFragmentView;
    }

    /**
     * 初始化阿里云旺
     */
    private void initAliYW()
    {
        mIMKit = LoginSampleHelper.getInstance().getIMKit();
    }


    /**
     * 说明：	      初始化轮播图                                             <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/7/25 13:28                        <br>
     */
    private void initConvenient()
    {
        networkImages = Arrays.asList(images);

        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>()
        {
            @Override
            public NetworkImageHolderView createHolder()
            {
                return new NetworkImageHolderView();
            }
        }, networkImages)    //设置需要切换的View
                .setPointViewVisible(true)    //设置指示器是否可见
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator,
                                            R.mipmap.ic_page_indicator_focused})   //设置指示器圆点
                .startTurning(3000)     //设置自动切换（同时设置了切换时间间隔）
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL) //设置指示器位置（左、中、右）
                .setOnItemClickListener(this);
    }

    @Override
    protected void initData()
    {
        tvMiddle.setText(R.string.home_fregment_title);


        GridHomeAdapter gridHomeAdapter = new GridHomeAdapter(mAppContext);
        gvHome.setAdapter(gridHomeAdapter);
        gvHome.setOnItemClickListener(this);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Intent intent = null;
        switch (position)
        {
            default:
            case 0:
                LogUtils.d(TAG,"跳转到货物管理");

                intent = new Intent(mAppContext, GoodsActivity.class);
                break;
            case 1:
//                intent = new Intent(mAppContext, GoodsActivity.class);
                Fragment f = mIMKit.getContactsFragment();

                ((MainActivity)getActivity()).changFragmentByTag(HomeFragment.this, f, getActivity().getSupportFragmentManager().beginTransaction(), "1");
                break;
            case 2:
                intent = new Intent(mAppContext, GoodsActivity.class);
                break;
            case 3:
                intent = new Intent(mAppContext, GoodsActivity.class);
                break;
            case 4:
                intent = new Intent(mAppContext, GoodsActivity.class);
                break;
            case 5:
                intent = new Intent(mAppContext, GoodsActivity.class);
                break;
            case 6:
                intent = new Intent(mAppContext, GoodsActivity.class);
                break;
            case 7:
                intent = new Intent(mAppContext, GoodsActivity.class);
                break;
            case 8:
                intent = new Intent(mAppContext, GoodsActivity.class);
                break;
        }

        if (intent != null)  startActivity(intent);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    /**
     * 说明：	         轮播图点击事件                                          <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/7/26 9:58                        <br>
     */
    @Override
    public void onItemClick(int position)
    {
        switch (position)
        {
            case 0:
                ToastUtils.show(mAppContext, "轮播图1");
                break;
            case 1:
                ToastUtils.show(mAppContext, "轮播图2");
                break;
            case 2:
                ToastUtils.show(mAppContext, "轮播图3");
                break;
            case 3:
                ToastUtils.show(mAppContext, "轮播图4");
                break;
            case 4:
                ToastUtils.show(mAppContext, "轮播图5");
                break;
        }
    }
}
