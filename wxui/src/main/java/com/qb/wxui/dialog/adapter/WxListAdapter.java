package com.qb.wxui.dialog.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qb.wxui.R;
import com.qb.wxui.dialog.util.RecyclerBean;
import com.qb.wxui.inter.CommonOnItemClick;

import java.util.List;

import static com.qb.wxui.inter.EnumUtils.ItemClick.Base;


/**
 * RecyclerView适配器
 * 选择购买列表适配器
 * Created by 贾恒飞 on 2017/12/28 0028.
 */
public class WxListAdapter extends RecyclerView.Adapter<WxListAdapter.ViewHolder> implements View.OnClickListener{
    private List<RecyclerBean> items;
    private Context context;

    //构造方法,用于传入数据参数
    public WxListAdapter(List<RecyclerBean> beans, Context context) {
        this.items = beans;
        this.context = context;
    }

    /**
     * 设置数据源
     * @param items
     */
    public void setItems(List<RecyclerBean> items) {
        this.items = items;
        this.notifyDataSetChanged();
    }

    //创建新View，被LayoutManager所调用
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_wx_list_item,viewGroup,false);
        return new ViewHolder(view);
    }

    private CompoundButton.OnCheckedChangeListener listener;

    public void setListener(CompoundButton.OnCheckedChangeListener listener) {
        this.listener = listener;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView centerText;
        private LinearLayout bg;
        private View line;
        public ViewHolder(View view){
            super(view);
            centerText = view.findViewById(R.id.centerText);
            bg = view.findViewById(R.id.bg);
            line = view.findViewById(R.id.line);
        }
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder v, int position) {
        RecyclerBean bean = items.get(position);
//        v.line.setVisibility(position==items.size()-1?View.GONE:View.VISIBLE);
        v.centerText.setText(bean.getListName()!=null?bean.getListName():"-");
        if (bean.getBindClick()!=null)v.bg.setOnClickListener(bean.getBindClick());
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return items.size();
    }

    ////////////////////////////以下为item点击处理///////////////////////////////
    private CommonOnItemClick mOnItemClickListener = null;

    //提供给子类,使子类可以设置点击监听事件
    public void setOnItemClickListener(CommonOnItemClick listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        //注意这里使用getTag方法获取数据
        int position = (int) v.getTag();
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, Base, position);
        }
    }
}