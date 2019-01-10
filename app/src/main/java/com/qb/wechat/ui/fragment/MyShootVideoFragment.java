package com.qb.wechat.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qb.wechat.R;
import com.qb.wxbase.app.BaseFragment;
import com.qb.wxbase.create.foxbind.Find;
import com.qb.wxbase.listener.OnNotRepetitionClickListener;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/10
 * 包    名：com.qb.wechat.ui.fragment
 * 描    述：拍视频动态页面
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class MyShootVideoFragment extends BaseFragment {
    @Find(R.id.toPiLyClick)
    private LinearLayout toPiLyClick;
    @Find(R.id.bgClick)
    private ImageView bgClick;

    private OnDownView onDownView;

    @Override
    protected int gainViewRes() {
        return R.layout.fragment_my_shoot_video;
    }

    @Override
    protected View create(View view) {

        bgClick.setOnClickListener(new OnNotRepetitionClickListener(0) {
            @Override
            public void onAfterClick(View v) {
                if (onDownView!=null)onDownView.downView();
            }
        });
        return view;
    }

    public MyShootVideoFragment.OnDownView getOnDownView() {
        return onDownView;
    }

    public void setOnDownView(MyShootVideoFragment.OnDownView onDownView) {
        this.onDownView = onDownView;
    }

    public interface OnDownView{
        void downView();
    }
}
