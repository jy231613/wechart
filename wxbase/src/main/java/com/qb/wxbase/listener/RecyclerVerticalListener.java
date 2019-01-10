package com.qb.wxbase.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：zcapp
 * 日    期：2018/8/23 0023--6:18--星期四
 * 包    名：cn.secret.base.listener
 * 描    述：RecyclerView上下滑动监听
 * Create by Administrator from AndroidStudio3.1
 * ================================================
 */
public abstract class RecyclerVerticalListener extends RecyclerView.OnScrollListener {
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();//获取LayoutManager
        if (manager != null && manager instanceof LinearLayoutManager) {
            //第一个可见的位置
            int firstPosition = ((LinearLayoutManager) manager).findFirstVisibleItemPosition();
            //如果 dx>0 则表示 右滑 ,dx<0 表示 左滑,dy <0 表示 上滑, dy>0 表示下滑
            if (dy < 0) {
                //上滑监听
                onUpGlide(firstPosition);
            } else {
                //下滑监听
                onDownGlide(firstPosition);
            }
        }
    }

    /**
     * 下滑监听
     *
     * @param firstPosition 第一个可见的位置
     */
    public abstract void onDownGlide(int firstPosition);

    /**
     * 上滑监听
     *
     * @param firstPosition 第一个可见的位置
     */
    public abstract void onUpGlide(int firstPosition);
}
