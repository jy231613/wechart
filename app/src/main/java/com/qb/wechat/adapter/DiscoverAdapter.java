package com.qb.wechat.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qb.wechat.R;
import com.qb.wechat.ui.viewuti.model.DiscoverModel;
import com.qb.wxbase.adapter.FoxAdapter;
import com.qb.wxbase.listener.OnNotRepetitionClickListener;

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
public class DiscoverAdapter extends FoxAdapter<DiscoverModel,DiscoverAdapter.ViewHolder>{

    /**
     * 构造方法,传入上下文关系
     *
     * @param context 上下文
     */
    public DiscoverAdapter(Context context) {
        super(context);
    }

    @Override
    protected ViewHolder getViewHolder(View view, int viewType) {
        return new ViewHolder(view);
    }

    @Override
    protected int getLayoutRes(int viewType) {
        return R.layout.adapter_discover;
    }

    @Override
    protected void bindViewHolder(ViewHolder v, DiscoverModel s, int position) {
        v.bigLine.setVisibility(s.isShow()?View.VISIBLE:View.GONE);
        v.smallLine.setVisibility(s.isShow()?View.GONE:View.VISIBLE);
        v.titleText.setText(s.getName());
        v.leftIcon.setImageResource(s.getImg());
        //页面跳转
        v.clickLy.setOnClickListener(new OnNotRepetitionClickListener() {
            @Override
            public void onAfterClick(View v) {
                if (s.getToActivity()!=null)context.startActivity(new Intent(context,s.getToActivity()));
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView leftIcon;
        TextView titleText;
        View bigLine,smallLine;
        LinearLayout clickLy;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            clickLy = itemView.findViewById(R.id.clickLy);
            bigLine = itemView.findViewById(R.id.bigLine);
            smallLine = itemView.findViewById(R.id.smallLine);
            titleText = itemView.findViewById(R.id.titleText);
            leftIcon = itemView.findViewById(R.id.leftIcon);
        }
    }
}
