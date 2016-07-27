package com.hzmy.zm.warehouse.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzmy.zm.warehouse.utils.LogUtils;


//import android.support.v4.app.Fragment;

/**
 * @author 追梦
 * @email 1521541979@qq.com
 * @description fragment的基类
 */
public abstract class BaseFragment extends Fragment
{
	protected Activity	mActivity;
	protected Context	mAppContext;
	private View		rootView;

	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		this.mActivity = activity;
		this.mAppContext = (Context) activity.getApplicationContext();

		LogUtils.d("【mActivity BaseFragment：" + mActivity);
	}

	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState)
	// {
	// if (rootView == null)
	// {
	// rootView = initView();
	//
	// /* 加载数据 */
	// initData();
	//
	// LogUtils.d("【初始化了fragemnt  rootView == null】");
	// }
	//
	// LogUtils.d("【初始化了fragemnt rootView != null】");
	//
	// // 缓存的rootView需要判断是否已经被加过parent，
	// // 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
	//
	// ViewGroup parent = (ViewGroup) rootView.getParent();
	//
	// if (parent != null)
	// {
	// LogUtils.d("【初始化了fragemnt  onCreateView中    parent.removeView(rootView);】");
	// parent.removeView(rootView);
	// }
	// else
	// {
	// LogUtils.d("【初始化了fragemnt onCreateView中     parent为空;】");
	// }
	//
	//
	// initData();
	// return rootView;
	//
	// }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		rootView = initView();
		
		initData();

		return rootView;

	}
	

	@Override
	public void onDestroyView()
	{
		super.onDestroyView();

		ViewGroup parent = (ViewGroup) rootView.getParent();

		if (parent != null)
		{
			LogUtils.d("【初始化了fragemnt onDestroyView 中   parent.removeView(rootView);】");
			parent.removeView(rootView);
		}
		else
		{
			LogUtils.d("【初始化了fragemnt onDestroyView 中   parent为空;】");
		}

	}

	// @Override
	// public void onActivityCreated(Bundle savedInstanceState)
	// {
	// super.onActivityCreated(savedInstanceState);
	//
	// }

	/**
	 * @author 追梦
	 * @email 1521541979@qq.com
	 * @description 子类重写该方法初始化视图
	 * @return
	 */
	protected abstract View initView();

	/**
	 * @author 追梦
	 * @email 1521541979@qq.com
	 * @description 子类重写该方法加载数据
	 */
	protected abstract void initData();

}
