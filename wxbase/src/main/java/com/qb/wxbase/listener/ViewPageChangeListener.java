package com.qb.wxbase.listener;

import android.support.v4.view.ViewPager;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：zcapp
 * 日    期：2018/8/25 0025--3:32--星期六
 * 包    名：cn.secret.base.listener
 * 描    述：ViewPager切换监听
 * Create by Administrator from AndroidStudio3.1
 * ================================================
 */
public class ViewPageChangeListener implements ViewPager.OnPageChangeListener {
    private OnSelect onSelect;

    public ViewPageChangeListener(OnSelect onSelect){this.onSelect = onSelect;}

    /**
     *当前页面被滚动时，这个方法将被调用，或者作为一部分以编程方式启动的平滑滚动或用户发起的触摸滚动。
     * @param position 当前正在显示的第一个页面的位置索引。
                        如果位置偏移是非零的，页面位置+1将是可见的。
     * @param positionOffset 值从0到1）表示位置的偏移量。
     * @param positionOffsetPixels 以像素为单位的值表示位置的偏移量。
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    /**
     *这个方法将在新页面被选中时被调用。动画不是一定完成。
     * @param position 新选择页面的位置索引。
     */
    @Override
    public void onPageSelected(int position) {
        onSelect.onSelect(position);
    }

    /**
     *当滚动状态改变时调用。用于发现用户何时开始拖动，当寻呼机自动地固定到当前页面时，或者当它完全停止/闲置时。
     * @param state 新的滚动状态。
     * @see ViewPager#SCROLL_STATE_IDLE 表明寻呼机处于闲置状态。当前页面完全在视图中，没有动画在进行中。
     * @see ViewPager#SCROLL_STATE_DRAGGING 表明寻呼机目前正被用户拖拽。
     * @see ViewPager#SCROLL_STATE_SETTLING 表明寻呼机正处于最终位置的过程中。
     */
    @Override
    public void onPageScrollStateChanged(int state) {
    }


    public interface OnSelect{
        void onSelect(int position);
    }
}
