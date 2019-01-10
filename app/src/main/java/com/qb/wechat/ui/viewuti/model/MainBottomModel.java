package com.qb.wechat.ui.viewuti.model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/9
 * 包    名：com.qb.wechat.ui.viewuti.model
 * 描    述：目录
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class MainBottomModel {
    private String title;//标题
    private boolean haveSide;//是否有横线
    private boolean checked;//是否选中
    private int selectRes;//选中图标
    private int res;//未选中
    private int selectColor;//选中颜色
    private int color;//未选中
    private TextView tv;
    private ImageView imc;

//    public MainBottomModel() {
//        super();
//    }
//
//    public MainBottomModel(String title, boolean checked, int selectRes, int res) {
//        this.title = title;
//        this.checked = checked;
//        this.selectRes = selectRes;
//        this.res = res;
//    }
//
//    public MainBottomModel(String title, boolean checked, int selectRes, int res, int selectColor, int color) {
//        this.title = title;
//        this.checked = checked;
//        this.selectRes = selectRes;
//        this.res = res;
//        this.selectColor = selectColor;
//        this.color = color;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isHaveSide() {
        return haveSide;
    }

    public void setHaveSide(boolean haveSide) {
        this.haveSide = haveSide;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getSelectRes() {
        return selectRes;
    }

    public void setSelectRes(int selectRes) {
        this.selectRes = selectRes;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public int getSelectColor() {
        return selectColor;
    }

    public void setSelectColor(int selectColor) {
        this.selectColor = selectColor;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public TextView getTv() {
        return tv;
    }

    public void setTv(TextView tv) {
        this.tv = tv;
    }

    public ImageView getImc() {
        return imc;
    }

    public void setImc(ImageView imc) {
        this.imc = imc;
    }

    public MainBottomModel(String title, boolean checked, int selectRes, int res, int selectColor, int color, TextView tv, ImageView imc) {

        this.title = title;
        this.checked = checked;
        this.selectRes = selectRes;
        this.res = res;
        this.selectColor = selectColor;
        this.color = color;
        this.tv = tv;
        this.imc = imc;
    }
}
