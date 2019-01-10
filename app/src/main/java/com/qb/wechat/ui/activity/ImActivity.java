package com.qb.wechat.ui.activity;

import com.qb.wechat.R;
import com.qb.wxbase.app.BaseActivity;
import com.qb.wxbase.create.foxbus.Improved.FxEventBean;
import com.qb.wxbase.create.foxbus.Improved.base.FxGet;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/10
 * 包    名：com.qb.wechat.ui.activity
 * 描    述：实时聊天Activity
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class ImActivity extends BaseActivity{

    @FxGet("ImUserId")
    private FxEventBean<Integer> userIdBean;

    @Override
    protected int gainContentView() {
        return R.layout.activity_im;
    }

    @Override
    protected void create() {
        showToast(String.valueOf(userIdBean.getBean()));
    }

    @Override
    protected boolean isOpenSwipeBack() {
        return true;
    }
}
