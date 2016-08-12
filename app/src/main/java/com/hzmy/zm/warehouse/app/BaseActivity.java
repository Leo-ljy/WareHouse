package com.hzmy.zm.warehouse.app;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.hzmy.zm.warehouse.utils.LogUtils;

/**
 * 应用程序Activity的基类
 */
public class BaseActivity extends AppCompatActivity
{
	// 是否允许全屏
	private boolean allowFullScreen = true;

	// 是否允许销毁
	private boolean allowDestroy = true;

	private View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉顶部栏
		allowFullScreen = true;
		
		LogUtils.d("【初始化了BaseActivity  添加Activity到堆栈】");
		// 添加Activity到堆栈
		AppManager.getAppManager().addActivity(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		LogUtils.d("baseActivity  销毁了");
		LogUtils.d("【初始化了BaseActivity   结束Activity&从堆栈中移除】");
		// 结束Activity&从堆栈中移除
		AppManager.getAppManager().finishActivity(this);
	}

	public boolean isAllowFullScreen() {
		return allowFullScreen;
	}

	/**
	 * 设置是否可以全屏
	 * 
	 * @param allowFullScreen
	 */
	public void setAllowFullScreen(boolean allowFullScreen) {
		this.allowFullScreen = allowFullScreen;
	}

	public void setAllowDestroy(boolean allowDestroy) {
		this.allowDestroy = allowDestroy;
	}

	public void setAllowDestroy(boolean allowDestroy, View view) {
		this.allowDestroy = allowDestroy;
		this.view = view;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && view != null) {
			view.onKeyDown(keyCode, event);
			if (!allowDestroy) {
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	@Override
	public void onPause() {
		super.onPause();
	}
	
	
	/*
	 * 返回
	 */
	public void doBack(View view) {
		onBackPressed();
	}
}
