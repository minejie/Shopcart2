package com.bwie.test.shopcart;

/**
 * Date：2017/6/29
 * author: 曹政杰Administrator.
 * function：
 */

public class GoodEntity {
    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int imgId;
    private String goodsName;



    private String price;
    private int count;
    private boolean isCheck;


    public GoodEntity(int imgId, String goodsName,String price ,int count, boolean isCheck) {
        this.imgId = imgId;
        this.goodsName = goodsName;
        this.count = count;
        this.isCheck = isCheck;
        this.price = price;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
