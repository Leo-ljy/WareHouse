package com.hzmy.zm.warehouse.utils;

import java.lang.reflect.Field;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * @author 追梦
 * @email 1521541979@qq.com
 * @description 屏幕尺寸工具类
 */
public class ScreenUtils
{
	private Context				mCtx;
	private static ScreenUtils	mScreenTools;

	public static ScreenUtils getInstance(Context ctx)
	{
		if (null == mScreenTools)
		{
			mScreenTools = new ScreenUtils(ctx);
		}
		return mScreenTools;
	}

	private ScreenUtils(Context ctx) {
		mCtx = ctx.getApplicationContext();
	}

	/**
	 * 得到设备屏幕的宽度
	 */
	public int getScreenWidth()
	{
		return mCtx.getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * 得到设备屏幕的高度
	 */
	public int getScreenHeight()
	{
		return mCtx.getResources().getDisplayMetrics().heightPixels;
	}

	/**
	 * 得到设备的密度
	 */
	public float getScreenDensity()
	{
		return mCtx.getResources().getDisplayMetrics().density;
	}

	/**
	 * 把密度转换为像素
	 */

	public int dip2px(int dip)
	{
		float density = getDensity(mCtx);
		return (int) (dip * density + 0.5);
	}

	/**
	 * @description 像素转换成密度
	 * @author 追梦
	 * @email 1521541979@qq.com
	 * @param px
	 * @return
	 */
	public int px2dip(int px)
	{
		float density = getDensity(mCtx);
		return (int) ((px - 0.5) / density);
	}

	/**
	 * @description 获取密度
	 * @author 追梦
	 * @email 1521541979@qq.com
	 * @param ctx
	 * @return
	 */
	private float getDensity(Context ctx)
	{
		return ctx.getResources().getDisplayMetrics().density;
	}

	/**
	 * 获取状态栏高度
	 * 
	 * @return
	 */
	public int getStatusBarHeight()
	{
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 0;
		try
		{
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			sbar = mCtx.getResources().getDimensionPixelSize(x);
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		return sbar;
	}

	/**
	 * 判断是否为平板
	 * 
	 * @return
	 */
	public boolean isPad(Context activity)
	{
		WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		// 屏幕宽度
		float screenWidth = display.getWidth();
		// 屏幕高度
		float screenHeight = display.getHeight();
		DisplayMetrics dm = new DisplayMetrics();
		display.getMetrics(dm);
		double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
		double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
		// 屏幕尺寸
		double screenInches = Math.sqrt(x + y);
		// 大于6尺寸则为Pad
		if (screenInches >= 6.0)
		{

			LogUtils.d("是平板");
			return true;

		}
		else
		{
			LogUtils.d("不是平板");
			return false;
		}
	}

	/**
	 * @description 获取手机型号
	 * @author 追梦
	 * @email 1521541979@qq.com
	 * @return
	 */
	public String getPhoneModel()
	{
		return android.os.Build.MODEL;
	}

	/**
	 * @description 获取SDK版本号
	 * @author 追梦
	 * @email 1521541979@qq.com
	 * @return
	 */
	public String getPhoneSDKNum()
	{
		String sdk = android.os.Build.VERSION.SDK;
		LogUtils.d("sdk版本号：" + sdk);
		return sdk;
	}

	/**
	 * @description 获取系统版本号
	 * @author 追梦
	 * @email 1521541979@qq.com
	 * @return
	 */
	public String getSystemVersion()
	{
		
		return android.os.Build.VERSION.RELEASE;
	}

	/**
	 * @description  获取当前版本号
	 * @author       追梦
	 * @email        1521541979@qq.com 
	 * @param context
	 * @return       
	 */  
	private String getAppVersionName(Context context)
	{
		String versionName = "";
		try
		{
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			versionName = packageInfo.versionName;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return versionName;
	}
}
