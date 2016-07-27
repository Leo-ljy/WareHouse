package com.hzmy.zm.warehouse.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * 字符串操作工具包
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class StringUtilsTools 
{
	private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	//private final static SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//private final static SimpleDateFormat dateFormater2 = new SimpleDateFormat("yyyy-MM-dd");
	
	private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};
	
	/**
	 * 将字符串转位日期类型
	 * @param sdate
	 * @return
	 */
	public static Date toDate(String sdate) {
		try {
			return dateFormater.get().parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 以友好的方式显示时间
	 * @param sdate
	 * @return
	 */
	public static String friendly_time(String sdate) {
		String re_StrTime = "";
		long lcc_time= toLong(sdate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 例如：cc_time=1291778220	
		re_StrTime = sdf.format(new Date(lcc_time * 1000L));		
		Date time = toDate(re_StrTime);
		if(time == null) {
			return "Unknown";
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();
		
		//判断是否是同�?�?
		String curDate = dateFormater2.get().format(cal.getTime());
		String paramDate = dateFormater2.get().format(time);
		if(curDate.equals(paramDate)){
			int hour = (int)((cal.getTimeInMillis() - time.getTime())/3600000);
			if(hour == 0)
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000,1)+"分钟�?";
			else 
				ftime = hour+"小时�?";
			return ftime;
		}
		
		long lt = time.getTime()/86400000;
		long ct = cal.getTimeInMillis()/86400000;
		int days = (int)(ct - lt);		
		if(days == 0){
			int hour = (int)((cal.getTimeInMillis() - time.getTime())/3600000);
			if(hour == 0)
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000,1)+"分钟�?";
			else 
				ftime = hour+"小时�?";
		}
		else if(days == 1){
			ftime = "昨天";
		}
		else if(days == 2){
			ftime = "前天";
		}
		else if(days > 2 && days <= 10){ 
			ftime = days+"天前";			
		}
		else if(days > 10){			
			ftime = dateFormater2.get().format(time);
		}
		return ftime;
	}
	
	/**
	 * 判断给定字符串时间是否为今日
	 * @param sdate
	 * @return boolean
	 */
	public static boolean isToday(String sdate){
		boolean b = false;
		Date time = toDate(sdate);
		Date today = new Date();
		if(time != null){
			String nowDate = dateFormater2.get().format(today);
			String timeDate = dateFormater2.get().format(time);
			if(nowDate.equals(timeDate)){
				b = true;
			}
		}
		return b;
	}
	
	/**
	 * 判断给定字符串是否空白串
	 * 空白串是指由空格、制表符、回车符、换行符组成的字符串
	 * 若输入字符串为null或空字符串，返回true
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty( String input ) 
	{
		if ( input == null || "".equals( input ) )
			return true;
		
		for ( int i = 0; i < input.length(); i++ ) 
		{
			char c = input.charAt( i );
			if ( c != ' ' && c != '\t' && c != '\r' && c != '\n' )
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是不是一个合法的电子邮件地址
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email){
		if(email == null || email.trim().length()==0) 
			return false;
	    return emailer.matcher(email).matches();
	}
	/**
	 * 字符串转整数
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try{
			return Integer.parseInt(str);
		}catch(Exception e){}
		return defValue;
	}
	/**
	 * 对象转整�?
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static int toInt(Object obj) {
		if(obj==null) return 0;
		return toInt(obj.toString(),0);
	}
	/**
	 * 对象转整�?
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static long toLong(String obj) {
		try{
			return Long.parseLong(obj);
		}catch(Exception e){}
		return 0;
	}
	/**
	 * 字符串转布尔�?
	 * @param b
	 * @return 转换异常返回 false
	 */
	public static boolean toBool(String b) {
		try{
			return Boolean.parseBoolean(b);
		}catch(Exception e){}
		return false;
	}
	public static String setString(String str,int len) {  
	    int strlen = 0;  
	    String s = "";  
	    for (int i = 0; i < str.length(); i++) {  
	        if (str.charAt(i) > 128) {  
	            strlen += 2;  
	        } else {  
	            strlen++;  
	        }  
	        s += str.charAt(i);  
	        if (strlen >= len) {  
	            return s+"...";  
	        }  
	    }  
	    return s;  
	}  
	
	/**
	 * @author 追梦
	 * @email 1521541979@qq.com
	 * @description 判断list<String[]> 是否包含一个特定的 string[] <br/>
	 *              如果返回 true，则包含，否则不包含
	 */
	public static boolean isListContainsStringArr(List<String[]> list, String[] stringArr)
	{
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i)[0].equals(stringArr[0]) && list.get(i)[1].equals(stringArr[1])) { return true; }
			}
			return false;
	}
	
	/**
	 * 正则表达式,从路由器的URL中解析stok
	 */
	public static String parseUrl(String url){
		Pattern p = Pattern.compile("stok=[A-Za-z0-9]*");  
        Matcher m = p.matcher(url);  
        if(m.find()){  
            return m.group();
        }else {
			return null;
		}
	}
	
	
	/**
	 * @author       追梦
	 * @email        1521541979@qq.com
	 * @description  解析Unicode
	 * @param theString
	 * @return       
	 */  
	public static String decodeUnicode(String theString) {      
		   
	    char aChar;      
	   
	     int len = theString.length();      
	   
	    StringBuffer outBuffer = new StringBuffer(len);      
	   
	    for (int x = 0; x < len;) {      
	   
	     aChar = theString.charAt(x++);      
	   
	     if (aChar == '\\') {      
	   
	      aChar = theString.charAt(x++);      
	   
	      if (aChar == 'u') {      
	   
	       // Read the xxxx      
	   
	       int value = 0;      
	   
	       for (int i = 0; i < 4; i++) {      
	   
	        aChar = theString.charAt(x++);      
	   
	        switch (aChar) {      
	   
	        case '0':      
	   
	        case '1':      
	   
	        case '2':      
	   
	        case '3':      
	   
	       case '4':      
	   
	        case '5':      
	   
	         case '6':      
	          case '7':      
	          case '8':      
	          case '9':      
	           value = (value << 4) + aChar - '0';      
	           break;      
	          case 'a':      
	          case 'b':      
	          case 'c':      
	          case 'd':      
	          case 'e':      
	          case 'f':      
	           value = (value << 4) + 10 + aChar - 'a';      
	          break;      
	          case 'A':      
	          case 'B':      
	          case 'C':      
	          case 'D':      
	          case 'E':      
	          case 'F':      
	           value = (value << 4) + 10 + aChar - 'A';      
	           break;      
	          default:      
	           throw new IllegalArgumentException(      
	             "Malformed   \\uxxxx   encoding.");      
	          }      
	   
	        }      
	         outBuffer.append((char) value);      
	        } else {      
	         if (aChar == 't')      
	          aChar = '\t';      
	         else if (aChar == 'r')      
	          aChar = '\r';      
	   
	         else if (aChar == 'n')      
	   
	          aChar = '\n';      
	   
	         else if (aChar == 'f')      
	   
	          aChar = '\f';      
	   
	         outBuffer.append(aChar);      
	   
	        }      
	   
	       } else     
	   
	       outBuffer.append(aChar);      
	      }      
	   
	      return outBuffer.toString();      
	   
	     }    
	
	public static String reverse(String s) {
		  int length = s.length();
		  String reverse = "";
		  for (int i = 0; i < length; i++)
		   reverse = s.charAt(i) + reverse;
		  return reverse;
		 }
	
	
	/**
	 * @description  判断字符串有没有包含中文
	 * @author       追梦
	 * @email        1521541979@qq.com 
	 * @param str
	 * @return       
	 */  
	public static boolean checkIsChina(String str)
	{
		String reg = "[\\u4e00-\\u9fa5]+";// 表示+表示一个或多个中文，
		if (str.matches(reg))
		{
			return true;
		}
		else
		{
			return false;

		}
	}
}
