package com.hzmy.zm.warehouse.utils;


/**
 * @author       追梦
 * @email        1521541979@qq.com
 * @description  
 */
public class TimeToolUtils
{
	/**
	 * @author       追梦
	 * @email        1521541979@qq.com
	 * @description  获取当前时间戳
	 * @return       
	 */  
	public static  long getCurrentTimeStamp(){
		return  System.currentTimeMillis();  
	}
	
	
	/**
	 * @author       追梦
	 * @email        1521541979@qq.com
	 * @description  获取几秒后的时间戳
	 * @param curTimeStamp  当前时间戳
	 * @param afterTime		几秒后
	 * @return       
	 */  
	public static long getAfterSomeTime(long curTimeStamp, int afterTime)
	{
		 long timeStamp = curTimeStamp + afterTime* 1000;
		 return timeStamp;
	}
}
