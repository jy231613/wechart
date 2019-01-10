package com.qb.wxbase.json;

/**
 * Summarize:本项目服务器返回json解析
 * UserJson:贾恒飞
 * Date:2018/3/31
 * Time:1:03
 * Email:17515250730@163.com
 * Created with AndroidStudio3.0
 */

public class RequestJson {
    public String code;
    public String msg;
    public String data;

    public static RequestJson json(String json){
        return Json.obj(json,RequestJson.class);
    }

    public RequestJson() {
        super();
    }

    public RequestJson(String code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
