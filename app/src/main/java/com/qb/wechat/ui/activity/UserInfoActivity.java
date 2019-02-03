package com.qb.wechat.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qb.wechat.R;
import com.qb.wxbase.app.BaseActivity;
import com.qb.wxbase.create.foxbind.Find;
import com.qb.wxui.widget.CircularBeadImageView;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/12
 * 包    名：com.qb.wechat.ui.activity
 * 描    述：用户详情信息页面
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class UserInfoActivity extends BaseActivity {
    @Find(R.id.leftTitle)TextView leftTitle;
    @Find(R.id.leftClick)ImageView leftClick;
    @Find(R.id.searchClick)ImageView searchClick;
    @Find(R.id.addClick)ImageView addClick;
    @Find(R.id.nickNameText)TextView nickNameText;
    @Find(R.id.chartNText)TextView chartNText;
    @Find(R.id.userPic)CircularBeadImageView userPic;

    @Override
    protected boolean isOpenSwipeBack() {
        return true;
    }

    @Override
    protected boolean isShowTopBar() {
        return true;
    }

    @Override
    protected int gainContentView() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void create() {
        leftTitle.setText("个人信息");
        leftClick.setVisibility(View.VISIBLE);
        addClick.setVisibility(View.GONE);
        searchClick.setVisibility(View.GONE);
    }
}
