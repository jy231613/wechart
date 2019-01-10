package com.qb.wxui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.qb.wxbase.util.apkutil.SystemUtils;
import com.qb.wxui.widget.listener.OnViewLongListener;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：cn.fox.ui.widget
 * 日    期：2018/9/26
 * 包    名：onese
 * 描    述：带有长按复制功能的文本框
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class CTextView extends android.support.v7.widget.AppCompatTextView implements View.OnLongClickListener {
    private OnViewLongListener onViewLongListener;

    public CTextView(Context context) {
        super(context);
        init(context);
    }

    public CTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     * @param context 上下文
     */
    private void init(Context context) {
        setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View v) {
        SystemUtils.copyFor(getContext(),getText().toString());
        Toast.makeText(getContext(), "已复制", Toast.LENGTH_SHORT).show();
        //如果设置了组件长按监听续,就会调用这个方法onLong.
        if (onViewLongListener !=null) onViewLongListener.onLong();
        return true;
    }
}
