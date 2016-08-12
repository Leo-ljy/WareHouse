package com.hzmy.zm.warehouse.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.gc.materialdesign.views.ButtonRectangle;
import com.hzmy.zm.warehouse.R;
import com.hzmy.zm.warehouse.constant.IntentData;
import com.hzmy.zm.warehouse.utils.ToastUtils;
import com.hzmy.zm.warehouse.widgets.toolbar.ToolBarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述：                                                               <br>
 * 作者：        追梦                                                <br>
 * 邮箱：        1521541979@qq.com                        <br>
 * 公司：        杭州码友网络科技有限公司             <br>
 * 日期：        2016/7/29 15:01                               <br>
 */
public class SearchActivity extends ToolBarActivity implements Toolbar.OnMenuItemClickListener, View.OnClickListener, View.OnTouchListener
{
    private  static  String TAG = "SearchActivity";

    /**
     * catagory请求码
     */
    private static  final   int CATAGORYREQUESTCODE = 1;
    /**
     * type请求码
     */
    private static final int TYPEREQUESTCODE = 2;


    @Bind(R.id.btn_search)
    ButtonRectangle btnSearch;
    @Bind(R.id.et_goods_category)
    EditText etGoodsCategory;
    @Bind(R.id.et_goods_type)
    EditText etGoodsType;
    @Bind(R.id.et_goods_name)
    EditText etGoodsName;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    private void initView()
    {
        this.mContext = this;
        toolbar.setOnMenuItemClickListener(this);

        etGoodsCategory.setOnTouchListener(this);
        etGoodsName.setOnTouchListener(this);
        etGoodsType.setOnTouchListener(this);
        btnSearch.setOnClickListener(this);
    }

    private void initData()
    {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar_search, menu);
        return true;
    }


    @Override
    public boolean onMenuItemClick(MenuItem item)
    {
        ToastUtils.show(mContext, "清空");
        return false;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_search:
                ToastUtils.show(mContext, "搜索");
                break;

        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {

        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            Intent i ;
            switch (v.getId())
            {
                case R.id.et_goods_category:

                    i = new Intent(mContext, SelectCategory.class);
                    startActivityForResult(i,CATAGORYREQUESTCODE);
                    break;
                case R.id.et_goods_type:
                    i = new Intent(mContext, SelectCategory.class);
                    startActivityForResult(i,TYPEREQUESTCODE);


                    break;
                case R.id.et_goods_name:
                    ToastUtils.show(mContext, "名称");
                    break;
            }

        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundleDate;
        String nameStr;
        if (resultCode == -1)
        {
            switch (requestCode)
            {
                case CATAGORYREQUESTCODE:
                    bundleDate=data.getBundleExtra(IntentData.defaultBundle);
                    nameStr=bundleDate.getString(IntentData.catagoryName, getString(R.string.device));
                    etGoodsCategory.setText(nameStr);
                    break;
                case TYPEREQUESTCODE:
                    bundleDate=data.getBundleExtra(IntentData.defaultBundle);
                    nameStr=bundleDate.getString(IntentData.catagoryName, getString(R.string.device));
                    etGoodsType.setText(nameStr);
                    break;
            }
        }


    }
}
