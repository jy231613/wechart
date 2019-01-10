package com.qb.wxbase.create.foxbus.Improved;

import com.qb.wxbase.create.foxbus.Improved.base.Condition;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：lovey
 * 日    期：2019/1/5
 * 包    名：cn.secret.base.created.foxbus.Improved
 * 描    述：时间传递封装类
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class FxEventBean<T> {
    private String key;
    private T bean;
    private boolean isCondition;//事件是否传递
    private Condition condition;//事件传递条件

    /**
     * 初始化构造
     * @param bean 对象
     */
    public FxEventBean(String key,T bean) {
        this.key = key;
        this.bean = bean;
        isCondition = true;
        condition = () -> true;
    }

    /**
     * 初始化构造
     * @param bean 对象
     * @param condition 事件传递条件
     */
    public FxEventBean(String key,T bean, Condition condition) {
        this.key = key;
        this.bean = bean;
        this.condition = condition;
        if (condition!=null)isCondition = condition.condition();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }

    public boolean isCondition() {
        return isCondition;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
        if (condition!=null)isCondition = condition.condition();
    }
}
