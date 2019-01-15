package com.qb.wxbase.create.sql.model;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/14
 * 包    名：com.qb.wxbase.create.sql.model
 * 描    述：配置信息类
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class ConfigModel {
    public final static String configName = "fox_sql_config.xml";

    private String scanName = "";
    private String jsonPath = "";
    private int version = 1;
    private boolean catchOpen = false;

    public String getScanName() {
        return scanName;
    }

    public void setScanName(String scanName) {
        this.scanName = scanName;
    }

    public boolean isCatchOpen() {
        return catchOpen;
    }

    public static String getConfigName() {
        return configName;
    }

    public String getJsonPath() {
        return jsonPath;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setJsonPath(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public void setCatchOpen(boolean catchOpen) {
        this.catchOpen = catchOpen;
    }

    @Override
    public String toString() {
        return "ConfigModel{" +
                "scanName='" + scanName + '\'' +
                ", catchOpen=" + catchOpen +
                '}';
    }
}
