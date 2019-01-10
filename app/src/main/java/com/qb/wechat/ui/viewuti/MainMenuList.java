package com.qb.wechat.ui.viewuti;

import android.annotation.SuppressLint;
import android.view.View;

import com.qb.wechat.ui.viewuti.model.MainBottomModel;
import com.qb.wechat.widget.MainTopBar;

import java.util.ArrayList;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/9
 * 包    名：com.qb.wechat.ui.viewuti
 * 描    述：首页带单
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class MainMenuList {

    private int selectColor;//选中颜色
    private int color;//未选中
    private static ArrayList<MainBottomModel> list = new ArrayList<>();
    @SuppressLint("StaticFieldLeak")
    private static MainBottomModel selectBean = null;
    private static int index = 0;

    public static int getIndex() {
        return index;
    }

    /**
     * 销毁
     */
    public static void destory(){
        list = new ArrayList<>();
        selectBean = null;
        index = 0;
    }

    /**
     * 初始化Menu
     * @param mainTopBar 头
     */
    public static void initMenu(MainTopBar mainTopBar){
        boolean isSelect = false;
        int i = 0;
        for (MainBottomModel model:list) {
            model.getTv().setText(model.getTitle());
            if (model.isChecked()){
                if (!isSelect){
                    isSelect = true;
                    selectBean = model;
                    index = i;
                    mainTopBar.setText(model.getTitle());
                    model.getTv().setTextColor(model.getSelectColor());
                    model.getImc().setImageResource(model.getSelectRes());
                }else{
                    model.getTv().setTextColor(model.getColor());
                    model.getImc().setImageResource(model.getRes());
                }
            }else{
                model.getTv().setTextColor(model.getColor());
                model.getImc().setImageResource(model.getRes());
            }
            i++;
        }
    }

    /**
     * @param s 选择
     */
    public static void selectMenu(int s,MainTopBar mainTopBar){
        MainBottomModel now = list.get(s);
        MainBottomModel old = list.get(index);
        old.setChecked(false);
        old.getTv().setTextColor(old.getColor());
        old.getImc().setImageResource(old.getRes());
        now.setChecked(true);
        now.getTv().setTextColor(now.getSelectColor());
        now.getImc().setImageResource(now.getSelectRes());
        selectBean = now;
        index = s;
        mainTopBar.setText(now.getTitle());
        if (s==3)mainTopBar.setVisibility(View.GONE);
        else mainTopBar.setVisibility(View.VISIBLE);
    }

    public int getSelectColor() {
        return selectColor;
    }

    public void setSelectColor(int selectColor) {
        this.selectColor = selectColor;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public static ArrayList<MainBottomModel> getList() {
        return list;
    }

    public static void setList(ArrayList<MainBottomModel> list) {
        MainMenuList.list = list;
    }

    public static MainBottomModel getSelectBean() {
        return selectBean;
    }

    public static void setSelectBean(MainBottomModel selectBean) {
        MainMenuList.selectBean = selectBean;
    }
}
