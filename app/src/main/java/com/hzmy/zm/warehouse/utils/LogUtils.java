package com.hzmy.zm.warehouse.utils;

import android.util.Log;

/**
 * @author 追梦
 * @email 1521541979@qq.com
 * @description Log统一管理类
 */

public class LogUtils
{

    public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化
    public static boolean isDetail = false;//是否显示具体的类名，方法名
    private static  String TAG = "";
    private String className;

    private LogUtils()
    {
        /* cannot be instantiated */

        throw new UnsupportedOperationException("cannot be instantiated");
    }

    // 下面四个是默认tag的函数
    public static void i(String msg)
    {

        if (isDebug)
        {
            if (isDetail)
            {
                Log.i(TAG, buildMessage(msg));
            }else{
                Log.i(TAG, msg);
            }
        }
    }

    public static void d(String msg)
    {

        if (isDebug)
        {
            if (isDetail)
            {
                Log.d(TAG, buildMessage(msg));
            }else{
                Log.d(TAG, msg);
            }
        }
    }

    public static void e(String msg)
    {
        if (isDebug)
        {
            if (isDetail)
            {
                Log.e(TAG, buildMessage(msg));
            }else{
                Log.e(TAG, msg);
            }
        }
    }

    public static void v(String msg)
    {
        if (isDebug)
            Log.v(TAG, msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg)
    {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg)
    {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void e(String tag, String msg)
    {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void v(String tag, String msg)
    {
        if (isDebug)
            Log.i(tag, msg);
    }


    /**
     * 说明：	      获取类名                                             <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/7/29 9:16                        <br>
     */
    public static String getClassName()
    {
        StackTraceElement caller = new Throwable().fillInStackTrace().getStackTrace()[2];
        String className =  caller.getClassName();
        return  className;
    }


    /**
     * 说明：	        获取方法名                                           <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/7/29 9:16                        <br>
     */
    public static String  getMethodName(){
        StackTraceElement caller = new Throwable().fillInStackTrace().getStackTrace()[2];
        String methodName =  caller.getClassName();
        return  methodName;
    }


    /**
     * 说明：	       另一种获取类名方法名的方法                                            <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/7/29 9:17                        <br>
     */
    private void anotherMethod()
    {

        String clazz = this.getClass().getName();

        String method = Thread.currentThread()
                .getStackTrace()[1].getMethodName();

        System.out.println("class name: " + clazz
                           + " Method Name " + method);

    }

    /**
     * Building Message
     *
     * @param msg
     *            The message you would like logged.
     * @return Message String
     */
    protected static String buildMessage(String msg) {
        StackTraceElement caller = new Throwable().fillInStackTrace().getStackTrace()[2];
        String className =  caller.getClassName();
        String methodName = caller.getMethodName();

        String result = "类名：" + className + "  方法名：" + methodName + "/n" + msg;
        return result;
    }

}
