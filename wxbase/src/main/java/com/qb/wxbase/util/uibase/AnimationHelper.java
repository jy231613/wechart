package com.qb.wxbase.util.uibase;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Summarize:动画帮助类
 * User:贾恒飞
 * Date:2018/5/10
 * Time:16:00
 * Email:17515250730@163.com
 * Created with AndroidStudio3.0
 */

public class AnimationHelper {
    //layout.startAnimation(mShowAction);//开始动画

    /**
     * 控件显示动画
     * @return
     */
    public static TranslateAnimation visibleAnim(){
        // 显示动画
        TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, -0.0f);
        mShowAction.setRepeatMode(Animation.REVERSE);
        mShowAction.setDuration(500);
        return mShowAction;
    }

    /**
     * 控件隐藏动画
     * @return
     */
    public static TranslateAnimation goneAnim(){
        // 隐藏动画
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f);
        mHiddenAction.setDuration(500);
        return mHiddenAction;
    }

}
