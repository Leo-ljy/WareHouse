package com.hzmy.zm.warehouse.utils;


import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 
 * @author       追梦
 * @email        1521541979@qq.com
 * @description  html文件的下载，保存方法集合
 */
public class HtmlUtil {

	
	
	/**
	 * 获取HTML内容
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String getHtml(String path) throws Exception {
		InputStream inStream = getInputStream(path);//通过输入流获取html数据
		byte[] data = readInputStream(inStream);//得到html的二进制数据
		String html = new String(data, "gb2312");
		return html;
	}
	
	public static InputStream getInputStream2(String url) throws IOException, URISyntaxException{  
        URI u = new URI(url);  
        DefaultHttpClient httpclient = new DefaultHttpClient();   
        HttpGet httpget = new HttpGet(u);     
        ResponseHandler<String> responseHandler = new BasicResponseHandler();     
        String content = httpclient.execute(httpget, responseHandler); 
        
        content = new String(content.getBytes("UTF-8"),"UTF-8"); 
        InputStream   result   =   new   ByteArrayInputStream(content.getBytes("UTF-8"));   
        
//        content = new String(content.getBytes("ISO-8859-1"),"UTF-8"); 
//        InputStream   result   =   new   ByteArrayInputStream(content.getBytes("UTF-8"));   
     
//        content = new String(content.getBytes("ISO-8859-1"),"GBK");
//        InputStream   result   =   new   ByteArrayInputStream(content.getBytes("GBK")); 
        
        System.out.println("开始下载到的HTML内容：" + content);
        return result;       
    }  
	
	
	
	/**
	 * 获取html的inputStream
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static InputStream getInputStream(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5 * 1000);
		InputStream inStream = conn.getInputStream();//通过输入流获取html数据
		return inStream;
	}
	
	 
	
	/**
	 * 得到html的二进制数据
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while( (len=inStream.read(buffer)) != -1 ){
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
	
	/**
	 * 保存HTML文件
	 * @param context
	 * @param in
	 * @throws IOException
	 * 在你的res目录下，新建一个目录： raw，然后将mobiles.html文件放到 raw 目录下。
	 * 这段代码会将你的index.html文件存储到手机的目录：/data/data/(项目包名)/mobiles.html   
	 */
//	public static void saveFile(Context context, InputStream in) throws IOException{
//
//        int lenght = in.available();
//        //创建byte数组
//        byte[]  buffer = new byte[lenght];
//        //将文件中的数据读到byte数组中
//        in.read(buffer);
//        //取得HTML的文件名
////        SharedPreferences sharedPreferences = context.getSharedPreferences("GT250", Context.MODE_PRIVATE);
//        String htmlFileName = AppContext.getCurrentHtmlFileName();
//
//        //内部储存
//        FileOutputStream outStream = context.openFileOutput(htmlFileName, Context.MODE_PRIVATE+Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE);
////        可以方便地再手机中创建文件，并返回文件输出流，用于对文件做写入操作。
////        第一个参数，代表文件名称，注意这里的文件名称不能包括任何的/或者/这种分隔符，只能是文件名
////        该文件会被保存在/data/data/应用名称/files/
////        第二个参数，代表文件的操作模式
////        *             MODE_PRIVATE 私有（只能创建它的应用访问） 重复写入时会文件覆盖
////        *             MODE_APPEND  私有   重复写入时会在文件的末尾进行追加，而不是覆盖掉原来的文件
////        *             MODE_WORLD_READABLE 公用  可读
////        *             MODE_WORLD_WRITEABLE 公用 可读写
//
//        outStream.write(buffer);
//        outStream.close();
//        Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
//    }
	
}
