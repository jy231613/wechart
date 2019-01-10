package com.qb.wechat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.qb.wechat.R;
import com.qb.wxbase.listener.OnNotRepetitionClickListener;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/9
 * 包    名：com.qb.wechat.widget
 * 描    述：MainTopBar
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class MainTopBar extends FrameLayout {
    private View root;
    private TextView textView;

    public MainTopBar(Context context) {
        super(context);
        init(context);
    }

    public MainTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        root = LayoutInflater.from(context).inflate(R.layout.layout_top_main, this);
        textView = root.findViewById(R.id.leftTitle);
        ImageView searchClick = root.findViewById(R.id.searchClick);
        ImageView addClick = root.findViewById(R.id.addClick);
        searchClick.setOnClickListener(new OnNotRepetitionClickListener() {
            @Override
            public void onAfterClick(View v) {
            }
        });
        addClick.setOnClickListener(new OnNotRepetitionClickListener() {
            @Override
            public void onAfterClick(View v) {
            }
        });
    }

    public void setText(String str) {
        textView.setText(str);
    }
}
