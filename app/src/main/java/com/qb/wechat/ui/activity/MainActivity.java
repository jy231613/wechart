package com.qb.wechat.ui.activity;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.qb.wechat.R;
import com.qb.wechat.ui.fragment.main.AddressBookFragment;
import com.qb.wechat.ui.fragment.main.DiscoverFragment;
import com.qb.wechat.ui.fragment.main.MyFragment;
import com.qb.wechat.ui.fragment.main.Weng2Fragment;
import com.qb.wechat.ui.viewuti.MainMenuList;
import com.qb.wechat.ui.viewuti.model.MainBottomModel;
import com.qb.wechat.widget.MainTopBar;
import com.qb.wxbase.adapter.FragAdapter;
import com.qb.wxbase.app.BaseActivity;
import com.qb.wxbase.create.foxbind.Find;
import com.qb.wxbase.listener.OnNotRepetitionClickListener;
import com.qb.wxbase.listener.ViewPageScrollAndChangeListener;
import com.qb.wxbase.util.apkutil.SystemUtils;
import com.qb.wxbase.widget.viewpage.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    @Find(R.id.linearLayout)
    private LinearLayout linearLayout;
    @Find(R.id.scrollViewPage)
    private NoScrollViewPager scrollViewPage;
    @Find(R.id.click1)
    private LinearLayout click1;
    @Find(R.id.click2)
    private LinearLayout click2;
    @Find(R.id.click3)
    private LinearLayout click3;
    @Find(R.id.click4)
    private LinearLayout click4;
    @Find(R.id.mainTopBar)
    private MainTopBar mainTopBar;

    private Weng2Fragment weng2Fragment;
    private MyFragment myFragment;
    private DiscoverFragment discoverFragment;
    private AddressBookFragment addressBookFragment;
    private List<Fragment> fragments;

    @Override
    protected void init() {
        super.init();
        weng2Fragment = new Weng2Fragment();
        myFragment = new MyFragment();
        discoverFragment = new DiscoverFragment();
        addressBookFragment = new AddressBookFragment();
        fragments = new ArrayList<>();
        fragments.add(weng2Fragment);
        fragments.add(addressBookFragment);
        fragments.add(discoverFragment);
        fragments.add(myFragment);
    }

    @Override
    protected boolean isBackDialog() {
        return true;
    }

    @Override
    protected int setTopBarColor() {
        return R.color.colorPrimary;
    }

    @Override
    protected int gainContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void create() {
        //初始化Tab视图
        initTab();

        //初始化ViewPage
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(),fragments);
        scrollViewPage.setAdapter(adapter);
        scrollViewPage.addOnPageChangeListener(new ViewPageScrollAndChangeListener(new ViewPageScrollAndChangeListener.OnScrollAndChange() {
            @Override
            public void onIdle() {
                addressBookFragment.setGoneCustom(false);
            }

            @Override
            public void onDragging() {
                addressBookFragment.setGoneCustom(true);
            }

            @Override
            public void onSettling() {
            }

            @Override
            public void onSelect(int position) {
                selectTab(position);
            }
        }));
        scrollViewPage.setNoScroll(false);

        //初始化子页面监听
        myFragment.setOnDownOrUpListener(new MyFragment.OnDownOrUpListener() {
            @Override
            public void onDown() {
                linearLayout.setVisibility(View.GONE);
                scrollViewPage.setNoScroll(true);
            }
            @Override
            public void onUp() {
                linearLayout.setVisibility(View.VISIBLE);
                scrollViewPage.setNoScroll(false);
            }
        });

        //同步底部监听
        click1.setOnClickListener(new OnNotRepetitionClickListener() {
            @Override
            public void onAfterClick(View v) {
                scrollViewPage.setCurrentItem(0);
                selectTab(0);
            }
        });
        click2.setOnClickListener(new OnNotRepetitionClickListener() {
            @Override
            public void onAfterClick(View v) {
                scrollViewPage.setCurrentItem(1);
                selectTab(1);
            }
        });
        click3.setOnClickListener(new OnNotRepetitionClickListener() {
            @Override
            public void onAfterClick(View v) {
                scrollViewPage.setCurrentItem(2);
                selectTab(2);
            }
        });
        click4.setOnClickListener(new OnNotRepetitionClickListener() {
            @Override
            public void onAfterClick(View v) {
                scrollViewPage.setCurrentItem(3);
                selectTab(3);
            }
        });

    }

    @Override
    protected void destory() {
        super.destory();
        MainMenuList.destory();
    }

    @Override
    protected void begin() {
        super.begin();
        SystemUtils.setTopFontColor(this,true);
    }

    /**
     * @param s
     */
    public void selectTab(int s){
        if (s!=MainMenuList.getIndex()){
            MainMenuList.selectMenu(s,mainTopBar);
        }
    }

    public void initTab(){
        MainMenuList.getList().add(new MainBottomModel(
                "嗡嗡",
                true,
                R.mipmap.icon_msg,
                R.mipmap.icon_msg_no,
                getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.text_black),
                findViewById(R.id.tx1),
                findViewById(R.id.wx1)
        ));
        MainMenuList.getList().add(new MainBottomModel(
                "通讯录",
                false,
                R.mipmap.icon_userlist,
                R.mipmap.icon_userkist_no,
                getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.text_black),
                findViewById(R.id.tx2),
                findViewById(R.id.wx2)
        ));
        MainMenuList.getList().add(new MainBottomModel(
                "发现",
                false,
                R.mipmap.icon_look,
                R.mipmap.icon_look_no,
                getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.text_black),
                findViewById(R.id.tx3),
                findViewById(R.id.wx3)
        ));
        MainMenuList.getList().add(new MainBottomModel(
                "我",
                false,
                R.mipmap.icon_my,
                R.mipmap.icon_my_no,
                getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.text_black),
                findViewById(R.id.tx4),
                findViewById(R.id.wx4)
        ));
        MainMenuList.initMenu(mainTopBar);
    }
}
