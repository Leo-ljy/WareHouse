package com.hzmy.zm.warehouse.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.alibaba.mobileim.YWIMKit;
import com.hzmy.zm.warehouse.R;
import com.hzmy.zm.warehouse.app.BaseActivity;
import com.hzmy.zm.warehouse.third_party_libs.a_li_yun_wang.helper.LoginSampleHelper;

/**
 * 描述：         联系人列表                                                      <br>
 * 作者：        追梦                                                <br>
 * 邮箱：        1521541979@qq.com                        <br>
 * 公司：        杭州码友网络科技有限公司             <br>
 * 日期：        2016/8/12 13:01                               <br>
 */
public class ContactListActivity extends BaseActivity
{
    private YWIMKit mIMKit;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        InitView();
    }

    private void InitView()
    {
        mIMKit = LoginSampleHelper.getInstance().getIMKit();
        Fragment fragment = mIMKit.getContactsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_contact, fragment).commit();
    }
}
