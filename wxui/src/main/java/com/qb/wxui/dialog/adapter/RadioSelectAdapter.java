package com.qb.wxui.dialog.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qb.wxui.R;
import com.qb.wxui.inter.CommonOnItemClick;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.qb.wxui.inter.EnumUtils.ItemClick.Base;

/**
 * RecyclerView适配器
 * 选择购买列表适配器
 * Created by 贾恒飞 on 2017/12/28 0028.
 */
public class RadioSelectAdapter extends RecyclerView.Adapter<RadioSelectAdapter.ViewHolder> implements View.OnClickListener{
    private List<String> items;
    private Context context;
    private int morenPos;

    //构造方法,用于传入数据参数
    public RadioSelectAdapter(List<String> beans,int morenPos, Context context) {
        this.items = beans;
        this.context = context;
        this.morenPos = morenPos;
    }

    //构造方法,用于传入数据参数
    public RadioSelectAdapter(Set<String> beans, int morenPos, Context context) {
        this.items = new ArrayList<>();
        this.items.addAll(beans);
        this.context = context;
        this.morenPos = morenPos;
    }

    /**
     * 设置数据源
     * @param items
     */
    public void setItems(List<String> items) {
        this.items = items;
        this.notifyDataSetChanged();
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_radio_select_item,viewGroup,false);
        return new ViewHolder(view);
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleLeft;
        private ImageView img;
        private LinearLayout bg;
        public ViewHolder(View view){
            super(view);
            titleLeft = view.findViewById(R.id.titleLeft);
            img = view.findViewById(R.id.img);
            bg = view.findViewById(R.id.bg);
        }
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder v, int position) {
        if (position==morenPos){
            v.img.setImageResource(R.drawable.icon_select_yes);
        }else{
            v.img.setImageResource(R.drawable.icon_select_no);
        }
        v.titleLeft.setText(items.get(position));

        v.bg.setTag(position);
        v.bg.setOnClickListener(this);
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