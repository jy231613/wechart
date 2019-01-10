package com.qb.wxbase.anim;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 自定义动画
 * 默认用于ViewPage滑动效果
 */
public class DefaultTransformer implements ViewPager.PageTransformer {
    public static final String TAG = "simple";

    @Override
    public void transformPage(@NonNull View view, float position) {
        float alpha = 0;
        if (0 <= position && position <= 1) {
            alpha = 1 - position;
        } else if (-1 < position && position < 0) {
            alpha = position + 1;
        }
        view.setAlpha(alpha);
        float transX = view.getWidth() * -position;
        view.setTranslationX(transX);
        float transY = position * view.getHeight();
        view.setTranslationY(transY);
    }
}