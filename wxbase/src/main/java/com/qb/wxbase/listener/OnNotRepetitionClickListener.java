package com.qb.wxbase.listener;

import android.view.View;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/9
 * 包    名：com.qb.wxbase.listener
 * 描    述：点击按钮防止重复
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public abstract class OnNotRepetitionClickListener implements View.OnClickListener {
    /**
     * 上一次点击时间
     */
    private long lastClickTime = 0;
    /**
     * 分钟
     */
    private int minute = 1;

    /**
     * 默认构造,默认延时1秒
     */
    public OnNotRepetitionClickListener(){}

    /**
     * 构造
     * @param minute 延时市场/分钟
     */
    public OnNotRepetitionClickListener(int minute){
        this.minute = minute;
    }

    @Override
    public void onClick(View v) {
        long now = System.currentTimeMillis();
        if (now > lastClickTime+(1000*minute)){
            lastClickTime = now;
            onAfterClick(v);
        }
    }

    /**
     * 重复点击之后
     * @param v View
     */
    public abstract void onAfterClick(View v);
}
