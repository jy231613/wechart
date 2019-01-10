package com.qb.wechat.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qb.wechat.R;
import com.qb.wxbase.app.BaseFragment;
import com.qb.wxbase.create.foxbind.Find;
import com.qb.wxbase.listener.OnNotRepetitionClickListener;
import com.qb.wxui.widget.CircularBeadImageView;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/10
 * 包    名：com.qb.wechat.ui.fragment
 * 描    述：我的页面用户信息页
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class MyUserInfoFragment extends BaseFragment {
    @Find(R.id.addClick)
    private ImageView addClick;
    @Find(R.id.toPiLyClick)
    private TextView toPiLyClick;
    @Find(R.id.userNameText)
    private TextView userNameText;
    @Find(R.id.nextMsgText)
    private TextView nextMsgText;
    @Find(R.id.userInfoLy)
    private View userInfoLy;
    @Find(R.id.userInfoTopLy)
    private LinearLayout userInfoTopLy;
    @Find(R.id.zhufuClick)
    private LinearLayout zhufuClick;
    @Find(R.id.shoucangClick)
    private LinearLayout shoucangClick;
    @Find(R.id.xiangceClick)
    private LinearLayout xiangceClick;
    @Find(R.id.kabaoClick)
    private LinearLayout kabaoClick;
    @Find(R.id.biaoqingClick)
    private LinearLayout biaoqingClick;
    @Find(R.id.shezhiClick)
    private LinearLayout shezhiClick;

    private OnDownView onDownView;

    @Override
    protected int gainViewRes() {
        return R.layout.fragment_my;
    }

    @Override
    protected View create(View view) {


        toPiLyClick.setOnClickListener(new OnNotRepetitionClickListener(0) {
            @Override
            public void onAfterClick(View v) {
                if (onDownView!=null)onDownView.downView();
            }
        });
        return view;
    }

    public MyUserInfoFragment.OnDownView getOnDownView() {
        return onDownView;
    }

    public void setOnDownView(MyUserInfoFragment.OnDownView onDownView) {
        this.onDownView = onDownView;
    }

    public interface OnDownView{
        void downView();
    }
}
