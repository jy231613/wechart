package com.qb.wechat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qb.wechat.R;
import com.qb.wechat.bean.UserInfoBean;
import com.qb.wxbase.adapter.FoxAdapter;
import com.qb.wxbase.create.foxbind.Find;
import com.qb.wxbase.create.foxbind.FoxFindBind;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/11
 * 包    名：com.qb.wechat.adapter
 * 描    述：通讯录用户列表详细
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class UserBookAdapter extends FoxAdapter<UserInfoBean,UserBookAdapter.ViewHolder> {

    /**
     * 构造方法,传入上下文关系
     *
     * @param context 上下文
     */
    public UserBookAdapter(Context context) {
        super(context);
    }

    @Override
    protected ViewHolder getViewHolder(View view, int viewType) {
        return new ViewHolder(view);
    }

    @Override
    protected int getLayoutRes(int viewType) {
        return R.layout.adapter_user_book_list;
    }

    @Override
    protected void bindViewHolder(ViewHolder viewHolder, UserInfoBean userInfoBean, int position) {
        viewHolder.clickLy.setTag(position);
        if (userInfoBean.isSetSystemInfo()){
            //系统
            viewHolder.userHeard.setImageResource(userInfoBean.getBaseRes());
            viewHolder.userNameText.setText(userInfoBean.getUsername());
        }else{
            //用户
            viewHolder.userHeard.setImageResource(R.mipmap.ic_launcher);
            viewHolder.userNameText.setText("朋友"+position);
        }
        if (position==getItemCount()-1){
            viewHolder.smallLine.setVisibility(View.GONE);
        }else{
            viewHolder.smallLine.setVisibility(View.VISIBLE);
        }
        bindOnclickListener(viewHolder.clickLy);
        bindOnLongClickListener(viewHolder.clickLy);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @Find(R.id.clickLy)
        LinearLayout clickLy;
        @Find(R.id.userHeard)
        ImageView userHeard;
        @Find(R.id.smallLine)
        View smallLine;
        @Find(R.id.userNameText)
        TextView userNameText;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            FoxFindBind.bind(this.getClass(),this,itemView);
        }
    }
}
