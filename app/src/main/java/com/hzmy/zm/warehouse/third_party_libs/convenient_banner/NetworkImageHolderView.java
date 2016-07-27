package com.hzmy.zm.warehouse.third_party_libs.convenient_banner;

import android.content.Context;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Sai on 15/8/4.
 * 网络图片加载例子
 */
public class NetworkImageHolderView implements Holder<String>
{
    private SimpleDraweeView simpleDraweeView;

    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        Fresco.initialize(context);
        simpleDraweeView = new SimpleDraweeView(context);
        return simpleDraweeView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        simpleDraweeView.setImageURI(data);
    }
}
