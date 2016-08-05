
package com.hzmy.zm.warehouse.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hzmy.zm.warehouse.R;
import com.hzmy.zm.warehouse.adapter.drop_down.BaseListAdapter;
import com.hzmy.zm.warehouse.bean.Goods;
import com.hzmy.zm.warehouse.third_party_libs.glide.ImageManager;
import com.hzmy.zm.warehouse.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：                                                               <br>
 * 作者：        追梦                                                <br>
 * 邮箱：        1521541979@qq.com                        <br>
 * 公司：        杭州码友网络科技有限公司             <br>
 * 日期：        2016/8/4 15:30                               <br>
 */
public class ListViewGoodsAdapter extends BaseListAdapter<Goods>
{
    private static final java.lang.String TAG ="ListViewGoodsAdapter" ;
    private int itemRescource;

    private boolean isNoData;
    private int mHeight;
    public static final int ONE_SCREEN_COUNT = 7; // 一屏能显示的个数，这个根据屏幕高度和各自的需求定
    public static final int ONE_REQUEST_COUNT = 10; // 一次请求的个数
    private ImageManager imageManager;

    public final class ListItemView{
        public ImageView fresco_goods_item;
        public TextView tv_goods_name ;
        public TextView tv_goods_number ;
        public TextView tv_goods_unit_name ;
        public TextView tv_goods_category_name;
        public TextView tv_goods_type_name;
    }

    public ListViewGoodsAdapter(Context context)
    {
        super(context);
        init();
    }

    public ListViewGoodsAdapter(Context context, List<Goods> list)
    {
        super(context, list);
        init();
    }

