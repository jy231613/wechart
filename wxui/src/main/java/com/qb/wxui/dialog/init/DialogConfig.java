package com.qb.wxui.dialog.init;

import com.qb.wxui.dialog.style.DialogStyleClassify;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：cn.fox.ui.dialog.init
 * 日    期：2018/9/17
 * 包    名：onese
 * 描    述：对话框配置模型类,涵盖了一些Dialog的默认属性设置
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class DialogConfig {
    private DialogStyleClassify dialogStyleClassify;

    /**
     * 设置Dialog的风格样式
     * @param dialogStyleClassify DialogStyleClassify
     * @return DialogConfig
     */
    public DialogConfig setDialogStyleClassify(DialogStyleClassify dialogStyleClassify) {
        this.dialogStyleClassify = dialogStyleClassify;
        return this;
    }

    /**
     * 获取Dialog的风格样式
     * @return DialogStyleClassify
     */
    public DialogStyleClassify getDialogStyleClassify() {
        return dialogStyleClassify;
    }
}
