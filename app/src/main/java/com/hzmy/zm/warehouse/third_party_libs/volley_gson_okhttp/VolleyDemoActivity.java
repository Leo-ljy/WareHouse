package com.hzmy.zm.warehouse.third_party_libs.volley_gson_okhttp;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：        追梦
 * 邮箱：        1521541979@qq.com
 * 公司：        杭州码友网络科技有限公司
 * 日期：        2016/7/11 10:28
 * 描述：        volley常用方法
 */
public class VolleyDemoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    String[] items = {
            "StringRequest",
            "StringRequest带参数",
            "JsonRequest",
            "ImageRequest",
            "ImageLoader",
            "NetworkImageView",
            "自定义 XML解析",
            "自定义GsonRequest"
    };

    Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mContext = this;

        initView();
    }


    private void initView()
    {
        ListView lv = new ListView(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, items);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        setContentView(lv);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        switch (position)
        {
            default:
            case 0:
//                StringRequest
                stringRequestDemo();
                break;
            case 1:
//                  "StringRequest带参数",
                stringRequestWithParamsDemo();
                break;

            case 2:
//                JsonRequest
                jsonRequestDemo();
                break;
            case 3:
//                ImageRequest
                imageRequestDemo();
                break;
            case 4:
//                ImageLoader
                break;
            case 5:
//                NetworkImageView
                break;
            case 6:
//                自定义 XML解析
                break;
            case 7:
//                自定义GsonRequest
                break;
        }
    }


    /**
     * 说明：	       stringRequestDemo  不带参数                       <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/7/11 13:07                        <br>
     */
    private void stringRequestDemo()
    {
        RequestQueue mQueue = Volley.newRequestQueue(mContext);
        String url = "http://www.baidu.com";
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Log.d("TAG", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("TAG", error.getMessage(), error);
                    }
                });

        mQueue.add(stringRequest);
    }


    /**
     * 说明：	      StringRequest带参数     post方法                                        <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/7/11 13:12                        <br>
     */
    private void stringRequestWithParamsDemo()
    {
        RequestQueue mQueue = Volley.newRequestQueue(mContext);
        String url = "http://www.baidu.com";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Log.d("TAG", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("TAG", error.getMessage(), error);
                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> map = new HashMap<String, String>();
                map.put("params1", "value1");
                map.put("params2", "value2");
                return map;
            }
        };

        mQueue.add(stringRequest);

    }


    /**
     * 说明：	       jsonRequest                             <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/7/11 13:21                        <br>
     */
    private void jsonRequestDemo()
    {
        RequestQueue mQueue = Volley.newRequestQueue(mContext);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://m.weather.com.cn/data/101010100.html", null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Log.d("TAG", response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("TAG", error.getMessage(), error);
                    }
                });
        mQueue.add(jsonObjectRequest);
    }

    private void imageRequestDemo()
    {
        RequestQueue mQueue = Volley.newRequestQueue(mContext);
        String imgUrl = "http://developer.android.com/images/home/aw_dac.png";
        ImageRequest imageRequest = new ImageRequest
                (
                        imgUrl,
                        new Response.Listener<Bitmap>()
                        {
                            @Override
                            public void onResponse(Bitmap response)
                            {
                                Log.d("TAG", "加载图片成功");
                            }
                        }
                        , 0
                        , 0
                        , Bitmap.Config.RGB_565
                        ,
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
//                              imageView.setImageResource(R.drawable.default_image);
                                Log.d("TAG", error.getMessage(), error);
                            }
                        }
                );
        mQueue.add(imageRequest);
    }


}
