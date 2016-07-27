package com.hzmy.zm.warehouse.widgets.dialog;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.View;

/**
 * @author       追梦
 * @email        1521541979@qq.com
 * @description  对话框管理     
 */  
public class AlertDialogManger
{
	/**
	 * @description  获取两个按钮的dialog
	 * @author       追梦
	 * @email        1521541979@qq.com 
	 * @param activity
	 * @param title
	 * @param message
	 * @param okString
	 * @param noString
	 * @param ok
	 * @param no
	 * @param view       
	 */  
	public static void getAlertDialog(Context activity, String title, String message,String okString, String noString, OnClickListener ok, OnClickListener no, View view)
	{
		Builder builder = new Builder(activity);
		
		if (title.length() != 0) builder.setTitle(title);
		
		if (view != null) builder.setView(view);
		
		if (message.length() != 0) builder.setMessage(message);
		
		if (okString.length() != 0) builder.setPositiveButton(okString, ok);
		if (noString.length() != 0) builder.setNegativeButton(noString, no);
		builder.show();
	}
	
	/**
	 * @description  获取3个按钮的dialog
	 * @author       追梦
	 * @email        1521541979@qq.com 
	 * @param activity
	 * @param title
	 * @param message
	 * @param okString
	 * @param noString
	 * @param ok
	 * @param no
	 * @param view       
	 */  
	public static void getAlertDialogThreeBtn(Context activity, String title, String message,String okString, String middleString, String noString, OnClickListener ok, OnClickListener middle, OnClickListener no, View view)
	{
		Builder builder = new Builder(activity);
		
		if (title.length() != 0) builder.setTitle(title);
		
		if (view != null) builder.setView(view);
		
		if (message.length() != 0) builder.setMessage(message);
		
		if (okString.length() != 0) builder.setPositiveButton(okString, ok);
		
		if (noString.length() != 0) builder.setNegativeButton(noString, no);
		
		if (middleString.length() != 0) builder.setNeutralButton(middleString, middle);
		
		
		builder.show();
	}
}
