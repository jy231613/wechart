package com.qb.wxui.dialog.util;

import android.app.Dialog;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：cn.fox.ui.dialog.util
 * 日    期：2018/9/3
 * 包    名：zcapp
 * 描    述：无操作的dialog监听器
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class DontDialogClickListener implements MsgDialogClickListener {
    private Dialog dialog;
    public DontDialogClickListener(Dialog dialog){this.dialog=dialog;}
    @Override
    public void doYes() {
        if (dialog!=null)dialog.dismiss();
    }
    @Override
    public void doNo() {
        if (dialog!=null)dialog.dismiss();
    }
}
