package com.qb.wxui.dialog.util;

import android.app.Dialog;

/**
 * Summarize:QMUI对话框按钮点击监听
 * User:贾恒飞
 * Date:2018/5/10
 * Time:9:14
 * Email:17515250730@163.com
 * Created with AndroidStudio3.0
 */

public interface MsgDialogClickListener {
    /**
     * 点击确定选项
     */
    void doYes();
    /**
     * 点击取消操作
     */
    void doNo();
}
