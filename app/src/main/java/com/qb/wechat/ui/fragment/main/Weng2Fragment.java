package com.qb.wechat.ui.fragment.main;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qb.wechat.R;
import com.qb.wechat.adapter.MsgAdapter;
import com.qb.wechat.ui.activity.ImActivity;
import com.qb.wechat.widget.MainTopBar;
import com.qb.wxbase.app.BaseFragment;
import com.qb.wxbase.app.FoxBaseManagement;
import com.qb.wxbase.create.foxbind.Find;
import com.qb.wxbase.create.foxbus.FxBus;
import com.qb.wxbase.create.foxbus.Improved.FxEventBean;
import com.qb.wxbase.create.speasy.Sp;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/9
 * 包    名：com.qb.wechat.ui.fragment
 * 描    述：嗡嗡页面
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class Weng2Fragment extends BaseFragment implements View.OnClickListener {
    @Find(R.id.recyclerLy)
    private RecyclerView recyclerLy;

    //
    private GridLayoutManager gridLayoutManager;
    private MsgAdapter adapter;

    @Override
    protected int gainViewRes() {
        return R.layout.fragment_weng2;
    }

    @Override
    protected View create(View view) {
        gridLayoutManager = new GridLayoutManager(getContext(),1);
        recyclerLy.setLayoutManager(gridLayoutManager);

        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add("测试"+i);
        }
        adapter = new MsgAdapter(getContext());
        adapter.setAdapterData(strings);
        adapter.setClickListener(this);
        recyclerLy.setAdapter(adapter);
        return view;
    }

    @Override
    public void onClick(View v) {
        FxEventBean<Integer> bean = new FxEventBean<>("ImUserId",adapter.getPositionFromTag(v));
        FxBus.post(bean);
        FoxBaseManagement.getFoxManagement().beginActivity(ImActivity.class);
    }
}
