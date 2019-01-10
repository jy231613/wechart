package com.qb.wxui.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.qb.wxui.R;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：zcapp
 * 日    期：2018/8/24 0024--4:06--星期五
 * 包    名：cn.app.zc.widget
 * 描    述：热度bar
 * Create by Administrator from AndroidStudio3.1
 * ================================================
 */
public class HeatBar extends FrameLayout implements View.OnClickListener {
    private View root;
    private Context context;
    private boolean isClick = false;

    private ImageView img1,img2,img3,img4,img5;

    public HeatBar(@NonNull Context context) {
        super(context);
        init(context);
    }

    public HeatBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HeatBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        this.context = context;
        root = LayoutInflater.from(context).inflate(R.layout.view_heatbar_layout, this);
        img1 = root.findViewById(R.id.img1);
        img2 = root.findViewById(R.id.img2);
        img3 = root.findViewById(R.id.img3);
        img4 = root.findViewById(R.id.img4);
        img5 = root.findViewById(R.id.img5);
        if (isClick)setBarOnclick();
    }

    /**
     * 开启点击监听
     */
    public void setClick() {
        isClick = true;
        setBarOnclick();
    }

    /**
     * 设置热度
     * @param number 热度数
     */
    public void setHeat(int number){
        //热度0-5
        int heat = 0;
        if (number <0){
            heat = 0;
        }else if (number>5){
            heat = 5;
        }else{
            heat = number;
        }
        switch (heat){
            case 0:
                setVis(false,false,false,false,false);
                break;
            case 1:
                setVis(true,false,false,false,false);
                break;
            case 2:
                setVis(true,true,false,false,false);
                break;
            case 3:
                setVis(true,true,true,false,false);
                break;
            case 4:
                setVis(true,true,true,true,false);
                break;
            case 5:
                setVis(true,true,true,true,true);
                break;
                default:
                    setVis(true,true,true,true,true);
                    break;
        }
    }

    /**
     * 设置点击监听
     */
    private void setBarOnclick(){
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);
    }

    /**
     * 设置是否为热度
     * @param is1
     * @param is2
     * @param is3
     * @param is4
     * @param is5
     */
    private void setVis(boolean is1,boolean is2,boolean is3,boolean is4,boolean is5){
        img1.setBackgroundResource(is1 ? R.mipmap.icon_redu2 : R.mipmap.icon_redu);
        img2.setBackgroundResource(is2 ? R.mipmap.icon_redu2 : R.mipmap.icon_redu);
        img3.setBackgroundResource(is3 ? R.mipmap.icon_redu2 : R.mipmap.icon_redu);
        img4.setBackgroundResource(is4 ? R.mipmap.icon_redu2 : R.mipmap.icon_redu);
        img5.setBackgroundResource(is5 ? R.mipmap.icon_redu2 : R.mipmap.icon_redu);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.img1) {
            setVis(true, false, false, false, false);
        } else if (i == R.id.img2) {
            setVis(true, true, false, false, false);
        } else if (i == R.id.img3) {
            setVis(true, true, true, false, false);
        } else if (i == R.id.img4) {
            setVis(true, true, true, true, false);
        } else if (i == R.id.img5) {
            setVis(true, true, true, true, true);
        } else {
            setVis(false, false, false, false, false);
        }
    }
}
