package com.qb.wxui.dialog.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Summarize:dialog工具类
 * User:贾恒飞
 * Date:2018/5/10
 * Time:8:51
 * Email:17515250730@163.com
 * Created with AndroidStudio3.0
 */

public class DialogHelper {

    /**
     * 显示一个中间弹出的自定义布局提示框
     * @return dialog对象
     */
    public static Dialog showRadioDialog(Context context,int layoutRes, int[] ids,int style, View.OnClickListener click){
        Dialog dialog = new Dialog(context, style);
        //填充对话框的布局
        View inflate = LayoutInflater.from(context).inflate(layoutRes, null);
        //调用接口方法
        setOnclickListener(inflate,ids,click);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        // 设置宽高为match_parent，不要去算出来屏幕宽高再赋值哦，因为有些
        // 有虚拟按键的手机上计算出来的高度不一定准确，所以dialog不会全屏
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(params);
        //设置Dialog从窗体中间弹出
        dialogWindow.setGravity( Gravity.CENTER);
        dialog.show();
        return dialog;
    }

    /**
     * 显示一个中间弹出的自定义布局提示框
     * @return dialog对象
     */
    public static Dialog showRadioDialog(Context context,int layoutRes,int style,Bind.BindView bind){
        Dialog dialog = new Dialog(context, style);
        //填充对话框的布局
        View inflate = LayoutInflater.from(context).inflate(layoutRes, null);
        //布局数据绑定
        bind.bindView(inflate);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        // 设置宽高为match_parent，不要去算出来屏幕宽高再赋值哦，因为有些
        // 有虚拟按键的手机上计算出来的高度不一定准确，所以dialog不会全屏
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(params);
        //设置Dialog从窗体中间弹出
        dialogWindow.setGravity(Gravity.CENTER);
        try{
            dialog.show();
        }catch (Exception e){}
        return dialog;
    }

    public static Dialog showDownDialog(Context context,int layoutRes,int style,Bind.BindView bind){
        Dialog dialog = new Dialog(context, style);
        //填充对话框的布局
        View inflate = LayoutInflater.from(context).inflate(layoutRes, null);
        //布局数据绑定
        bind.bindView(inflate);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        // 设置宽高为match_parent，不要去算出来屏幕宽高再赋值哦，因为有些
        // 有虚拟按键的手机上计算出来的高度不一定准确，所以dialog不会全屏
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(params);
        //设置Dialog从窗体中间弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        try{
            dialog.show();
        }catch (Exception e){}
        return dialog;
    }

    /**
     * 显示一个中间弹出的自定义布局提示框
     * @return dialog对象
     */
    public static Dialog showRadioDialog3(Context context,int layoutRes,int style,Bind.BindView bind){
        Dialog dialog = new Dialog(context, style);
        //填充对话框的布局
        View inflate = LayoutInflater.from(context).inflate(layoutRes, null);
        //布局数据绑定
        bind.bindView(inflate);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        // 设置宽高为match_parent，不要去算出来屏幕宽高再赋值哦，因为有些
        // 有虚拟按键的手机上计算出来的高度不一定准确，所以dialog不会全屏
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(params);
        //设置Dialog从窗体中间弹出
        dialogWindow.setGravity(Gravity.CENTER);
        try{
            dialog.show();
        }catch (Exception e){}
        return dialog;
    }

    /**
     * 显示一个底部弹出的对话框(样式自定)
     * @param layoutRes
     * @param ids 设置监听的对象id数组
     * @param yValue 距离底部的位置
     * @return dialog对象  不提供shwo()方法
     */
    public static Dialog showButtomDialog(Context context,int layoutRes,int[] ids,int yValue,int style, View.OnClickListener click){
        Dialog dialog = new Dialog(context, style);
        //填充对话框的布局
        View inflate = LayoutInflater.from(context).inflate(layoutRes, null);
        //调用接口方法
        setOnclickListener(inflate,ids,click);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity( Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = yValue;//设置Dialog距离底部的距离
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        return dialog;
    }

    /**
     * 显示一个底部弹出的对话框(样式自定)
     * @param layoutRes
     * @param ids 设置监听的对象id数组
     * @param yValue 距离底部的位置
     * @param bind 绑定组件操作
     * @return dialog对象  不提供shwo()方法
     */
    public static Dialog showButtomDialog(Context context, int layoutRes, int[] ids, int yValue, int style, Bind.BindView bind, View.OnClickListener click){
        Dialog dialog = new Dialog(context, style);
        //填充对话框的布局
        View inflate = LayoutInflater.from(context).inflate(layoutRes, null);
        bind.bindView(inflate);
        //调用接口方法
        setOnclickListener(inflate,ids,click);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity( Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = yValue;//设置Dialog距离底部的距离
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        return dialog;
    }

    /**
     * 显示一个底部弹出的对话框(样式自定)(Dialog以外区域不模糊)
     * @param layoutRes
     * @param ids 设置监听的对象id数组
     * @param yValue 距离底部的位置
     * @return dialog对象  不提供show()方法
     */
    public static Dialog showButtomDialogWhite(Context context, int layoutRes, int[] ids, int yValue,int style, View.OnClickListener click){
        Dialog dialog = new Dialog(context, style);
        //填充对话框的布局
        View inflate = LayoutInflater.from(context).inflate(layoutRes, null);
        //调用接口方法
        setOnclickListener(inflate,ids,click);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity( Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = yValue;//设置Dialog距离底部的距离
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        return dialog;
    }

    public interface SingleChoiceListener{
        void onChick(DialogInterface dialog, int which);
    }

    /**
     * 根据控件id批量设置点击监听(引用布局)
     * @param ids
     */
    private static void setOnclickListener(View view,int[] ids,View.OnClickListener click){
        if (ids.length != 0 && view != null){
            for (int id:ids) {
                view.findViewById(id).setOnClickListener(click);
            }
        }
    }

}
