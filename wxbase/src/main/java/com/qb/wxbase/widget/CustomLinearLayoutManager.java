package com.qb.wxbase.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Summarize:屏蔽RecyclerView滑动效果的布局的管理器
 * User:贾恒飞
 * Date:2018/5/10
 * Time:15:36
 * Email:17515250730@163.com
 * Created with AndroidStudio3.0
 */
public class CustomLinearLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnabled = true;

    public CustomLinearLayoutManager(Context context) {
        super(context);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }
}
