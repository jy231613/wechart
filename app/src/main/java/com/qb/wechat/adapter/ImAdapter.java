package com.qb.wechat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qb.wechat.R;
import com.qb.wechat.aax.UserMsgDb;
import com.qb.wxbase.adapter.FoxAdapter;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechart
 * 日    期：2019/1/18
 * 包    名：com.qb.wechat.adapter
 * 描    述：消息列表
 * Create by zFox from AndroidStudio3.2
 * ================================================
 */
public class ImAdapter extends FoxAdapter<UserMsgDb,ImAdapter.ViewHolder> {

    /**
     * 构造方法,传入上下文关系
     *
     * @param context 上下文
     */
    public ImAdapter(Context context) {
        super(context);
    }

    @Override
    protected ViewHolder getViewHolder(View view, int viewType) {
        return new ViewHolder(view);
    }

    @Override
    protected int getLayoutRes(int viewType) {
        return R.layout.adapter_im_message;
    }

    @Override
    protected void bindViewHolder(ViewHolder viewHolder, UserMsgDb userMsgDb, int position) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
