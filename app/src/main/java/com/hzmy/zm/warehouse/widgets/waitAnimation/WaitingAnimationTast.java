package com.hzmy.zm.warehouse.widgets.waitAnimation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

/**
 * @author 追梦
 * @email 1521541979@qq.com
 * @description
 */
public class WaitingAnimationTast extends AsyncTask<Void, Integer, Void>
{
	public ProgressDialog	progressDialog;
	public String title = "正在初始化";
	public String message = "请稍等...";
	private Activity	mActivity;

	public WaitingAnimationTast(Activity a) {
		this.mActivity = a;
	}

	@Override
	protected void onPreExecute()
	{
		super.onPreExecute();
		progressDialog = ProgressDialog.show(mActivity, title, message, false);
		setProgressDialog(progressDialog);
	}

	@Override
	protected Void doInBackground(Void... arg0)
	{

		return null;

	}

	@Override
	protected void onProgressUpdate(Integer... values)
	{
		super.onProgressUpdate(values);
		// dialog.dismiss();//关闭ProgressDialog
	}
	
	@Override
	protected void onPostExecute(Void result)
	{
		super.onPostExecute(result);
	}

	public ProgressDialog getProgressDialog()
	{
		return progressDialog;
	}

	public void setProgressDialog(ProgressDialog progressDialog)
	{
		this.progressDialog = progressDialog;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	
	
}
