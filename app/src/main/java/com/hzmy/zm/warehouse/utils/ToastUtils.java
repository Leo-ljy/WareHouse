package com.hzmy.zm.warehouse.utils;


import android.content.Context;
import android.widget.Toast;

/**
 * @author       追梦
 * @email        1521541979@qq.com
 * @description  Toast统一管理类
 */
public class ToastUtils  
{  
  
    private ToastUtils()  
    {  
        /* cannot be instantiated */  
        throw new UnsupportedOperationException("cannot be instantiated");  
    }  
  
    /*是否需要显示，可以在application的onCreat方法中设置*/
    public static boolean isShow = true;  
  
    /** 
     * 短时间显示Toast 
     *  
     * @param context 
     * @param message 
     */  
    public static void showVeryShort(Context context, CharSequence message)  
    {  
        if (isShow)  
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    
    
    /** 
     * 短时间显示Toast 
     *  
     * @param context 
     * @param message 
     */  
    public static void show(Context context, CharSequence message)  
    {  
        if (isShow)  
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();  
    }  
  
    /** 
     * 短时间显示Toast 
     *  
     * @param context 
     * @param message 
     */  
    public static void show(Context context, int message)  
    {  
        if (isShow)  
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();  
    }  
  
    /** 
     * 长时间显示Toast 
     *  
     * @param context 
     * @param message 
     */  
    public static void showLong(Context context, CharSequence message)  
    {  
        if (isShow)  
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();  
    }  
    

    /** 
     * 长时间显示Toast 
     *  
     * @param context 
     * @param message 
     */  
    public static void showLong(Context context, int message)  
    {  
        if (isShow)  
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();  
    }  
  
    /** 
     * 自定义显示Toast时间 
     *  
     * @param context 
     * @param message 
     * @param duration 
     */  
    public static void showDIY(Context context, CharSequence message, int duration)  
    {  
        if (isShow)  
            Toast.makeText(context, message, duration).show();  
    }  
  
    /** 
     * 自定义显示Toast时间 
     *  
     * @param context 
     * @param message 
     * @param duration 
     */  
    public static void showDIY(Context context, int message, int duration)  
    {  
        if (isShow)  
            Toast.makeText(context, message, duration).show();  
    }  
  
}  