package com.hzmy.zm.warehouse.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.mobileim.YWIMKit;
import com.hzmy.zm.warehouse.R;
import com.hzmy.zm.warehouse.app.AppManager;
import com.hzmy.zm.warehouse.fragment.BaseFragment;
import com.hzmy.zm.warehouse.fragment.HomeFragment;
import com.hzmy.zm.warehouse.fragment.MessageFragment;
import com.hzmy.zm.warehouse.fragment.UserFragment;
import com.hzmy.zm.warehouse.third_party_libs.a_li_yun_wang.helper.LoginSampleHelper;
import com.hzmy.zm.warehouse.utils.LogUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener
{
    @Bind(R.id.rb_home)
    RadioButton rbHome;
    @Bind(R.id.rb_message)
    RadioButton rbMessage;
    @Bind(R.id.rb_user)
    RadioButton rbUser;

    @Bind(R.id.rg_tab_bar)
    RadioGroup rgTabBar;
    //    @Bind(R.id.ly_content)
//    android.support.v4.app.Fragment lyContent;
    private Context mContext;

    //Fragment Object
    private BaseFragment fgHome, fgUser;
    private Fragment fgMessage;
    //    private FragmentManager fManager;
    private android.support.v4.app.FragmentTransaction fTransaction;


    private YWIMKit mIMKit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mContext = this;
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);

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

        mIMKit = LoginSampleHelper.getInstance().getIMKit();

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

        initV4FragmentView(group, checkedId);


//        initFragmentView(group, checkedId);

    }

    /**
     * 处理V4包   注意，这时继承了FragmentActivity
     */
    private void initV4FragmentView(RadioGroup group, int checkedId)
    {
        fTransaction = getSupportFragmentManager().beginTransaction();
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
//                    fgMessage = new MessageFragment();
                    fgMessage = mIMKit.getConversationFragment();
                    fTransaction.add(R.id.ly_content, fgMessage);
                } else
                {
                    fTransaction.show(fgMessage);
                }
                break;
            case R.id.rb_user:
                if (fgUser == null)
                {
                    fgUser = new UserFragment();
                    fTransaction.add(R.id.ly_content, fgUser);
                } else
                {
                    fTransaction.show(fgUser);
                }
                break;

        }

        fTransaction.commit();

    }


    /**
     * 说明：	       fragment之间的切换                                            <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/8/12 9:55                        <br>
     */
    public  Fragment changFragmentByTag(Fragment currFragment, Fragment chooseFragment, FragmentTransaction ft,String TAG) {
        if (currFragment != chooseFragment) {
            ft.hide(currFragment);
            if (chooseFragment.isAdded()) {
                ft.show(chooseFragment);
            } else {
                ft.add(R.id.ly_content, chooseFragment, TAG);
            }
        }
        ft.commitAllowingStateLoss();
        return chooseFragment;
    }

    /**
     * 非V4包， 继承BaseActivity
     */
//    private void initFragmentView(RadioGroup group, int checkedId)
//    {
//    fManager = getFragmentManager();
//        fTransaction = fManager.beginTransaction();
//        hideAllFragment(fTransaction);
//        switch (checkedId)
//        {
//            case R.id.rb_home:
//                if (fgHome == null)
//                {
//                    fgHome = new HomeFragment();
//                    fTransaction.add(R.id.ly_content, fgHome);
//                } else
//                {
//                    fTransaction.show(fgHome);
//                }
//                break;
//            case R.id.rb_message:
//                if (fgMessage == null)
//                {
//                    fgMessage = new MessageFragment();
//
//                    fTransaction.add(R.id.ly_content, fgMessage);
//                }else
//                {
//                    fTransaction.show(fgMessage);
//                }
//                break;
//            case R.id.rb_user:
//                if (fgUser == null)
//                {
//                    fgUser = new UserFragment();
//                    fTransaction.add(R.id.ly_content, fgUser);
//                }else{
//                    fTransaction.show(fgUser);
//                }
//                break;
//
//        }
//
//        fTransaction.commit();
//    }


    /**
     * 说明：	       隐藏所有fragment                                            <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/7/13 10:39                        <br>
     *
     * @param fTransaction
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
    private void hideFragment(Fragment fg)
    {
        if (fg != null) fTransaction.hide(fg);
    }


    /**
     * 退出应用
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {

        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            Hook();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    /**
     * @author 追梦
     * @email 1521541979@qq.com
     * @description 退出应用
     */
    private void Hook()
    {
        new AlertDialog.Builder(this)
                .setTitle("确定退出应用吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        AppManager.getAppManager().AppExit(mContext);

                        // MobclickAgent.onKillProcess(appContext);

                        // int pid = android.os.Process.myPid();
                        // android.os.Process.killProcess(pid);
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        LogUtils.d("Main  销毁了");
        AppManager.getAppManager().finishActivity(this);
    }

    public FragmentTransaction getfTransaction()
    {
        return fTransaction;
    }

    public Fragment getFgMessage()
    {
        return fgMessage;
    }

    public BaseFragment getFgUser()
    {
        return fgUser;
    }

    public RadioButton getRbHome()
    {
        return rbHome;
    }
}
