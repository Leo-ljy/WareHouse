package com.hzmy.zm.warehouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hzmy.zm.warehouse.R;

/**
 * 描述：                                                               <br>
 * 作者：        追梦                                                <br>
 * 邮箱：        1521541979@qq.com                        <br>
 * 公司：        杭州码友网络科技有限公司             <br>
 * 日期：        2016/7/13 12:30                               <br>
 */

public class GridHomeAdapter extends BaseAdapter
{
    private Context context;

    public GridHomeAdapter(Context context)
    {
        this.context = context;
    }

    private Integer[] images = {
            R.mipmap.home_grid_view_item1,
            R.mipmap.home_grid_view_item2,
            R.mipmap.home_grid_view_item3,
            R.mipmap.home_grid_view_item4,
            R.mipmap.home_grid_view_item5,
            R.mipmap.home_grid_view_item6,
            R.mipmap.home_grid_view_item7,
            R.mipmap.home_grid_view_item8,
            R.mipmap.home_grid_view_item9,
            };

    private String[] texts = {
            "货物管理",
            "同事",
            "出库",
            "入库",
            "历史记录",
            "统计",
            "物流",
            "入门教程",
            ""
    };

    public int getCount()
    {
        return images.length;
    }

    public Object getItem(int position)
    {
        return position;
    }

    public long getItemId(int position)
    {
        return position;
    }

    static class ViewHolder
    {
        private ImageView imageView;
        private TextView textView;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder wrapper;
        if (convertView == null)
        {
            wrapper = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_grid_home, null);
            convertView.setTag(wrapper);
            convertView.setPadding(15, 15, 15, 15);
        } else
        {
            wrapper = (ViewHolder) convertView.getTag();
        }
        wrapper.imageView = (ImageView) convertView.findViewById(R.id.device_setting_item_image);
        wrapper.imageView.setBackgroundResource(images[position]);
        wrapper.textView = (TextView) convertView.findViewById(R.id.device_setting_item_text);
        wrapper.textView.setText(texts[position]);
        return convertView;
    }


}

