package com.qb.wechat.ui.viewuti.model;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/9
 * 包    名：com.qb.wechat.ui.viewuti.model
 * 描    述：发现页模板类
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class DiscoverModel {
    private String name;
    private boolean isShow;
    private int img;
    private int number;
    private int order;
    private Class toActivity;

    public DiscoverModel(String name, boolean isShow, int img, int number, int order, Class toActivity) {
        this.name = name;
        this.isShow = isShow;
        this.img = img;
        this.number = number;
        this.order = order;
        this.toActivity = toActivity;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Class getToActivity() {
        return toActivity;
    }

    public void setToActivity(Class toActivity) {
        this.toActivity = toActivity;
    }
}
