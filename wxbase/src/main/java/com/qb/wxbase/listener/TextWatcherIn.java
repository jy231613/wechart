package com.qb.wxbase.listener;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：zcapp
 * 日    期：2018/8/24 0024--7:16--星期五
 * 包    名：cn.secret.base.listener
 * 描    述：TextView监听类，默认只需要onTextChanged方法
 * Create by Administrator from AndroidStudio3.1
 * ================================================
 */
public abstract class TextWatcherIn implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
