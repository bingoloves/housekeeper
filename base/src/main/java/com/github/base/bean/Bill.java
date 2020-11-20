package com.github.base.bean;

import org.litepal.crud.LitePalSupport;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by bingo on 2020/11/20.
 *
 * @Author: bingo
 * @Email: 657952166@qq.com
 * @Description: 账单类
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/11/20
 */

public class Bill extends LitePalSupport {
    /**
     * id
     */
    private int id;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品单价
     */
    private float goodsPrice;
    /**
     * 商品数量 如：斤
     */
    private float goodsNum;
    /**
     * 消费地名称
     */
    private String consumePlaceName;
    /**
     * 图片数据
     */
    private byte[] cover;
    /**
     * 时间
     */
    private Date date;
    /**
     * 类别
     */
    private int category;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 描述性信息
     */
    private String desc;
    /**
     * 地址位置
     */
    private String address;
    /**
     * 经度
     */
    private double longitude;
    /**
     * 纬度
     */
    private double latitude;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public float getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(float goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getConsumePlaceName() {
        return consumePlaceName;
    }

    public void setConsumePlaceName(String consumePlaceName) {
        this.consumePlaceName = consumePlaceName;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsNum=" + goodsNum +
                ", consumePlaceName='" + consumePlaceName + '\'' +
                ", cover=" + Arrays.toString(cover) +
                ", date=" + date +
                ", category=" + category +
                ", remarks='" + remarks + '\'' +
                ", desc='" + desc + '\'' +
                ", address='" + address + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
