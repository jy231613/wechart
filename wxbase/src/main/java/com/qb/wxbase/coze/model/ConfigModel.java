package com.qb.wxbase.coze.model;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechart
 * 日    期：2019/1/21
 * 包    名：com.qb.wxbase.coze.model
 * 描    述：配置信息类
 * Create by zFox from AndroidStudio3.2
 * ================================================
 */
public class ConfigModel {
    public static String configName = "fox_coze_config.xml";

    private String ipConfig;
    private int portConfig;
    private boolean cache;
    private String phoneName;
    private String myIp;

    public String getMyIp() {
        return myIp;
    }

    public void setMyIp(String myIp) {
        this.myIp = myIp;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getIpConfig() {
        return ipConfig;
    }

    public void setIpConfig(String ipConfig) {
        this.ipConfig = ipConfig;
    }

    public int getPortConfig() {
        return portConfig;
    }

    public void setPortConfig(int portConfig) {
        this.portConfig = portConfig;
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }
}
