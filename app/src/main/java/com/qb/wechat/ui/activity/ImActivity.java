package com.qb.wechat.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qb.wechat.R;
import com.qb.wechat.aax.UserMsgListDb;
import com.qb.wxbase.app.BaseActivity;
import com.qb.wxbase.create.foxbind.Find;
import com.qb.wxbase.create.foxbus.Improved.FxEventBean;
import com.qb.wxbase.create.foxbus.Improved.base.FxGet;
import com.qb.wxbase.create.sql.base.DbOperation;
import com.qb.wxbase.create.sql.base.OperationFactory;
import com.qb.wxbase.rxsql.RxSqlBinding;
import com.qb.wxbase.rxsql.base.RxSqlSet;
import com.qb.wxbase.util.apkutil.SystemUtils;
import com.qb.wxbase.util.baseutil.TimeUtil;

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
    @Find(R.id.sendClick)
    TextView sendClick;
    @Find(R.id.bgImage)
    ImageView bgImage;
    @Find(R.id.leftClick)
    ImageView leftClick;
    @Find(R.id.leftTitle)
    TextView leftTitle;
    @Find(R.id.searchClick)
    ImageView searchClick;
    @Find(R.id.addClick)
    ImageView addClick;
    @Find(R.id.recycler)
    RecyclerView recycler;
    @Find(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    @FxGet("Weng2Fragment_ImUserBean")
    private FxEventBean<UserMsgListDb> userBean;

    @Override
    protected int gainContentView() {
        return R.layout.activity_im;
    }

    @Override
    protected void create() {
    }

    @Override
    protected boolean isShowTopBar() {
        return false;
    }

    @Override
    protected int setTopBarColor() {
        return R.color.colorPrimary;
    }

    @Override
    protected boolean isOpenSwipeBack() {
        return true;
    }

    @Override
    protected void begin() {
        super.begin();
        SystemUtils.setTopFontColor(this,true);
        initBean();
//        DbOperation<UserMsgListDb> dbDbOperation = new DbOperation<>(OperationFactory.getFactory().getOperation(UserMsgListDb.class));
        userBean.getBean().setLastDate(TimeUtil.getTime());
//        dbDbOperation.update(userBean.getBean());
        RxSqlBinding.update(userBean.getBean(),this);
    }

    @RxSqlSet(value = UserMsgListDb.class)
    public void setUserMsgListDb(UserMsgListDb userMsgListDb){
        showToast(userMsgListDb.lastDate);
    }

    /**
     * 初始化对象关联视图
     */
    private void initBean() {
        leftTitle.setText(userBean.getBean().getUserName());
        leftClick.setVisibility(View.VISIBLE);
        searchClick.setVisibility(View.GONE);
        addClick.setVisibility(View.VISIBLE);
        addClick.setImageResource(R.mipmap.icon_diandian);
    }
}
