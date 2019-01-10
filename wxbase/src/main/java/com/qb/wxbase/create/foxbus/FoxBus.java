package com.qb.wxbase.create.foxbus;

import android.content.Intent;

import com.qb.wxbase.json.Json;
import com.qb.wxbase.util.baseutil.StrUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Summarize:FoxBus用于传递一些对象信息
 *  实现方式有两种
 *      ①:使用json的形式,转化对象为字符串,利用Intent传递
 *      ②:使用map集合存储key,value,把对象缓存到内存中,不宜太大;
 * UserJson:jhf
 * Date:2018/3/29
 * Time:13:10
 * Email:17515250730@163.com
 * Created with AndroidStudio3.0
 */

public class FoxBus {
    private static final String rxBus = "rxBus";//Intent传递时的默认字符串
    private static int cache = 10;//缓存数据数

    private String tag = rxBus;//标记,标记当前数据

    private Map<String,Object> map = new HashMap<>();//当前缓存的数据

    private List<String> tags = new ArrayList<>();//当前标记列表

    private static volatile FoxBus defaultInstance;
    private FoxBus() {}
    // 单例RxBus
    public static FoxBus getDefault() {
        if (defaultInstance == null) {
            synchronized (FoxBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new FoxBus();
                }
            }
        }
        return defaultInstance;
    }

    /**
     * 发送一个对象--json传递
     * @param intent Intent对象
     * @param t 传递的对象
     * @param <T> 对象类型--接收时的对象需要一致
     */
    public <T> void post(Intent intent, T t){
        tag = StrUtil.uuid();
        intent.putExtra(rxBus, Json.toJson(t));
    }

    /**
     * 发送(缓存)一个对象
     * @param t 对象
     * @param <T> 对象类型
     */
    public <T> void post(T t){
        tag = StrUtil.uuid();
        save(tag,t);
    }

    /**
     * 发送(缓存)一个对象
     * @param t 对象
     * @param <T> 对象类型
     */
    public <T> void postAndSetTag(T t,String tag){
        this.tag = tag;
        save(tag,t);
    }

    /**
     * 获取一个发送的对象(内部默认使用uuid做了tag)
     * @param intent Intent对象
     * @return 对象的json字符串
     */
    public String gainData(Intent intent){
        return intent.getStringExtra(rxBus);
    }

    /**
     * 获取一个缓存的对象
     * @return 默认当前tag对应的对象
     */
    public Object gainObj(){
        return map.get(tag);
    }

    /**
     * 获取一个缓存的对象
     * @param tag tag标记
     * @return tag对应的对象
     */
    public Object gainObj(String tag){
        return map.get(tag);
    }

    /**
     * 获取一个tag对象
     * @return 当前tag
     */
    public String gainTag(){
        return tag;
    }

    /**
     * 根据tag销毁一条数据
     * @param tag tag
     */
    public void destroy(String tag){
        if (map.get(tag)!=null){
            map.remove(tag);
            tags.remove(tag);
        }
    }

    /**
     * 缓存数据并判断是否该清理过期数据
     * @param tag
     * @param t
     */
    private void save(String tag,Object t){
        if (tags.size()==cache){
            map.remove(tags.get(0));
            tags.remove(0);//缓存十个数据
        }
        tags.add(tag);
        map.put(tag,t);
    }

    /**
     * 清除缓存的数据
     * @param isInit 是否初始化
     */
    public void clear(boolean isInit){
        tags.clear();
        map.clear();
        if (isInit){
            cache = 10;
            tag = rxBus;
        }
    }

}
