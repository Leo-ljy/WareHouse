package com.hzmy.zm.warehouse.third_party_libs.fresco;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hzmy.zm.warehouse.third_party_libs.greendao.GreenDaoDemoActivity;
import com.hzmy.zm.warehouse.third_party_libs.other.DownloadManagerDemoActivity;
import com.hzmy.zm.warehouse.third_party_libs.other.LiteCommonDemoActivity;
import com.hzmy.zm.warehouse.third_party_libs.other.MPAndroidChartDemoActivity;
import com.hzmy.zm.warehouse.third_party_libs.other.MaterialStyleDemoActivity;
import com.hzmy.zm.warehouse.third_party_libs.other.RxjavaDemoActivity;
import com.hzmy.zm.warehouse.third_party_libs.volley_gson_okhttp.GsonDemoActivity;
import com.hzmy.zm.warehouse.third_party_libs.volley_gson_okhttp.OkHttpDemoActivity;
import com.hzmy.zm.warehouse.third_party_libs.volley_gson_okhttp.VolleyDemoActivity;

/**
 * 作者：        追梦
 * 邮箱：        1521541979@qq.com
 * 公司：        杭州码友网络科技有限公司
 * 日期：        2016/7/11 10:30
 * 描述：
 */
public class FrescoDemoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    public String[] items = {
                                "SimpleDraweeView 1", "SimpleDraweeView 2", "SimpleDraweeView 3", "   ", "   ",
                             "   ", "   ", "       ", "   ",
                             "   "
                        };

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mContext = this;
        initView();
    }

    private void initView()
    {
        ListView lv = new ListView(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        setContentView(lv);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Intent intent;
        switch (position)
        {
            default:
            case 0:
//
                intent = new Intent(mContext, VolleyDemoActivity.class);
                break;
            case 1:
//                 "okhttp"
                intent = new Intent(mContext, OkHttpDemoActivity.class);
                break;
            case 2:
//                , "Rxjava"
                intent = new Intent(mContext, RxjavaDemoActivity.class);
                break;
            case 3:
//                , "GreenDao",
                intent = new Intent(mContext, GreenDaoDemoActivity.class);
                break;
            case 4:
//                "Fresco",
                intent = new Intent(mContext, FrescoDemoActivity.class);
                break;
            case 5:
//                "Gson",
                intent = new Intent(mContext, GsonDemoActivity.class);
                break;
            case 6:
//                "LiteCommon",
                intent = new Intent(mContext, LiteCommonDemoActivity.class);
                break;
            case 7:
//                Material Style",
                intent = new Intent(mContext, MaterialStyleDemoActivity.class);
                break;
            case 8:
//            "MPAndroidChart",
                intent = new Intent(mContext, MPAndroidChartDemoActivity.class);
                break;
            case 9:
//            "DownloadManager"
                intent = new Intent(mContext, DownloadManagerDemoActivity.class);
                break;

        }
        startActivity(intent);

    }

}
