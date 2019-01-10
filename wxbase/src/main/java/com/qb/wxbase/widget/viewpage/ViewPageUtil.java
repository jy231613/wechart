package com.qb.wxbase.widget.viewpage;

import android.support.v4.view.ViewPager;
import android.view.animation.AccelerateInterpolator;

import com.qb.wxbase.widget.viewpage.scroller.FixedSpeedScroller;

import java.lang.reflect.Field;

import static com.qb.wxbase.anim.DefaultTransformer.TAG;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/10
 * 包    名：com.qb.wxbase.widget
 * 描    述：
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class ViewPageUtil {

    /**
     * 设置ViewPage滑动速度
     * @param viewPageZoom ViewPage对象
     * @param time 时长
     */
    public static void setViewPageZoom(ViewPager viewPageZoom,int time){
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(viewPageZoom.getContext(),
                    new AccelerateInterpolator());
            field.set(viewPageZoom, scroller);
            scroller.setmDuration(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
