package com.qb.wxbase.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Summarize:Json数据解析类
 * UserJson:贾恒飞
 * Date:2018/3/31
 * Time:2:08
 * Email:17515250730@163.com
 * Created with AndroidStudio3.0
 */

public class Json {
    /**
     * 解析JsonObject数据
     *
     * @param jsonString
     *            Json格式字符串
     * @param cls
     *            封装类
     *
     */
    public static <T> T obj(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = JSON.parseObject(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 解析JsonArray数据
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> List<T> array(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            list = JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 解析JsonArray数据，返回Map类型的List
     *
     * @param jsonString
     * @return
     */
    public static List<Map<String, Object>> arrayMap(String jsonString) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = JSON.parseObject(jsonString,new TypeReference<List<Map<String, Object>>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 解析JsonArray数据，返回Map类型的List
     *
     * @param jsonString
     * @return
     */
    public static Map<String, Object> map(String jsonString) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = JSON.parseObject(jsonString,new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 解析JsonArray数据，返回Map类型
     *
     * @param jsonString
     * @return
     */
    public static Map<String, Integer> mapInt(String jsonString) {
        Map<String, Integer> map = new HashMap<>();
        try {
            map = JSON.parseObject(jsonString,new TypeReference<Map<String, Integer>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 解析JsonArray数据，返回Map类型的List
     *
     * @param jsonString
     * @return
     */
    public static List<Map<String, Integer>> arrayMapInt(String jsonString) {
        List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
        try {
            list = JSON.parseObject(jsonString,new TypeReference<List<Map<String, Integer>>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 解析JsonArray数据，返回Map类型的List
     *
     * @param jsonString
     * @return
     */
    public static List<Map<String, Double>> arrayMapDouble(String jsonString) {
        List<Map<String, Double>> list = new ArrayList<Map<String, Double>>();
        try {
            list = JSON.parseObject(jsonString,new TypeReference<List<Map<String, Double>>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 对象解析成Json数据
     * @param t 对象
     * @param <T> 类型
     * @return json字符串
     */
    public static <T> String toJson(T t){
        String json = "";
        try {
            json = JSON.toJSONString(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
