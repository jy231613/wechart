package com.qb.wechat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qb.wechat.R;
import com.qb.wxbase.adapter.FoxAdapter;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/9
 * 包    名：com.qb.wechat.adapter
 * 描    述：消息列表适配器
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class MsgAdapter extends FoxAdapter<String,MsgAdapter.ViewHolder>{

    /**
     * 构造方法,传入上下文关系
     *
     * @param context 上下文
     */
    public MsgAdapter(Context context) {
        super(context);
    }

    @Override
    protected ViewHolder getViewHolder(View view, int viewType) {
        return new ViewHolder(view);
    }

    @Override
    protected int getLayoutRes(int viewType) {
        return R.layout.adapter_msg_user;
    }

    @Override
    protected void bindViewHolder(ViewHolder viewHolder, String s, int position) {
        viewHolder.userNameText.setText(s);

        viewHolder.clickLy.setTag(position);
        bindOnclickListener(viewHolder.clickLy);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView userNameText;
        LinearLayout clickLy;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameText = itemView.findViewById(R.id.userNameText);
            clickLy = itemView.findViewById(R.id.clickLy);
        }
    }
}
