package com.qb.wechat.ui.fragment.main;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qb.wechat.R;
import com.qb.wechat.aax.UserMsgListDb;
import com.qb.wechat.adapter.MsgAdapter;
import com.qb.wechat.ui.activity.ImActivity;
import com.qb.wechat.ui.activity.MainActivity;
import com.qb.wechat.widget.MainTopBar;
import com.qb.wxbase.app.BaseFragment;
import com.qb.wxbase.app.FoxBaseManagement;
import com.qb.wxbase.create.foxbind.Find;
import com.qb.wxbase.create.foxbus.FxBus;
import com.qb.wxbase.create.foxbus.Improved.FxEventBean;
import com.qb.wxbase.create.speasy.Sp;
import com.qb.wxbase.create.sql.base.DbOperation;
import com.qb.wxbase.create.sql.base.OperationFactory;
import com.qb.wxbase.rxsql.RxSqlBinding;
import com.qb.wxbase.rxsql.base.RxSqlSet;
import com.qb.wxbase.util.baseutil.TimeUtil;
import com.qb.wxui.dialog.WaChatDialog;
import com.qb.wxui.dialog.util.MsgDialogClickListener;

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
public class Weng2Fragment extends BaseFragment implements View.OnClickListener, View.OnLongClickListener {
    @Find(R.id.recyclerLy)
    private RecyclerView recyclerLy;

    //
    private GridLayoutManager gridLayoutManager;
    private MsgAdapter adapter;

    @Override
    protected int gainViewRes() {
        return R.layout.fragment_weng2;
    }

    DbOperation<UserMsgListDb> dbDbOperation;

    @Override
    protected View create(View view) {
        gridLayoutManager = new GridLayoutManager(getContext(),1);
        recyclerLy.setLayoutManager(gridLayoutManager);


//        List<UserMsgListDb> listDbs = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            UserMsgListDb userMsgListDb = new UserMsgListDb();
//            userMsgListDb.setUserName("userName"+i);
//            listDbs.add(userMsgListDb);
//        }
//        RxSqlBinding.insert(listDbs);
        return view;
    }

    public void refreshList(List<UserMsgListDb> userMsgListDbs){
        this.userMsgListDbs = userMsgListDbs;
        adapter = new MsgAdapter(getContext());
        adapter.setAdapterData(userMsgListDbs);
        adapter.setClickListener(this);
        adapter.setLongClickListener(this);
        recyclerLy.setAdapter(adapter);
    }
    List<UserMsgListDb> userMsgListDbs;

    @Override
    public void onClick(View v) {
        FxEventBean<UserMsgListDb> bean = new FxEventBean<>("Weng2Fragment_ImUserBean",userMsgListDbs.get(adapter.getPositionFromTag(v)));
        FxBus.post(bean);
        FoxBaseManagement.getFoxManagement().beginActivity(ImActivity.class);
    }

    private Dialog dialog;

    @Override
    public boolean onLongClick(View v) {
        dialog = WaChatDialog.showAffirmDialog(
                getContext(),
                "删除后,将清空该聊天的消息记录",
                "取消",
                "删除",
                new MsgDialogClickListener() {
                    @Override
                    public void doYes() {
                        dialog.dismiss();
                        dbDbOperation.del(UserMsgListDb.class,String.valueOf(userMsgListDbs.get(adapter.getPositionFromTag(v)).getId()));
                    }
                    @Override
                    public void doNo() {
                        dialog.dismiss();
                    }
                }
        );
        return true;
    }
}
