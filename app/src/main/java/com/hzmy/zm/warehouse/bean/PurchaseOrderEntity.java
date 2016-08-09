package com.hzmy.zm.warehouse.bean;

import java.util.List;

//进货单
public class PurchaseOrderEntity
{
    //订单id
    private long purchaseId;
    //订单编号
    private String purchaseOrderNum;

    //-------------发货单位------------

    //发货单位名称
    private String deliverCompanyName;
    //发货单位地址
    private String deliverCompanyAddress;
    //发货单位电话
    private String deliverCompanyTelephone;
    //发货单位传真
    private String deliverCompanyFax;

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
    //收货仓库
    private String orderWarehourse;
    //--------------商品列表-----------
    //商品列表
    private List<GoodsEntity> goodsEntityList;

    //备注
    private String remarkContent;


}
