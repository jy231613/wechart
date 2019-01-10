package com.qb.wxbase.create.speasy.spbus;


import com.qb.wxbase.create.speasy.base.SharedPreferencesTrait;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：cades
 * 日    期：2018/11/1
 * 包    名：cn.secret.base.created.foxbus
 * 描    述：默认的信息传递存储类
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class EasyDataTrait extends SharedPreferencesTrait {
    private int id;//id
    private int index;//下标,索引
    private String phone;//手机号,或者其他纯数字的字符串
    private String str;//字符串
    private int status;//状态
    private int type;//类型
    private String url;//地址
    private boolean isBool;//一个布尔值
    private double money;//金额,或者其他double类型

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isBool() {
        return isBool;
    }

    public void setBool(boolean bool) {
        isBool = bool;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String getSharedXmlName() {
        return "FoxBusDataTrait";
    }
}
