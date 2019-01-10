package cn.secret.base.created.foxbus.base;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：cades
 * 日    期：2018/11/1
 * 包    名：cn.secret.base.created.foxbus.base
 * 描    述：封装事件信息对象
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class EventBean<T> {
    private String key;
    private T bean;

    public EventBean() {
        super();
    }

    public EventBean(String key, T bean) {
        this.key = key;
        this.bean = bean;
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
}
