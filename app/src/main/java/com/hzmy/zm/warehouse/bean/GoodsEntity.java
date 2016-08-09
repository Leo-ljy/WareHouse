package com.hzmy.zm.warehouse.bean;

//商品
public class GoodsEntity
{
    private  long goodsId;
    //商品图片
    private String imageUrl ;
    //类别
    private long categoryId;
    //型号
    private long typeId;
    //商品数量
    private long number;
    //商品名
    private String goodsName;
    //数量单位名称
    private String unitName;
    //类别名称
    private String categoryName;
    //型号名称
    private String typeName;
    //重量
    private int weight;
    //备注
    private String remarkContent;

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public long getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(long categoryId)
    {
        this.categoryId = categoryId;
    }

    public long getTypeId()
    {
        return typeId;
    }

    public void setTypeId(long typeId)
    {
        this.typeId = typeId;
    }

    public long getNumber()
    {
        return number;
    }

    public void setNumber(long number)
    {
        this.number = number;
    }

    public String getGoodsName()
    {
        return goodsName;
    }

    public void setGoodsName(String goodsName)
    {
        this.goodsName = goodsName;
    }

    public String getUnitName()
    {
        return unitName;
    }

    public void setUnitName(String unitName)
    {
        this.unitName = unitName;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }
}
