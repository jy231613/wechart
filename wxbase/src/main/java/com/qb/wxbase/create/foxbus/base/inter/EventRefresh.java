package com.qb.wxbase.create.foxbus.base.inter;

import com.qb.wxbase.create.foxbus.base.FoxEvent;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：cades
 * 日    期：2018/11/1
 * 包    名：cn.secret.base.created.foxbus.base
 * 描    述：事件刷新
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public interface EventRefresh {
    void receptionEvent(FoxEvent event, boolean isRefresh);
}
