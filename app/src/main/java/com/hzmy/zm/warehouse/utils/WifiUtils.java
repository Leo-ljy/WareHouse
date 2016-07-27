package com.hzmy.zm.warehouse.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hzmy.zm.warehouse.widgets.dialog.AlertDialogManger;
import com.hzmy.zm.warehouse.widgets.waitAnimation.WaitingAnimationTast;

import java.io.IOException;

/**
 * @author 追梦
 * @email 1521541979@qq.com
 * @description WiFi管理类
 */
public class WifiUtils
{
	/**
	 * @author 追梦
	 * @email 1521541979@qq.com
	 * @description 检测是否联网
	 * @param activity
	 * @return
	 */
	public static boolean checkNetworkInfo(Activity activity)
	{
		ConnectivityManager conMan = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (!isPad(activity))
		{
			// mobile 3G Data Network
			State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
			// 如果3G网络和wifi网络都未连接，且不是处于正在连接状态 则进入Network Setting界面 由用户配置网络连接
			if (mobile == State.CONNECTED || mobile == State.CONNECTING) { return true; }

		}
		// wifi
		State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

		if (wifi == State.CONNECTED || wifi == State.CONNECTING)
		{
			WaitingAnimationTast waitingAnimationTast = new WaitingAnimationTast(activity);
			waitingAnimationTast.setTitle("正在测试网络状态");

			waitingAnimationTast.execute();

			// 推荐这种方法，速度快
			getInterentInfo(activity, waitingAnimationTast);

			// 第二种方法，速度慢，不怎么推荐
			// if (ping(waitingAnimationTast))
			// {
			// // 如果网络可用
			// return true;
			// }
			// else
			// {
			// goToWifiSetting(activity);
			// return false;
			// }

		}
		else
		{
			goToWifiSetting(activity);
			return false;
		}
		return true;

	}

	private static String getInterentInfo(final Activity activity, final WaitingAnimationTast waitingAnimationTast)
	{
		RequestQueue mQueue = Volley.newRequestQueue(activity);
		String url = "http://www.baidu.com";
		LogUtils.d("计时开始");
		StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
														new Response.Listener<String>() {
															@Override
															public void onResponse(String response)
															{
																waitingAnimationTast.progressDialog.dismiss();
																//
																String deviceInfoJsonStr = response.trim().toString();
																// LogUtils.d("success， deviceInfoJsonStr:  "
																// +
																// deviceInfoJsonStr);

															}
															
															
														}, new Response.ErrorListener() {
															@Override
															public void onErrorResponse(VolleyError error)
															{
																waitingAnimationTast.progressDialog.dismiss();

																goToWifiSetting(activity);
																LogUtils.d("计时结束");
																Log.e("TAG", "失败返回结果：" + error.getMessage(), error);
															}
														})
		{
			@Override
			public RetryPolicy getRetryPolicy()
			{
				RetryPolicy retryPolicy = new DefaultRetryPolicy(1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
				return retryPolicy;
			}
			
		};

//		stringRequest.setRetryPolicy(new DefaultRetryPolicy(1000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		
		mQueue.add(stringRequest);
		
		return stringRequest.toString();
	}

	/*
	 * @author sichard
	 * 
	 * @category 判断是否有外网连接（普通方法不能判断外网的网络是否连接，比如连接上局域网） 速度太慢，不推荐
	 * 
	 * @return
	 */
	public static boolean ping(WaitingAnimationTast waitingAnimationTast)
	{

		String result = null;
		try
		{
			String ip = "www.baidu.com";// ping 的地址，可以换成任何一种可靠的外网
			Process p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);// ping网址3次

			// 读取ping的内容，可以不加
			// InputStream input = p.getInputStream();
			// BufferedReader in = new BufferedReader(new
			// InputStreamReader(input));
			// StringBuffer stringBuffer = new StringBuffer();
			// String content = "";
			// while ((content = in.readLine()) != null) {
			// stringBuffer.append(content);
			// }
			// Log.d("------ping-----", "result content : " +
			// stringBuffer.toString());

			// ping的状态
			int status = p.waitFor();
			if (status == 0)
			{
				result = "success";
				waitingAnimationTast.progressDialog.dismiss();
				return true;
			}
			else
			{
				result = "failed";
			}

		}
		catch (IOException e)
		{
			result = "IOException";
		}
		catch (InterruptedException e)
		{
			result = "InterruptedException";
		}
		finally
		{
			Log.d("----result---", "result = " + result);
		}
		waitingAnimationTast.progressDialog.dismiss();
		return false;
	}

	/**
	 * @author 追梦
	 * @email 1521541979@qq.com
	 * @description 跳转到WiFi设置页面
	 * @param context
	 */
	public static void goToWifiSetting(Context context)
	{
		// 进入无线网络配置界面
		context = context.getApplicationContext();
		ToastUtils.showLong(context, "当前网络不可用,请选择可用网络");
		Intent intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
		context.startActivity(intent);

	}

	/**
	 * @author 追梦
	 * @email 1521541979@qq.com
	 * @description 检测是否在WiFi环境下
	 * @param activity
	 */
	public static boolean checkIsinWifi(final Activity activity)
	{
		// 判断是否是WiFi环境下
		ConnectivityManager conMan = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

		// wifi
		State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

		if (!isPad(activity))
		{
			// mobile 3G Data Network
			State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
			if (mobile == State.CONNECTED || wifi != State.CONNECTED)
			{
				DialogInterface.OnClickListener ok = new AlertDialog.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						Intent intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
						activity.startActivity(intent);
					}
				};

				AlertDialogManger.getAlertDialog(activity, "提示", "没有连接WiFi，请连接机器WiFi后重试", "确定", "", ok, null, null);
				return false;
			}
			else
			{
				if (wifi != State.CONNECTED)
				{
					DialogInterface.OnClickListener ok = new AlertDialog.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							Intent intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
							intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
							activity.startActivity(intent);
						}
					};

					AlertDialogManger.getAlertDialog(activity, "提示", "没有连接WiFi，请连接机器WiFi后重试", "确定", "", ok, null, null);
					return false;
				}
			}

		}
		else
		{
			return true;
		}
		return true;

	}

	/**
	 * @author 追梦
	 * @email 1521541979@qq.com
	 * @description 检测是否联网
	 * @param activity
	 */
	public static boolean isConnectNet(final Activity activity)
	{
		// 判断是否是WiFi环境下
		ConnectivityManager conMan = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (!isPad(activity))
		{
			// mobile 3G Data Network
			State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
			// wifi
			State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

			// 如果3G网络和wifi网络都未连接，且不是处于正在连接状态 则进入Network Setting界面 由用户配置网络连接
			if (mobile != State.CONNECTED && wifi != State.CONNECTED)
			{

				return false;

			}
			else
			{

				return true;

			}
		}
		else
		{

			State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

			// 如果3G网络和wifi网络都未连接，且不是处于正在连接状态 则进入Network Setting界面 由用户配置网络连接
			if (wifi != State.CONNECTED)
			{

				return false;

			}
			else
			{

				return true;

			}
		}

	}

	/**
	 * 判断是否为平板
	 * 
	 * @return
	 */
	private static boolean isPad(Context activity)
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
		if (screenInches >= 6.0) { return true; }
		return false;
	}

}
