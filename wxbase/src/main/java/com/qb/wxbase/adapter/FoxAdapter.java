package com.qb.wxbase.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：cn.secret.base.adapter
 * 日    期：2018/10/11
 * 包    名：adsc
 * 描    述：这是一个Adapter包装类
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public abstract class FoxAdapter<BEAN,HOD extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BEAN> items;
    public Context context;

    /**
     * 构造方法,传入上下文关系
     * @param context 上下文
     */
    public FoxAdapter(Context context){
        this.items = new ArrayList<>();
        this.context = context;
    }

    /**
     * 设置数据
     * @param items 数据源
     */
    public void setAdapterData(List<BEAN> items){
        assert items != null;
        if (items.size()>0){
            int posAll = getItemCount();
            this.items = new ArrayList<>(items);
            if (onDataRefreshListener!=null)onDataRefreshListener.onRefreshListener(this.items,posAll);
        }
    }

    /**
     * 更新并新增数据,并跳转到底部
     * @param items 数据源
     */
    public void refreshAdapter(List<BEAN> items, RecyclerView.LayoutManager manager) {
        if (items!=null&&items.size()>0){
            int posAll = getItemCount();
            this.items.addAll(items);
            this.notifyItemInserted(this.items.size()-items.size());
            manager.scrollToPosition(posAll);
            if (onDataRefreshListener!=null)onDataRefreshListener.onRefreshListener(this.items,posAll);
        }
    }

    /**
     * 更新并新增数据,并跳转到相应位置
     * @param items 数据源
     */
    public void refreshAdapter(List<BEAN> items, RecyclerView.LayoutManager manager,int pos) {
        if (items!=null&&items.size()>0){
            int posAll = getItemCount();
            this.items = items;
            this.notifyItemInserted(items.size()>=pos?pos:items.size()-1);
            manager.scrollToPosition(posAll);
            if (onDataRefreshListener!=null)onDataRefreshListener.onRefreshListener(this.items,posAll);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutRes(viewType), parent, false);
        return getViewHolder(view,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemCount()!=0){
            bindViewHolder((HOD) holder,items.get(position),position);
        }
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        } else {
            return items.size();
        }
    }

    protected abstract HOD getViewHolder(View view,int viewType);
    protected abstract @LayoutRes int getLayoutRes(int viewType);
    protected abstract void bindViewHolder(HOD viewHolder,BEAN bean,int position);

    ////////////////////////////以下为item事件处理///////////////////////////////
    /**
     * 组件点击事件
     */
    protected View.OnClickListener clickListener = null;
    /**
     * 组件长按事件
     */
    protected View.OnLongClickListener longClickListener = null;
    /**
     * 组件选择事件
     */
    protected RadioGroup.OnCheckedChangeListener onRadioGroupCheckedChangeListener = null;
    /**
     * 组件选择事件
     */
    protected CompoundButton.OnCheckedChangeListener onCompoundCheckedChangeListener = null;
    /**
     * 数据更新事件
     */
    protected OnDataRefreshListener<BEAN> onDataRefreshListener = null;

    /**
     * 在组件的Tag中获取一个Bean对象
     * @param view 组件对象
     * @return HOD
     */
    public BEAN getBeanFromTag(View view){
        Object obj = view.getTag();
        assert obj != null;
        return (BEAN) obj;
    }

    /**
     * 在组件的Tag中获取一个索引下标
     * @param view 组件对象
     * @return int索引下标
     */
    public int getPositionFromTag(View view){
        Object obj = view.getTag();
        assert obj != null;
        return (Integer) obj;
    }

    /**
     * 批量绑定组件点击监听事件
     * @param views 组件s
     */
    @SuppressLint("Assert")
    public void bindOnclickListener(View... views){
        assert views != null && views.length > 0;
        for (View view:views) {
            view.setOnClickListener(clickListener);
        }
    }

    /**
     * 批量绑定组件长按监听事件
     * @param views 组件s
     */
    @SuppressLint("Assert")
    public void bindOnLongClickListener(View... views){
        assert views != null && views.length > 0;
        for (View view:views) {
            view.setOnLongClickListener(longClickListener);
        }
    }

    /**
     * 数据变化更新监听接口
     * @param <T> 数据类型
     */
    public interface OnDataRefreshListener<T>{
        /**
         * 数据发生变化时
         * @param items 数据源
         * @param oldSize 更新之前的数据长度
         */
        void onRefreshListener(List<T> items, int oldSize);
    }

    ///////////////////////////////设置监听////////////////////////////
    public void setClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setLongClickListener(View.OnLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void setOnRadioGroupCheckedChangeListener(RadioGroup.OnCheckedChangeListener onRadioGroupCheckedChangeListener) {
        this.onRadioGroupCheckedChangeListener = onRadioGroupCheckedChangeListener;
    }

    public void setOnCompoundCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCompoundCheckedChangeListener) {
        this.onCompoundCheckedChangeListener = onCompoundCheckedChangeListener;
    }

    public void setOnDataRefreshListener(OnDataRefreshListener<BEAN> onDataRefreshListener) {
        this.onDataRefreshListener = onDataRefreshListener;
    }
}
