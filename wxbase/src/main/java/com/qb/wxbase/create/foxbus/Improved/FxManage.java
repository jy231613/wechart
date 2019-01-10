package com.qb.wxbase.create.foxbus.Improved;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：lovey
 * 日    期：2019/1/5
 * 包    名：cn.secret.base.created.foxbus.Improved
 * 描    述：Fx管理类
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class FxManage {
    public static int cache = 10;//缓存数据数

    private Map<String, FxEventBean> map;//当前缓存的数据
    private List<String> tags;//当前标记列表

    private static FxManage fxManage;
    private FxManage (){
        map = new HashMap<>();
        tags = new ArrayList<>();
    }
    public static FxManage getFxManage(){
        if (fxManage == null) fxManage = new FxManage();
        return fxManage;
    }

    /**
     * 增加
     * @param bean 对象
     */
    public void add(FxEventBean bean){
        if (bean!=null) save(bean.getKey(),bean);
    }

    public FxEventBean get(String key) {
        return map.get(key);
    }

    /**
     * 缓存数据并判断是否该清理过期数据
     */
    private void save(String tag, FxEventBean t){
        if (tags.size()==cache){
            map.remove(tags.get(0));
            tags.remove(0);//缓存十个数据
        }
        tags.add(tag);
        map.put(tag,t);
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
     *
     */
    public void clear(){
        cache = 10;
        map.clear();
        tags.clear();
    }
}
