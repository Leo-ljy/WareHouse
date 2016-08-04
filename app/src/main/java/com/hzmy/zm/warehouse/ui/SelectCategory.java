package com.hzmy.zm.warehouse.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hzmy.zm.warehouse.R;
import com.hzmy.zm.warehouse.config.IntentData;
import com.hzmy.zm.warehouse.utils.LogUtils;
import com.hzmy.zm.warehouse.utils.MethodsCompat;
import com.hzmy.zm.warehouse.widgets.toolbar.ToolBarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述：                                                               <br>
 * 作者：        追梦                                                <br>
 * 邮箱：        1521541979@qq.com                        <br>
 * 公司：        杭州码友网络科技有限公司             <br>
 * 日期：        2016/7/29 16:49                               <br>
 */
public class SelectCategory extends ToolBarActivity implements AdapterView.OnItemClickListener
{
    private static String TAG = "SearchActivity";
    private Context mContext;

    @Bind(R.id.lv_select_category)
    ListView lvSelectCategory;
    private String[] catagoryData;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView()
    {
        this.mContext = this;
    }

    private void initData()
    {
        catagoryData = new String[]{"喷码机","打码机"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, catagoryData);
        lvSelectCategory.setAdapter(adapter);

        lvSelectCategory.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        String categoryName = catagoryData[position];

        Intent intent=new Intent(mContext,SearchActivity.class);
        Bundle myBundle = new Bundle();
        myBundle.putString(IntentData.catagoryName , categoryName);
        intent.putExtra(IntentData.defaultBundle, myBundle);

        setResult(Activity.RESULT_OK, intent);
        //默认 RESULT_OK=-1;
        finish();//结束当前的activity，返回上一个activity

    }
}
