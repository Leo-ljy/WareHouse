package com.hzmy.zm.warehouse.utils;

import android.content.Context;
import android.content.Intent;

/**
 * 描述：                                                               <br>
 * 作者：        追梦                                                <br>
 * 邮箱：        1521541979@qq.com                        <br>
 * 公司：        杭州码友网络科技有限公司             <br>
 * 日期：        2016/7/15 10:02                               <br>
 */
public class ActivityUtils
{


    /**
     * 说明：	      跳转到wifi设置页面                                             <br>
     * 作者：         追梦                                     <br>
     * 邮箱：        1521541979@qq.com              <br>
     * 公司：        杭州码友网络科技有限公司   <br>
     * 日期：        2016/7/15 10:03                        <br>
     */
    public static void gotoWiFiSettingActivity(Context context)
    {
        Intent i = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(i);
    }
}
