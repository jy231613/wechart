package com.qb.wxbase.json;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：TreeHole
 * 日    期：2018/7/9 0009--12:45--星期一
 * 包    名：cn.secret.base.json
 * 描    述：本项目json解析
 * ================================================
 */
public class ApiJson<T> {
    public String code;
    public String msg;
    public T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
