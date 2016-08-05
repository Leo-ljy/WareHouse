package com.hzmy.zm.warehouse.third_party_libs.volley_gson_okhttp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.hzmy.zm.warehouse.R;
import com.hzmy.zm.warehouse.config.Urls;
import com.hzmy.zm.warehouse.third_party_libs.glide.ImageManager;
import com.hzmy.zm.warehouse.third_party_libs.volley_gson_okhttp.beans.Person;
import com.hzmy.zm.warehouse.third_party_libs.volley_gson_okhttp.manage.VolleyManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：        追梦
 * 邮箱：        1521541979@qq.com
 * 公司：        杭州码友网络科技有限公司
 * 日期：        2016/7/11 10:30
 * 描述：
 */
public class GsonDemoActivity extends AppCompatActivity
{
    @Bind(R.id.textview)
    TextView mTextview;
    @Bind(R.id.imageview)
    ImageView mImageview;
    @Bind(R.id.circleimageview)
    ImageView mCircleimageview;

    public ImageManager imageManager;
    private String TAG = "Gson";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);
        ButterKnife.bind(this);

        getData();
        postData();
        getImage();
        getCircleImage();

    }

    /**
     * 解析单个后台返回的json对象
     */
    private void getData() {
        VolleyManager.newInstance().GsonGetRequest(TAG, Urls.mJsonUrl2, Person.class,
                new Response.Listener<Person>() {
                    @Override
                    public void onResponse(Person person) {
                        Log.v(TAG, "get方法: " + person.toString());
                        mTextview.setText(person.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage(), error);
                    }
                });
    }

    /**
     * 上传数据到后台
     */
    private void postData() {

        /*post方式一：post map参数*/
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", "allen");
        map.put("password", "linqinan");

        VolleyManager.newInstance().GsonPostRequest(TAG, map, Urls.mJsonUrl2, Person.class,
                new Response.Listener<Person>() {
                    @Override
                    public void onResponse(Person person) {
                        Log.v(TAG, "post map参数: " + person.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage(), error);

                    }
                });

        /*post方式一：post json对象*/
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", "allen");
            jsonObject.put("password", "linqinan");
            Log.v(TAG, "本地json打印: " + jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        VolleyManager.newInstance().PostjsonRequest(TAG, Urls.mJsonUrl2, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.v(TAG, "post json对象: " + jsonObject.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage(), error);
                    }
                });

    }

    /**
     * 加载图片
     */
    private void getImage() {
        VolleyManager.newInstance().ImageLoaderRequest
                (mImageview, Urls.mImageUrl, R.mipmap.ic_default, R.mipmap.ic_error);
    }

    /**
     * 加载圆形图片
     */
    private void getCircleImage() {
        imageManager = new ImageManager(this);
        imageManager.loadCircleImage(Urls.mImageUrl, mCircleimageview);
//        VolleyManager.newInstance().ImageRequest(TAG, Urls.mImageUrl,
//                new Response.Listener<Bitmap>() {
//                    @Override
//                    public void onResponse(Bitmap bitmap) {
//                        mCircleimageview.setImageBitmap(bitmap);
//
//                    }
//                }, 0, 0, ImageView.ScaleType.CENTER_INSIDE, null,
//                new Response.ErrorListener() {
//                    public void onErrorResponse(VolleyError error) {
//                        mCircleimageview.setImageResource(R.mipmap.ic_error);
//                    }
//                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        /**
         * 取消请求
         */
        if (VolleyManager.newInstance() != null) {
            VolleyManager.newInstance().cancel(TAG);
        }

    }


}
