package com.qb.wechat.ui.fragment.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qb.wechat.R;
import com.qb.wechat.adapter.AddressBookBaseAdapter;
import com.qb.wechat.adapter.UserBookAdapter;
import com.qb.wechat.bean.AddressBookBean;
import com.qb.wechat.bean.BaseAddressBean;
import com.qb.wechat.bean.UserInfoBean;
import com.qb.wechat.util.ChartArray;
import com.qb.wechat.widget.CustomIndexBar;
import com.qb.wechat.widget.MainTopBar;
import com.qb.wxbase.app.BaseFragment;
import com.qb.wxbase.create.foxbind.Find;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/9
 * 包    名：com.qb.wechat.ui.fragment
 * 描    述：通讯录页面
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class AddressBookFragment extends BaseFragment implements AddressBookBaseAdapter.OnBindRecyclerView, CustomIndexBar.OnSollerFormCustom {
    @Find(R.id.recyclerLy)
    private RecyclerView recyclerLy;
    @Find(R.id.custom)
    private CustomIndexBar custom;
    @Find(R.id.customBar)
    private TextView customBar;
    @Find(R.id.loading)
    private TextView loading;

    private AddressBookBaseAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    private List<String> stringList;

    @Override
    protected int gainViewRes() {
        return R.layout.fragment_addressbook;
    }

    @Override
    protected View create(View view) {
        loading.setVisibility(View.VISIBLE);

        stringList = ChartArray.array();
        List<BaseAddressBean> beas = new ArrayList<>();
        for (int i = 0; i < stringList.size()-1; i++) {
            BaseAddressBean bean = new BaseAddressBean();
            bean.setTopName(stringList.get(i));
            List<UserInfoBean> beanson = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                beanson.add(new UserInfoBean());
            }
            //toUpperCase()字符转大写toLowerCase()转小写
            if (!bean.getTopName().toUpperCase().equals("C"))bean.setBeans(beanson);
            beas.add(bean);
        }
        loading.setVisibility(View.GONE);
        adapter = new AddressBookBaseAdapter(getContext(),this);
        adapter.setAdapterData(ChartArray.configArray(beas));
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerLy.setLayoutManager(linearLayoutManager);
        recyclerLy.setAdapter(adapter);

        custom.setOnSollerFormCustom(this);

        return view;
    }

    @Override
    public void onBindRecycler(RecyclerView recyclerView, List<UserInfoBean> beans,int position) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        UserBookAdapter userBookAdapter = new UserBookAdapter(getContext());
        userBookAdapter.setAdapterData(beans);
        recyclerView.setAdapter(userBookAdapter);
    }

    @Override
    public void onDown(int pos) {
        customBar.setText(stringList.get(pos));
        customBar.setVisibility(View.VISIBLE);
        linearLayoutManager.scrollToPosition(pos);
    }

    @Override
    public void onUp(int pos) {
        customBar.setVisibility(View.GONE);
    }

    /**
     * 设置右侧边栏隐藏或消失
     * @param isGone true隐藏
     */
    public void setGoneCustom(boolean isGone){
        custom.setVisibility(isGone?View.GONE:View.VISIBLE);
    }

    @Override
    public void onMove(int pos) {
        customBar.setText(stringList.get(pos));
        customBar.setVisibility(View.VISIBLE);
        linearLayoutManager.scrollToPosition(pos);
    }
}
