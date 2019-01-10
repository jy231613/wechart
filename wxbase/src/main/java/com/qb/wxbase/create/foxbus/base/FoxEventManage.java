package com.qb.wxbase.create.foxbus.base;

import java.util.Vector;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：cades
 * 日    期：2018/11/1
 * 包    名：cn.secret.base.created.foxbus.base
 * 描    述：事件管理类
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class FoxEventManage {
    Vector<FoxEvent> events = new Vector<FoxEvent>();

    public void addEvent(FoxEvent event){
        events.add(event);
    }

    public void delEvent(FoxEvent event){
        events.remove(event);
    }

    public void pushEvent(FoxEvent event){
        for (FoxEvent eventFor:events) {
            
        }
    }

}
