package com.hzmy.zm.warehouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hzmy.zm.warehouse.R;
import com.hzmy.zm.warehouse.bean.Goods;
import com.hzmy.zm.warehouse.utils.LogUtils;

import java.util.List;

/**
 * 描述：                                                               <br>
 * 作者：        追梦                                                <br>
 * 邮箱：        1521541979@qq.com                        <br>
 * 公司：        杭州码友网络科技有限公司             <br>
 * 日期：        2016/7/28 14:18                               <br>
 */
public class ListViewGoodsAdapter extends BaseAdapter
{
    private static final java.lang.String TAG ="ListViewGoodsAdapter" ;
    private List<Goods> lists;
    private Context mContext;
    private LayoutInflater  holderView;
    private int itemRescource;

    public final class ListItemView{
        public SimpleDraweeView fresco_goods_item;
        public TextView tv_goods_name ;
        public TextView tv_goods_number ;
        public TextView tv_goods_unit_name ;
        public TextView tv_goods_category_name;
        public TextView tv_goods_type_name;
    }


    public   ListViewGoodsAdapter(Context context, List<Goods> lists)
    {
        this.mContext = context;
        this.lists = lists;
        this.itemRescource = R.layout.item_list_view_goods;
        this.holderView = LayoutInflater.from(context);
    }


    @Override
    public int getCount()
    {
        return lists.size();
    }

    @Override
    public Object getItem(int position)
    {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ListItemView listItemView = null;
        if (convertView == null)
        {
            listItemView = new ListItemView();
            convertView = holderView.inflate(itemRescource, null);
            listItemView.fresco_goods_item = (SimpleDraweeView) convertView.findViewById(R.id.fresco_goods_item);
            listItemView.tv_goods_category_name = (TextView) convertView.findViewById(R.id.tv_goods_category_name);
            listItemView.tv_goods_name = (TextView) convertView.findViewById(R.id.tv_goods_name);
            listItemView.tv_goods_number = (TextView) convertView.findViewById(R.id.tv_goods_number);
            listItemView.tv_goods_unit_name = (TextView) convertView.findViewById(R.id.tv_goods_unit_name);
            listItemView.tv_goods_type_name = (TextView) convertView.findViewById(R.id.tv_goods_type_name);




            convertView.setTag(listItemView);

        }else{
            listItemView = (ListItemView) convertView.getTag();
        }

        //赋值
        Goods temp = lists.get(position);
        listItemView.fresco_goods_item.setImageURI(temp.getImageUrl());
        listItemView.tv_goods_category_name.setText(temp.getCategoryName());
        listItemView.tv_goods_name.setText(temp.getGoodsName());
        listItemView.tv_goods_number.setText(String.valueOf(temp.getNumber()));
        listItemView.tv_goods_unit_name.setText(temp.getUnitName());
        listItemView.tv_goods_type_name.setText(temp.getTypeName());

        LogUtils.d("适配中： goodName: " + temp.getGoodsName());


        return convertView;
    }
}
