package com.hzmy.zm.warehouse.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dtr.settingview.lib.SettingView;
import com.dtr.settingview.lib.entity.SettingData;
import com.dtr.settingview.lib.entity.SettingViewItemData;
import com.dtr.settingview.lib.item.BasicItemViewH;
import com.hzmy.zm.warehouse.R;
import com.hzmy.zm.warehouse.third_party_libs.glide.ImageManager;
import com.hzmy.zm.warehouse.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述：                                                               <br>
 * 作者：        追梦                                                <br>
 * 邮箱：        1521541979@qq.com                        <br>
 * 公司：        杭州码友网络科技有限公司             <br>
 * 日期：        2016/7/13 9:44                               <br>
 */
public class UserFragment extends BaseFragment
{

    @Bind(R.id.tv_middle)
    TextView tvMiddle;
    @Bind(R.id.setting_view_01)
    SettingView mSettingView1;
    @Bind(R.id.setting_view_02)
    SettingView mSettingView2;
    @Bind(R.id.fresco_user_portrait)
    ImageView frescoUserPortrait;
    @Bind(R.id.tv_company_name)
    TextView tvCompanyName;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.lly_fg_user_info)
    LinearLayout llyFgUserInfo;

    private View userFragmentView;


    private SettingData mItemData = null;
    private SettingViewItemData mItemViewData = null;
    private List<SettingViewItemData> mListData = new ArrayList<SettingViewItemData>();
    private String[] settingStringNameArr;

    @Override
    protected View initView()
    {
        userFragmentView = View.inflate(mAppContext, R.layout.fragment_user, null);
        ButterKnife.bind(this, userFragmentView);
        return userFragmentView;
    }

    @Override
    protected void initData()
    {
        String url = "";

        ImageManager imageManager = new ImageManager(mAppContext);
        imageManager.loadCircleImage(url, frescoUserPortrait, R.mipmap.ic_place_img);


        tvMiddle.setText(getString(R.string.user_center));
        settingStringNameArr = getResources().getStringArray(R.array.user_certer_arr);

        /*========================头像============================*/
        llyFgUserInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ToastUtils.show(mAppContext, "修改头像");
            }
        });

        /* ==========================SettingView1========================== */
        addItemData(settingStringNameArr[0], R.mipmap.home_grid_view_item2);

        addItemData(settingStringNameArr[1], R.mipmap.home_grid_view_item1_64_64);

        addItemData(settingStringNameArr[2], R.mipmap.home_grid_view_item7);

        mSettingView1.setAdapter(mListData);

        mSettingView1.setOnSettingViewItemClickListener(new SettingView.onSettingViewItemClickListener()
        {
            @Override
            public void onItemClick(int index)
            {
                switch (index)
                {
                    case 0:
                        ToastUtils.show(mAppContext, "第" + (index + 1) + "个");
                        break;
                    case 1:
                        ToastUtils.show(mAppContext, "第" + (index + 1) + "个");
                        break;
                    case 2:
                        ToastUtils.show(mAppContext, "第" + (index + 1) + "个");
                        break;
                }
            }
        });
        /* ==========================SettingView2========================== */
        mListData.clear();

        addItemData("设置", R.mipmap.setting);

        mSettingView2.setAdapter(mListData);
        mSettingView2.setOnSettingViewItemClickListener(new SettingView.onSettingViewItemClickListener()
        {
            @Override
            public void onItemClick(int index)
            {
                ToastUtils.show(mAppContext, settingStringNameArr[3]);
            }
        });

    }

    private void addItemData(String title, int drawResource)
    {
        mItemViewData = new SettingViewItemData();
        mItemData = new SettingData();
        mItemData.setTitle(title);
        mItemData.setDrawable(getResources().getDrawable(drawResource));

        mItemViewData.setData(mItemData);
        mItemViewData.setItemView(new BasicItemViewH(mAppContext));
        mListData.add(mItemViewData);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
