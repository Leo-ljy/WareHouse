package com.hzmy.zm.warehouse.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.hzmy.zm.warehouse.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述：                                                               <br>
 * 作者：        追梦                                                <br>
 * 邮箱：        1521541979@qq.com                        <br>
 * 公司：        杭州码友网络科技有限公司             <br>
 * 日期：        2016/7/13 9:44                               <br>
 */
public class MessageFragment extends BaseFragment
{

    @Bind(R.id.tv_middle)
    TextView tvMiddle;
    @Bind(R.id.lv_fg_msg)
    ListView lvFgMsg;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
