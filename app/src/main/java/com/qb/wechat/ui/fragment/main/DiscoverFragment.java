package com.qb.wechat.ui.fragment.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qb.wechat.R;
import com.qb.wechat.adapter.DiscoverAdapter;
import com.qb.wechat.ui.viewuti.DiscoverMenu;
import com.qb.wechat.widget.MainTopBar;
import com.qb.wxbase.app.BaseFragment;
import com.qb.wxbase.create.foxbind.Find;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/9
 * 包    名：com.qb.wechat.ui.fragment
 * 描    述：发现页
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class DiscoverFragment extends BaseFragment {
    @Find(R.id.recyclerLy)
    private RecyclerView recyclerLy;

    @Override
    protected int gainViewRes() {
        return R.layout.fragment_discover;
    }

    @Override
    protected View create(View view) {
        recyclerLy.setLayoutManager(new LinearLayoutManager(getContext()));
        DiscoverAdapter adapter = new DiscoverAdapter(getContext());
        adapter.setAdapterData(DiscoverMenu.getDiscoverModels());
        recyclerLy.setAdapter(adapter);
        return view;
    }
}
