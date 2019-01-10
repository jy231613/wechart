package com.qb.wxbase.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * ================================================
 * 作    者：贾恒飞
 * 项    目：Ax
 * 日    期：2018/7/31 0031
 * 包    名：cn.secret.base.widget
 * 描    述：自定义RadioGroup,支持多行显示
 * ================================================
 */
public class HineRadioGroup extends RadioGroup{
    private OnCheckedChangeListener mOnCheckedChangeListener;

    public HineRadioGroup(Context context) {
        super(context);
    }

    public HineRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void addView(final View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof LinearLayout) {
            int childCount = ((LinearLayout) child).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View view = ((LinearLayout) child).getChildAt(i);
                if (view instanceof RadioButton) {
                    final RadioButton button = (RadioButton) view;
                    button.setOnTouchListener((v, event) -> {
                        button.setChecked(true);
                        checkRadioButton(button);
                        if (mOnCheckedChangeListener != null) {
                            mOnCheckedChangeListener.onCheckedChanged(HineRadioGroup.this, button.getId());
                        }
                        return true;
                    });
                }
            }
        }

        super.addView(child, index, params);
    }

    private void checkRadioButton(RadioButton radioButton) {
        View child;
        int radioCount = getChildCount();
        for (int i = 0; i < radioCount; i++) {
            child = getChildAt(i);
            if (child instanceof RadioButton) {
                if (child == radioButton) {
                    // do nothing
                } else {
                    ((RadioButton) child).setChecked(false);
                }
            } else if (child instanceof LinearLayout) {
                int childCount = ((LinearLayout) child).getChildCount();
                for (int j = 0; j < childCount; j++) {
                    View view = ((LinearLayout) child).getChildAt(j);
                    if (view instanceof RadioButton) {
                        final RadioButton button = (RadioButton) view;
                        if (button == radioButton) {
                            // do nothing
                        } else {
                            ((RadioButton) button).setChecked(false);
                        }
                    }
                }
            }
        }
    }
}
