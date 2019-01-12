package com.qb.wechat.bean;

import com.qb.wechat.R;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/11
 * 包    名：com.qb.wechat.bean
 * 描    述：用户信息对象
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class UserInfoBean {
    private String username;
    private int uid;

    //自定一
    private int baseRes = R.mipmap.weixin;
    private boolean setSystemInfo = false;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getBaseRes() {
        return baseRes;
    }

    public void setBaseRes(int baseRes) {
        this.baseRes = baseRes;
    }

    public boolean isSetSystemInfo() {
        return setSystemInfo;
    }

    public void setSetSystemInfo(boolean setSystemInfo) {
        this.setSystemInfo = setSystemInfo;
    }

    public UserInfoBean() {
        super();
    }

    public UserInfoBean(String username, int uid, int baseRes, boolean setSystemInfo) {
        this.username = username;
        this.uid = uid;
        this.baseRes = baseRes;
        this.setSystemInfo = setSystemInfo;
    }
}
