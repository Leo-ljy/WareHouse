package com.hzmy.zm.warehouse.fragment;

import android.view.View;

import com.hzmy.zm.warehouse.R;

/**
 * 描述：                                                               <br>
 * 作者：        追梦                                                <br>
 * 邮箱：        1521541979@qq.com                        <br>
 * 公司：        杭州码友网络科技有限公司             <br>
 * 日期：        2016/7/13 9:44                               <br>
 */
public class MessageFragment extends  BaseFragment
{

    private View messageFragmentView;

    @Override
    protected View initView()
    {
        messageFragmentView = View.inflate(mAppContext, R.layout.fragment_message, null);


        return messageFragmentView;
    }

    @Override
    protected void initData()
    {

    }
}
