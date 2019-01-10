package com.qb.wxui.inter;

import android.view.View;

/**
 * Summarize:通用的列表项点击事件
 * User:贾恒飞
 * Date:2018/4/3
 * Time:15:17
 * Email:17515250730@163.com
 * Created with AndroidStudio3.0
 */
public interface CommonOnItemClick {
    /**
     * 列表项点击事件
     * @param view 点击的对象
     * @param type 点击的区域区分
     * @param obj 当前数据对象
     */
    void onItemClick(View view, EnumUtils.ItemClick type, Object obj);
}