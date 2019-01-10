package com.qb.wxui.dialog.util;

import android.text.InputType;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：cn.fox.ui.dialog.util
 * 日    期：2018/9/10
 * 包    名：zcapp
 * 描    述：类型
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public enum InputTypeEnum {
    input_text(InputType.TYPE_CLASS_TEXT),
    input_number(InputType.TYPE_CLASS_NUMBER),
    input_password(InputType.TYPE_TEXT_VARIATION_PASSWORD),;

    int typeClassText;
    InputTypeEnum(int typeClassText) {
        this.typeClassText = typeClassText;
    }
    public int getTypeClassText() {
        return typeClassText;
    }
}
