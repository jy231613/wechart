package com.qb.wechat.ui.fragment.main;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.qb.wechat.R;
import com.qb.wechat.ui.fragment.MyShootVideoFragment;
import com.qb.wechat.ui.fragment.MyUserInfoFragment;
import com.qb.wxbase.adapter.FragAdapter;
import com.qb.wxbase.app.BaseFragment;
import com.qb.wxbase.create.foxbind.Find;
import com.qb.wxbase.listener.ViewPageChangeListener;
import com.qb.wxbase.widget.viewpage.VerticalViewPager;
import com.qb.wxbase.widget.viewpage.ViewPageUtil;
import com.qb.wxui.widget.CircularBeadImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/9
 * 包    名：com.qb.wechat.ui.fragment
 * 描    述：我的页面基础页
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class MyFragment extends BaseFragment {
    @Find(R.id.verticalViewPage)
    private VerticalViewPager verticalViewPager;
    @Find(R.id.circularImageView)
    private CircularBeadImageView circularImageView;
    @Find(R.id.topLy)
    private LinearLayout topLy;

    private OnDownOrUpListener onDownOrUpListener;

    private List<Fragment> fragments;
    private MyShootVideoFragment myShootVideoFragment;
    private MyUserInfoFragment myUserInfoFragment;

    @Override
    protected int gainViewRes() {
        return R.layout.fragment_my_base;
    }

    @Override
    protected void init(View view) {
        super.init(view);
        myShootVideoFragment = new MyShootVideoFragment();
        myUserInfoFragment = new MyUserInfoFragment();
        fragments = new ArrayList<>();
        fragments.add(myShootVideoFragment);
        fragments.add(myUserInfoFragment);
    }

    @Override
    protected View create(View view) {
        //初始化布局ViewPage
        FragAdapter adapter = new FragAdapter(getChildFragmentManager(),fragments);
        verticalViewPager.setAdapter(adapter);
        verticalViewPager.addOnPageChangeListener(new ViewPageChangeListener(this::selectPasge));
        selectPasge(1);//初始化位置
        ViewPageUtil.setViewPageZoom(verticalViewPager,500);

        //设置子页面监听
        myUserInfoFragment.setOnDownView(() -> {
            selectPasge(0);
        });
        myShootVideoFragment.setOnDownView(()->{
            selectPasge(1);
        });
        return view;
    }

    /**
     * 当前索引页
     */
    private int nowIndex = 0;
    /**
     * 选择页面
     * @param index 页面索引
     */
    private void selectPasge(int index){
        if (index != nowIndex){
            nowIndex = index;
            verticalViewPager.setCurrentItem(index);
            if (nowIndex == 0){
                topLy.setVisibility(View.GONE);
                onDownOrUpListener.onDown();
            }else{
                topLy.setVisibility(View.VISIBLE);
                onDownOrUpListener.onUp();
            }
        }
    }

    public OnDownOrUpListener getOnDownOrUpListener() {
        return onDownOrUpListener;
    }

    public void setOnDownOrUpListener(OnDownOrUpListener onDownOrUpListener) {
        this.onDownOrUpListener = onDownOrUpListener;
    }

    public interface OnDownOrUpListener{
        void onDown();
        void onUp();
    }
}
