package com.qb.wechat.bean;

import java.util.List;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/11
 * 包    名：com.qb.wechat.bean
 * 描    述：联系人分组模型
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class BaseAddressBean {
    private String topName;
    private List<UserInfoBean> beans;

    public String getTopName() {
        return topName;
    }

    public void setTopName(String topName) {
        this.topName = topName;
    }

    public List<UserInfoBean> getBeans() {
        return beans;
    }

    public void setBeans(List<UserInfoBean> beans) {
        this.beans = beans;
    }
}
