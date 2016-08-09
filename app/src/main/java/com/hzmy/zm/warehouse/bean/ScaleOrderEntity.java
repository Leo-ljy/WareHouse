package com.hzmy.zm.warehouse.bean;

import java.util.List;

//发货单
public class ScaleOrderEntity
{
    //订单id
    private long scaleId;
    //订单编号
    private String scaleNum;

    //-------------发货单位------------

    //发货单位名称
    private String deliverCompanyName;
    //发货单位地址
    private String deliverCompanyAddress;
    //发货单位电话
    private String deliverCompanyTelephone;
    //发货单位传真
    private String deliverCompanyFax;
    //发货仓库
    private String deliverWarehourse;
    //发货日期
    private String deliverDate;
    //发货人
    private String deliverName;
    //业务员
    private String scalesName;
    //快递员
    private String expresserName;
    //-------------收货单位-------------
    //收货单位名称
    private String orderCompanyName;
    //收货单位地址
    private String orderCompanyAddress;
    //收货单位收件人
    private String orderCompanyContacts;
    //收货单位电话
    private String orderCompanyTelphone;
    //--------------商品列表-----------
    //商品列表
    private List<GoodsEntity> goodsEntityList;

    //备注
    private String remarkContent;


}
