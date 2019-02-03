package com.qb.wxbase.okhttp.base;

import android.util.Log;

import com.qb.wxbase.json.Json;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/10
 * 包    名：com.qb.wxbase.okhttp.base
 * 描    述：HttpPost参数传递构建类
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class HttpPostParameterBuilder {
    private Map<String,Object> map;
    public HttpPostParameterBuilder(){
        map = new HashMap<>();
    }

    /**
     * 新增
     * @param key 键
     * @param value 值
     */
    public void add(String key,Object value){
        map.put(key,value);
    }

    /**
     * 删除
     * @param key 键
     */
    public void del(String key){
        map.remove(key);
    }

    /**
     * 清空对象
     */
    public void clear(){
        map.clear();
    }

    /**
     * 构建对象
     * @return RequestBody
     */
    private static final MediaType JSON = MediaType.get("application/json");
    public RequestBody builder(){
        Log.d("net", "builder: "+Json.toJson(map));
        return RequestBody.create(JSON, Json.toJson(map));
    }

}
