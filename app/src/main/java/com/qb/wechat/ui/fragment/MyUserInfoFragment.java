package com.qb.wechat.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qb.wechat.R;
import com.qb.wechat.aax.UserSelf;
import com.qb.wechat.ui.activity.UserInfoActivity;
import com.qb.wxbase.app.BaseFragment;
import com.qb.wxbase.app.FoxBaseManagement;
import com.qb.wxbase.create.foxbind.Find;
import com.qb.wxbase.create.speasy.base.SpGet;
import com.qb.wxbase.listener.OnNotRepetitionClickListener;
import com.qb.wxbase.util.easyutil.GlideUtils;
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
    @Find(R.id.addClick)ImageView addClick;
    @Find(R.id.circularImageView)CircularBeadImageView circularImageView;
    @Find(R.id.toPiLyClick)TextView toPiLyClick;
    @Find(R.id.userNameText)TextView userNameText;
    @Find(R.id.nextMsgText)TextView nextMsgText;
    @Find(R.id.userInfoLy)View userInfoLy;
    @Find(R.id.userInfoTopLy)LinearLayout userInfoTopLy;
    @Find(R.id.zhufuClick)LinearLayout zhufuClick;
    @Find(R.id.shoucangClick)LinearLayout shoucangClick;
    @Find(R.id.xiangceClick)LinearLayout xiangceClick;
    @Find(R.id.kabaoClick)LinearLayout kabaoClick;
    @Find(R.id.biaoqingClick)LinearLayout biaoqingClick;
    @Find(R.id.shezhiClick)LinearLayout shezhiClick;

    private OnDownView onDownView;

    @SpGet
    private UserSelf userSelf;

    @Override
    protected int gainViewRes() {
        return R.layout.fragment_my;
    }

    @Override
    protected View create(View view) {
        //设置数据
        refreshUserInfo(userSelf);

        //设置监听
        toPiLyClick.setOnClickListener(new OnNotRepetitionClickListener(0) {
            @Override
            public void onAfterClick(View v) {
                if (onDownView!=null)onDownView.downView();
            }
        });
        return view;
    }

    /**
     * 刷新当前页信息
     * @param self 信息对象
     */
    public void refreshUserInfo(UserSelf self){
        if (self==null)return;
        //设置头像
        if (self.getUserpic().equals(""))circularImageView.setImageResource(R.mipmap.default_nor_avatar);
        else GlideUtils.load(getContext(),self.getUserpic(),circularImageView);
        userNameText.setText(self.getUsername());
        nextMsgText.setText(getResources().getString(R.string.my_info_text_chartN).replace("%d",self.getChartn()));
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