    private void init()
    {
        this.itemRescource = R.layout.item_list_view_goods;
        imageManager = new ImageManager(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ListItemView listItemView = null;
        if (convertView == null)
        {
            listItemView = new ListItemView();

            convertView = mInflater.inflate(itemRescource, null);
            listItemView.fresco_goods_item = (ImageView) convertView.findViewById(R.id.fresco_goods_item);
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
        Goods temp = getItem(position);
//        listItemView.fresco_goods_item.setImageURI(temp.getImageUrl());
        imageManager.loadUrlImage(temp.getImageUrl(), listItemView.fresco_goods_item, R.mipmap.ic_place_img);
        listItemView.tv_goods_category_name.setText(temp.getCategoryName());
        listItemView.tv_goods_name.setText(temp.getGoodsName());
        listItemView.tv_goods_number.setText(String.valueOf(temp.getNumber()));
        listItemView.tv_goods_unit_name.setText(temp.getUnitName());
        listItemView.tv_goods_type_name.setText(temp.getTypeName());

        LogUtils.d("适配中： goodName: " + temp.getGoodsName());
        return convertView;
    }

    // 设置数据
    public void setData(List<Goods> list) {
        clearAll();
        addALL(list);

        if (list.size() == 1 ) {
            // 暂无数据布局
//            mHeight = list.get(0).getHeight();
        } else {
            // 添加空数据
            if (list.size() < ONE_SCREEN_COUNT) {
                addALL(createEmptyList(ONE_SCREEN_COUNT - list.size()));
            }
        }
        notifyDataSetChanged();
    }

    // 创建不满一屏的空数据
    public List<Goods> createEmptyList(int size) {
        List<Goods> emptyList = new ArrayList<>();
        if (size <= 0) return emptyList;
        for (int i=0; i<size; i++) {
            emptyList.add(new Goods());
        }
        return emptyList;
    }
    
    
}




//  原版

//package com.hzmy.zm.warehouse.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.facebook.drawee.view.SimpleDraweeView;
//import com.hzmy.zm.warehouse.R;
//import com.hzmy.zm.warehouse.bean.Goods;
//import com.hzmy.zm.warehouse.bean.drop_down.Goods;
//import com.hzmy.zm.warehouse.utils.LogUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 描述：                                                               <br>
// * 作者：        追梦                                                <br>
// * 邮箱：        1521541979@qq.com                        <br>
// * 公司：        杭州码友网络科技有限公司             <br>
// * 日期：        2016/7/28 14:18                               <br>
// */
//public class ListViewGoodsAdapter extends BaseAdapter
//{
//    private static final java.lang.String TAG ="ListViewGoodsAdapter" ;
//    private List<Goods> mList;
//    private Context mContext;
//    private LayoutInflater  holderView;
//    private int itemRescource;
//
//    public final class ListItemView{
//        public SimpleDraweeView fresco_goods_item;
//        public TextView tv_goods_name ;
//        public TextView tv_goods_number ;
//        public TextView tv_goods_unit_name ;
//        public TextView tv_goods_category_name;
//        public TextView tv_goods_type_name;
//    }
//
//
//    public   ListViewGoodsAdapter(Context context, List<Goods> lists)
//    {
//        this.mContext = context;
//        this.mList = lists;
//        this.itemRescource = R.layout.item_list_view_goods;
//        this.holderView = LayoutInflater.from(context);
//    }
//
//
//    @Override
//    public int getCount()
//    {
//        return mList.size();
//    }
//
//    @Override
//    public Object getItem(int position)
//    {
//        return mList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position)
//    {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent)
//    {
//        ListItemView listItemView = null;
//        if (convertView == null)
//        {
//            listItemView = new ListItemView();
//            convertView = holderView.inflate(itemRescource, null);
//            listItemView.fresco_goods_item = (SimpleDraweeView) convertView.findViewById(R.id.fresco_goods_item);
//            listItemView.tv_goods_category_name = (TextView) convertView.findViewById(R.id.tv_goods_category_name);
//            listItemView.tv_goods_name = (TextView) convertView.findViewById(R.id.tv_goods_name);
//            listItemView.tv_goods_number = (TextView) convertView.findViewById(R.id.tv_goods_number);
//            listItemView.tv_goods_unit_name = (TextView) convertView.findViewById(R.id.tv_goods_unit_name);
//            listItemView.tv_goods_type_name = (TextView) convertView.findViewById(R.id.tv_goods_type_name);
//
//
//
//
//            convertView.setTag(listItemView);
//
//        }else{
//            listItemView = (ListItemView) convertView.getTag();
//        }
//
//        //赋值
//        Goods temp = mList.get(position);
//        listItemView.fresco_goods_item.setImageURI(temp.getImageUrl());
//        listItemView.tv_goods_category_name.setText(temp.getCategoryName());
//        listItemView.tv_goods_name.setText(temp.getGoodsName());
//        listItemView.tv_goods_number.setText(String.valueOf(temp.getNumber()));
//        listItemView.tv_goods_unit_name.setText(temp.getUnitName());
//        listItemView.tv_goods_type_name.setText(temp.getTypeName());
//
//        LogUtils.d("适配中： goodName: " + temp.getGoodsName());
//
//
//        return convertView;
//    }
//
//    public void addALL(List<Goods> lists){
//        if(lists==null||lists.size()==0){
//            return ;
//        }
//        mList.addAll(lists);
//    }
//
//    // 设置数据
//    public void setData(List<Goods> list) {
//        list.clear();
//        addALL(list);
//
////        isNoData = false;
////        if (list.size() == 1 && list.get(0).isNoData()) {
////            // 暂无数据布局
////            isNoData = list.get(0).isNoData();
////            mHeight = list.get(0).getHeight();
////        } else {
////            // 添加空数据
////            if (list.size() < ONE_SCREEN_COUNT) {
////                addALL(createEmptyList(ONE_SCREEN_COUNT - list.size()));
////            }
////        }
//        notifyDataSetChanged();
//    }
//
//    // 创建不满一屏的空数据
//    public List<Goods> createEmptyList(int size) {
//        List<Goods> emptyList = new ArrayList<>();
//        if (size <= 0) return emptyList;
//        for (int i=0; i<size; i++) {
//            emptyList.add(new Goods());
//        }
//        return emptyList;
//    }
//}
