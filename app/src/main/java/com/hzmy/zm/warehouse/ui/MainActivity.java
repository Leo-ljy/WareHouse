package com.hzmy.zm.warehouse.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hzmy.zm.warehouse.R;
import com.hzmy.zm.warehouse.app.BaseActivity;
import com.hzmy.zm.warehouse.fragment.BaseFragment;
import com.hzmy.zm.warehouse.fragment.HomeFragment;
import com.hzmy.zm.warehouse.fragment.MessageFragment;
import com.hzmy.zm.warehouse.fragment.UserFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener
{
    @Bind(R.id.rb_home)
    RadioButton rbHome;
    @Bind(R.id.rb_message)
    RadioButton rbMessage;
    @Bind(R.id.rb_user)
    RadioButton rbUser;

    @Bind(R.id.rg_tab_bar)
    RadioGroup rgTabBar;
    @Bind(R.id.ly_content)
    FrameLayout lyContent;
    private Context mContext;

    //Fragment Object
    private BaseFragment fgHome, fgMessage, fgUser;
    private FragmentManager fManager;
    private FragmentTransaction fTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mContext = this;

        initView();
        initData();
    }


    /**
     * 说明：	      初始化视图                                            <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/7/13 9:57                        <br>
     */
    private void initView()
    {
        fManager = getFragmentManager();

        rgTabBar.setOnCheckedChangeListener(this);

        //设置第一个为选中状态
        rbHome.setChecked(true);
    }


    /**
     * 说明：	      初始化数据                                             <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/7/13 9:57                        <br>
     */
    private void initData()
    {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (checkedId)
        {
            case R.id.rb_home:
                if (fgHome == null)
                {
                    fgHome = new HomeFragment();
                    fTransaction.add(R.id.ly_content, fgHome);
                } else
                {
                    fTransaction.show(fgHome);
                }
                break;
            case R.id.rb_message:
                if (fgMessage == null)
                {
                    fgMessage = new MessageFragment();
                        fTransaction.add(R.id.ly_content, fgMessage);
                }else
                {
                    fTransaction.show(fgMessage);
                }
                break;
            case R.id.rb_user:
                    if (fgUser == null)
                    {
                        fgUser = new UserFragment();
                        fTransaction.add(R.id.ly_content, fgUser);
                    }else{
                        fTransaction.show(fgUser);
                    }
                break;

        }

        fTransaction.commit();

    }


    /**
     * 说明：	       隐藏所有fragment                                            <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/7/13 10:39                        <br>
     */
    private void hideAllFragment(FragmentTransaction fTransaction)
    {
        hideFragment(fgHome);
        hideFragment(fgMessage);
        hideFragment(fgUser);
    }


    /**
     * 说明：	       隐藏单个fragment                                            <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/7/13 10:41                        <br>
     */
    private void hideFragment(BaseFragment fg)
    {
        if (fg != null) fTransaction.hide(fg);
    }

}
