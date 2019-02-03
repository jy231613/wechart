package com.qb.wxbase.rxsql.base;

import java.lang.reflect.Method;
import java.util.List;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechart
 * 日    期：2019/1/21
 * 包    名：com.qb.wxbase.rxsql.base
 * 描    述：结果集对象
 * Create by zFox from AndroidStudio3.2
 * ================================================
 */
public class RxResultSet<T> {
    private Method method;
    private List<T> objects;
    private String remake;
    private int keys;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public int getKeys() {
        return keys;
    }

    public void setKeys(int keys) {
        this.keys = keys;
    }
}
