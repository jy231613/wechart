package com.qb.wxui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qb.wxbase.util.uibase.DialogFor;
import com.qb.wxui.R;
import com.qb.wxui.dialog.adapter.WxListAdapter;
import com.qb.wxui.dialog.util.Bind;
import com.qb.wxui.dialog.util.DialogHelper;
import com.qb.wxui.dialog.util.MsgDialogClickListener;
import com.qb.wxui.dialog.util.RecyclerBean;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.Arrays;
import java.util.List;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/8
 * 包    名：com.qb.wxui.dialog
 * 描    述：微信对话框
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class WaChatDialog {

    //显示图片对话框
    public static Dialog showImageDialog(Context context, String title, String imgUrl,String noBtn,String yesBtn, MsgDialogClickListener listener,boolean isSide){
        Dialog dialog = initImageDialog(context,title,imgUrl,noBtn,yesBtn,listener);
        dialog.setCanceledOnTouchOutside(isSide);
        return dialog;
    }
    public static Dialog showImageDialog(Context context,String imgUrl,String noBtn,String yesBtn, MsgDialogClickListener listener){
        Dialog dialog = initImageDialog(context,null,imgUrl,noBtn,yesBtn,listener);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }
    public static Dialog showImageDialog(Context context, String imgUrl, MsgDialogClickListener listener){
        Dialog dialog = initImageDialog(context,null,imgUrl,null,null,listener);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }
    public static Dialog showImageDialog(Context context, String title, String imgUrl,MsgDialogClickListener listener){
        Dialog dialog = initImageDialog(context,title,imgUrl,null,null,listener);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }
    public static Dialog showImageDialog(Context context,String content,String noBtn,String yesBtn, MsgDialogClickListener listener,boolean isSide){
        Dialog dialog = initImageDialog(context,null,content,noBtn,yesBtn,listener);
        dialog.setCanceledOnTouchOutside(isSide);
        return dialog;
    }
    public static Dialog showImageDialog(Context context, String imgUrl, MsgDialogClickListener listener,boolean isSide){
        Dialog dialog = initImageDialog(context,null,imgUrl,null,null,listener);
        dialog.setCanceledOnTouchOutside(isSide);
        return dialog;
    }
    public static Dialog showImageDialog(Context context, String title, String imgUrl,MsgDialogClickListener listener,boolean isSide){
        Dialog dialog = initImageDialog(context,title,imgUrl,null,null,listener);
        dialog.setCanceledOnTouchOutside(isSide);
        return dialog;
    }
    private static Dialog initImageDialog(Context context, String title,String imgUrl,String noBtn, String yesBtn, MsgDialogClickListener listener) {
        return DialogHelper.showRadioDialog(context, R.layout.wx_dialog_image_layout, R.style.wx_dialog_affirm, view -> {
            TextView titleTv = view.findViewById(R.id.title);
            ImageView contentTv = view.findViewById(R.id.content);
            TextView yesBtnTv = view.findViewById(R.id.yesBtn);
            TextView noBtnTv = view.findViewById(R.id.noBtn);
            if (title == null) {
                titleTv.setVisibility(View.GONE);
            } else {
                titleTv.setVisibility(View.VISIBLE);
                titleTv.setText(title);
            }
            if (noBtn!=null)noBtnTv.setText(noBtn);
            if (yesBtn!=null)yesBtnTv.setText(yesBtn);
            //加载图片
            //??
            yesBtnTv.setOnClickListener(view1 -> listener.doYes());
            noBtnTv.setOnClickListener(view12 -> listener.doNo());
        });
    }


    //显示提示对话框
    public static Dialog showAffirmDialog(Context context, String title, String content,String noBtn,String yesBtn, MsgDialogClickListener listener,boolean isSide){
        Dialog dialog = initAffirmDialog(context,title,content,noBtn,yesBtn,listener);
        dialog.setCanceledOnTouchOutside(isSide);
        return dialog;
    }
    public static Dialog showAffirmDialog(Context context,String content,String noBtn,String yesBtn, MsgDialogClickListener listener){
        Dialog dialog = initAffirmDialog(context,null,content,noBtn,yesBtn,listener);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }
    public static Dialog showAffirmDialog(Context context, String content, MsgDialogClickListener listener){
        Dialog dialog = initAffirmDialog(context,null,content,null,null,listener);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }
    public static Dialog showAffirmDialog(Context context, String title, String content,MsgDialogClickListener listener){
        Dialog dialog = initAffirmDialog(context,title,content,null,null,listener);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }
    public static Dialog showAffirmDialog(Context context,String content,String noBtn,String yesBtn, MsgDialogClickListener listener,boolean isSide){
        Dialog dialog = initAffirmDialog(context,null,content,noBtn,yesBtn,listener);
        dialog.setCanceledOnTouchOutside(isSide);
        return dialog;
    }
    public static Dialog showAffirmDialog(Context context, String content, MsgDialogClickListener listener,boolean isSide){
        Dialog dialog = initAffirmDialog(context,null,content,null,null,listener);
        dialog.setCanceledOnTouchOutside(isSide);
        return dialog;
    }
    public static Dialog showAffirmDialog(Context context, String title, String content,MsgDialogClickListener listener,boolean isSide){
        Dialog dialog = initAffirmDialog(context,title,content,null,null,listener);
        dialog.setCanceledOnTouchOutside(isSide);
        return dialog;
    }
    private static Dialog initAffirmDialog(Context context, String title, String content,String noBtn,String yesBtn, MsgDialogClickListener listener) {
        return DialogHelper.showRadioDialog(context, R.layout.wx_dialog_affirm_layout, R.style.wx_dialog_affirm, view -> {
            TextView titleTv = view.findViewById(R.id.title);
            TextView contentTv = view.findViewById(R.id.content);
            TextView yesBtnTv = view.findViewById(R.id.yesBtn);
            TextView noBtnTv = view.findViewById(R.id.noBtn);
            if (title == null) {
                titleTv.setVisibility(View.GONE);
            } else {
                titleTv.setVisibility(View.VISIBLE);
                titleTv.setText(title);
            }
            if (noBtn!=null)noBtnTv.setText(noBtn);
            if (yesBtn!=null)yesBtnTv.setText(yesBtn);
            contentTv.setText(content);
            yesBtnTv.setOnClickListener(view1 -> listener.doYes());
            noBtnTv.setOnClickListener(view12 -> listener.doNo());
        });
    }

    //显示提示信息
    private static Dialog dialog;
    public static void showSystemAffirmDialog(Context context, String title, String content){
        dialog = showAffirmDialogSystem(context, title, content, "确定", new MsgDialogClickListener() {
            @Override
            public void doYes() {
                dialog.dismiss();
            }
            @Override
            public void doNo() {
            }
        });
    }

    /**
     * 显示提示对话框
     * @param context 上下文关系
     */
    private static Dialog showAffirmDialogSystem(Context context, String title, String content,String yesBtn, MsgDialogClickListener listener) {
        return DialogHelper.showRadioDialog(context, R.layout.wx_dialog_affirm2_layout, R.style.wx_dialog_affirm, view -> {
            TextView titleTv = view.findViewById(R.id.title);
            TextView contentTv = view.findViewById(R.id.content);
            TextView yesBtnTv = view.findViewById(R.id.yesBtn);
            if (title == null) {
                titleTv.setVisibility(View.GONE);
            } else {
                titleTv.setVisibility(View.VISIBLE);
                titleTv.setText(title);
            }
            if (yesBtn!=null)yesBtnTv.setText(yesBtn);
            contentTv.setText(content);
            yesBtnTv.setOnClickListener(view1 -> listener.doYes());
        });
    }

    //列表
    public static Dialog showListDialog(Context context,RecyclerBean... recyclerBeans) {
        return DialogHelper.showDownDialog(context, R.layout.wx_dialog_list_layout, R.style.wx_dialog_list, view -> {
            RecyclerView recyclerLy = view.findViewById(R.id.recyclerLy);
            WxListAdapter wxListAdapter = new WxListAdapter(Arrays.asList(recyclerBeans),context);
            recyclerLy.setLayoutManager(new LinearLayoutManager(context));
            recyclerLy.setAdapter(wxListAdapter);
        });
    }

    //等待框
    public static Dialog showLoadingDialog(Context context,String... title) {
        return DialogHelper.showRadioDialog3(context, R.layout.wx_dialog_loading_layout, R.style.wx_dialog_list, view -> {
            AVLoadingIndicatorView avLoadingView = view.findViewById(R.id.imageRes);
            TextView tipTextView = view.findViewById(R.id.tipTxt);
            tipTextView.setText(title == null ? "加载中..." : title[0]);
            avLoadingView.setIndicator("BallClipRotateIndicator");
        });
    }

}
