package com.qb.wechat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qb.wechat.R;
import com.qb.wechat.bean.BaseAddressBean;
import com.qb.wechat.bean.UserInfoBean;
import com.qb.wxbase.adapter.FoxAdapter;
import com.qb.wxbase.create.foxbind.Find;
import com.qb.wxbase.create.foxbind.FoxFindBind;

import java.util.List;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/11
 * 包    名：com.qb.wechat.adapter
 * 描    述：通讯录页的BaseAdapter
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class AddressBookBaseAdapter extends FoxAdapter<BaseAddressBean,AddressBookBaseAdapter.ViewHolder> {
    private OnBindRecyclerView onBindRecyclerView;

    /**
     * 构造方法,传入上下文关系
     *
     * @param context 上下文
     */
    public AddressBookBaseAdapter(Context context,OnBindRecyclerView onBindRecyclerView) {
        super(context);
        this.onBindRecyclerView = onBindRecyclerView;
    }

    @Override
    protected ViewHolder getViewHolder(View view, int viewType) {
        return new ViewHolder(view);
    }

    @Override
    protected int getLayoutRes(int viewType) {
        return R.layout.adapter_base_address_book;
    }

    @Override
    protected void bindViewHolder(ViewHolder viewHolder, BaseAddressBean s, int position) {

        if (s.getBeans()!=null&&s.getBeans().size()>0){
            if (position==1){
                viewHolder.topTitle.setText("星标朋友");
            }else{
                viewHolder.topTitle.setText(s.getTopName());
            }
            if (position==0){
                viewHolder.topTitle.setVisibility(View.GONE);
            }else{
                viewHolder.topTitle.setVisibility(View.VISIBLE);
            }
            viewHolder.recyclerLy.setVisibility(View.VISIBLE);
            if (onBindRecyclerView!=null)onBindRecyclerView.onBindRecycler(viewHolder.recyclerLy,s.getBeans(),position);
        }else{
            viewHolder.topTitle.setVisibility(View.GONE);
            viewHolder.recyclerLy.setVisibility(View.GONE);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @Find(R.id.topTitle)
        TextView topTitle;
        @Find(R.id.recyclerLy)
        RecyclerView recyclerLy;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            FoxFindBind.bind(this.getClass(),this,itemView);
        }
    }

    public interface OnBindRecyclerView{
        void onBindRecycler(RecyclerView recyclerView,List<UserInfoBean> beans,int position);
    }
}
