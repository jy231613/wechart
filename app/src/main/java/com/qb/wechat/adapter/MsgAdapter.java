package com.qb.wechat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qb.wechat.R;
import com.qb.wechat.aax.UserMsgListDb;
import com.qb.wxbase.adapter.FoxAdapter;
import com.qb.wxbase.create.foxbind.Find;
import com.qb.wxbase.create.foxbind.FoxFindBind;

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
public class MsgAdapter extends FoxAdapter<UserMsgListDb,MsgAdapter.ViewHolder>{

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
    protected void bindViewHolder(ViewHolder viewHolder, UserMsgListDb s, int position) {
        viewHolder.userNameText.setText(s.getUserName());
        viewHolder.time.setText(s.getLastDate());

        Log.d("TAG", "bindViewHolder: >>>"+s.toString());

        viewHolder.clickLy.setTag(position);
        bindOnclickListener(viewHolder.clickLy);
        bindOnLongClickListener(viewHolder.clickLy);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @Find(R.id.userNameText)
        TextView userNameText;
        @Find(R.id.clickLy)
        LinearLayout clickLy;
        @Find(R.id.time)
        TextView time;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            FoxFindBind.bind(this.getClass(),this,itemView);
        }
    }
}
