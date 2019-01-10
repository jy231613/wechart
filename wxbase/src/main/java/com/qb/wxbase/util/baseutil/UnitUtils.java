package com.qb.wxbase.util.baseutil;

import android.content.Context;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：TreeHole
 * 日    期：2018/7/12 0012--11:27--星期四
 * 包    名：cn.secret.base.util
 * 描    述：单位转换类
 * Create by Administrator from AndroidStudio3.1
 * ================================================
 */
public class UnitUtils {
    /**
     * dip转换为px
     * @param context 上下文
     * @param dpValue dp值
     * @return px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转化为dip
     * @param context 上下文
     * @param pxValue px值
     * @return dip
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
