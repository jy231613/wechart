package com.qb.wxui.dialog.util;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：cn.fox.ui.dialog.util
 * 日    期：2018/9/20
 * 包    名：onese
 * 描    述：列表项点击事件
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class RecyclerBean {
    private String listName;
    private Bind.OnclickListener bindClick;

    public RecyclerBean(String listName, Bind.OnclickListener bindClick) {
        this.listName = listName;
        this.bindClick = bindClick;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Bind.OnclickListener getBindClick() {
        return bindClick;
    }

    public void setBindClick(Bind.OnclickListener bindClick) {
        this.bindClick = bindClick;
    }
}
