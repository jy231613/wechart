package com.qb.wxui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qb.wxui.R;
import com.qb.wxui.dialog.adapter.CheckedSelectAdapter;
import com.qb.wxui.dialog.adapter.RadioSelectAdapter;
import com.qb.wxui.dialog.adapter.TextListAdapter;
import com.qb.wxui.dialog.util.Bind;
import com.qb.wxui.dialog.util.DialogHelper;
import com.qb.wxui.dialog.util.DialogStyle;
import com.qb.wxui.dialog.util.InputTypeEnum;
import com.qb.wxui.dialog.util.MsgDialogClickListener;
import com.qb.wxui.dialog.util.RecyclerBean;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：src
 * 日    期：2018/8/25 0025--4:58--星期六
 * 包    名：cn.fox.ui.dialog
 * 描    述：嘉文四世Dialog -_-!
 * Create by Administrator from AndroidStudio3.1
 * ================================================
 */
public class JarvanDialog {
    /**
     * 显示一个提示对话框--title默认为"提示"
     *
     * @param context  上下文
     * @param content  内容
     * @param listener 点击监听,默认没有实现Dismiss方法
     * @return Dialog
     */
    public static Dialog showAffirmDialog(Context context, String content, MsgDialogClickListener listener) {
        return showAffirmDialog(context, "提示", content, listener);
    }

    /**
     * 显示一个提示对话框2--没有Title
     *
     * @param context  上下文
     * @param content  内容
     * @param listener 点击监听,默认没有实现Dismiss方法
     * @return Dialog
     */
    public static Dialog showAffirmDialogNoTitle(Context context, String content, MsgDialogClickListener listener) {
        return showAffirmDialog(context, null, content, listener);
    }

    public static Dialog showAffirmDialog(Context context, String title, String content, MsgDialogClickListener listener) {
        return DialogHelper.showRadioDialog(context, R.layout.dialog_affirm_layout, R.style.ActionSheetDialogStylePadding, view -> {
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
            contentTv.setText(content);
            yesBtnTv.setOnClickListener(view1 -> listener.doYes());
            noBtnTv.setOnClickListener(view12 -> listener.doNo());
        });
    }

    /**
     * 显示一个列表对话框
     *
     * @param context  上下文关系
     * @param bindData 为RecyclerView绑定数据
     * @return Dialog
     */
    public static Dialog showRecyclerListDialog(Context context, Bind.BindData bindData) {
        return DialogHelper.showRadioDialog(context, R.layout.dialog_recycler_layout, R.style.ActionSheetDialogStylePadding, view -> {
            RecyclerView recyclerView = view.findViewById(R.id.recyclerLy);
            bindData.bindData(recyclerView);
        });
    }

    /**
     * 显示一个列表对话框
     *
     * @param context 上下文关系
     * @return Dialog
     */
    public static Dialog showRecyclerListDialog(Context context, RecyclerBean... beans) {
        return DialogHelper.showRadioDialog(context, R.layout.dialog_recycler_layout, R.style.ActionSheetDialogStyle2, view -> {
            RecyclerView recyclerView = view.findViewById(R.id.recyclerLy);
            List<RecyclerBean> lists = new ArrayList<>(Arrays.asList(beans));
            TextListAdapter adapter = new TextListAdapter(lists, context);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(adapter);
        });
    }

    /**
     * 显示一个文本输入框对话框
     *
     * @param context  上下文关系
     * @param listener 监听器
     * @return Dialog
     */
    public static Dialog showEdTextDialog(Context context, String title, String hint, InputTypeEnum inputTypeEnum, Bind.OnEdTextListener listener) {
        return DialogHelper.showRadioDialog(context, R.layout.dialog_edtext_layout, R.style.ActionSheetDialogStylePadding, view -> {
            TextView titleTv = view.findViewById(R.id.title);
            EditText contentTv = view.findViewById(R.id.content);
            TextView yesBtnTv = view.findViewById(R.id.yesBtn);
            TextView noBtnTv = view.findViewById(R.id.noBtn);
            if (title == null) {
                titleTv.setVisibility(View.GONE);
            } else {
                titleTv.setVisibility(View.VISIBLE);
                titleTv.setText(title);
            }
            contentTv.setInputType(inputTypeEnum.getTypeClassText());
            contentTv.setHint(hint);
            yesBtnTv.setOnClickListener(view1 -> {
                if (contentTv.getText() == null || //为空时
                        contentTv.getText().toString().equals("") || //为空字符时
                        contentTv.getText().toString().trim().equals("") //去空格之后为空时
                        ) {
                    listener.onTextChangeCancel(false);
                } else {
                    listener.onTextChangeOk(contentTv.getText().toString());
                }
            });
            noBtnTv.setOnClickListener(view2 -> listener.onTextChangeCancel(true));
        });
    }


    /**
     * 显示一个图片对话框
     *
     * @param context 上下文关系
     * @return Dialog
     */
    public static Dialog showImageDialog(Context context, Bitmap bitmap) {
        return DialogHelper.showRadioDialog(context, R.layout.dialog_image_layout, R.style.LoadingDialogStyle, view -> {
            ImageView image = view.findViewById(R.id.img);
            image.setImageBitmap(bitmap);
        });
    }

    /**
     * 显示一个图片对话框
     *
     * @param context 上下文关系
     * @return Dialog
     */
    public static Dialog showImageDialog(Context context, String url) {
        return DialogHelper.showRadioDialog(context, R.layout.dialog_image_layout, R.style.LoadingDialogStyle, view -> {
            ImageView image = view.findViewById(R.id.img);
//            GlideUtils.load(context, url, image);
        });
    }

    /**
     * 显示一个大的图片对话框
     *
     * @param context 上下文关系
     * @return Dialog
     */
    public static Dialog showBigImageDialog(Context context, String url) {
        return DialogHelper.showRadioDialog(context, R.layout.dialog_big_image_layout, R.style.LoadingDialogStyle, view -> {
            ImageView image = view.findViewById(R.id.img);
//            GlideUtils.load(context, url, image);
        });
    }

    /**
     * 显示一个单选列表
     *
     * @param context  上下文关系
     * @param listener 监听事件
     * @return Dialog
     */
    public static Dialog showRadioListDialog(Context context, String title, int moren, List<String> items, Bind.OnRadioSelectListener listener) {
        return DialogHelper.showRadioDialog(context, R.layout.dialog_radio_layout, R.style.DialogStyle, view -> {
            RecyclerView recyclerView = view.findViewById(R.id.recyclerLy);
            TextView titleText = view.findViewById(R.id.titleText);
            TextView okBtn = view.findViewById(R.id.okBtn);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            titleText.setText(title);
            okBtn.setVisibility(View.GONE);
            RadioSelectAdapter selectAdapter = new RadioSelectAdapter(items, moren, context);
            selectAdapter.setOnItemClickListener((view1, type, obj) -> listener.onRadioSelect(view1, (Integer) obj));
            recyclerView.setAdapter(selectAdapter);
        });
    }

    /**
     * 显示一个单选列表
     *
     * @param context  上下文关系
     * @param listener 监听事件
     * @return Dialog
     */
    public static Dialog showRadioListDialog(Context context, String title, int moren, String[] strs, Bind.OnRadioSelectListener listener) {
        return DialogHelper.showRadioDialog(context, R.layout.dialog_radio_layout, R.style.DialogStyle, view -> {
            RecyclerView recyclerView = view.findViewById(R.id.recyclerLy);
            TextView titleText = view.findViewById(R.id.titleText);
            TextView okBtn = view.findViewById(R.id.okBtn);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            titleText.setText(title);
            okBtn.setVisibility(View.GONE);
            RadioSelectAdapter selectAdapter = new RadioSelectAdapter(Arrays.asList(strs), moren, context);
            selectAdapter.setOnItemClickListener((view1, type, obj) -> listener.onRadioSelect(view1, (Integer) obj));
            recyclerView.setAdapter(selectAdapter);
        });
    }

    /**
     * 显示一个单选列表
     *
     * @param context  上下文关系
     * @param listener 监听事件
     * @return Dialog
     */
    public static Dialog showRadioListDialog(Context context, String title, int moren, Set<String> items, Bind.OnRadioSelectListener listener) {
        return DialogHelper.showRadioDialog(context, R.layout.dialog_radio_layout, R.style.DialogStyle, view -> {
            RecyclerView recyclerView = view.findViewById(R.id.recyclerLy);
            TextView titleText = view.findViewById(R.id.titleText);
            TextView okBtn = view.findViewById(R.id.okBtn);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            titleText.setText(title);
            okBtn.setVisibility(View.GONE);
            RadioSelectAdapter selectAdapter = new RadioSelectAdapter(items, moren, context);
            selectAdapter.setOnItemClickListener((view1, type, obj) -> listener.onRadioSelect(view1, (Integer) obj));
            recyclerView.setAdapter(selectAdapter);
        });
    }

    /**
     * 显示一个多选列表
     *
     * @param context  上下文关系
     * @param listener 监听事件
     * @return Dialog
     */
    public static Dialog showCheckedListDialog(Context context, String title, List<String> oldItems, List<String> items, Bind.OnCheckedSelectListener listener) {
        return DialogHelper.showRadioDialog(context, R.layout.dialog_radio_layout, R.style.DialogStyle, view -> {
            RecyclerView recyclerView = view.findViewById(R.id.recyclerLy);
            TextView titleText = view.findViewById(R.id.titleText);
            TextView okBtn = view.findViewById(R.id.okBtn);
            recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
            titleText.setText(title);
            okBtn.setVisibility(View.VISIBLE);
            CheckedSelectAdapter selectAdapter = new CheckedSelectAdapter(items, oldItems, context);
            Set<String> newItems = new HashSet<>();
            Set<String> unCheckItems = new HashSet<>();
            newItems.addAll(oldItems);
            selectAdapter.setListener((compoundButton, b) -> {
                String s = compoundButton.getText().toString();
                if (b) {
                    newItems.add(s);
                    unCheckItems.remove(s);
                } else {
                    newItems.remove(s);
                    unCheckItems.add(s);
                }
            });
            okBtn.setOnClickListener(view1 -> {
                listener.onCheckedSelect(view, newItems, unCheckItems);
            });
            recyclerView.setAdapter(selectAdapter);
        });
    }

    /**
     * 显示一个默认样式Loading对话框
     *
     * @param context
     * @return
     */
    public static Dialog showLoadingDialog(Context context) {
        Dialog dialog = showLoadingDialog(context, null, DialogStyle.LoadingStyle.square_loading, DialogStyle.LoadingIconStyle.BallBeatIndicator);
        dialog.setCanceledOnTouchOutside(false);//设置点击阴影不消失
        return dialog;
    }

    /**
     * 显示一个默认样式Loading对话框
     *
     * @param context
     * @param title
     * @return
     */
    public static Dialog showLoadingDialog(Context context, String title) {
        Dialog dialog = showLoadingDialog(context, title, DialogStyle.LoadingStyle.square_loading, DialogStyle.LoadingIconStyle.BallBeatIndicator);
        dialog.setCanceledOnTouchOutside(false);//设置点击阴影不消失
        return dialog;
    }

    public static Dialog refreshDialog(Context context, String v, String refrestDate, String refreshBig, String refreshMsg, boolean isMust, MsgDialogClickListener clickListener) {
        Dialog dialog = DialogHelper.showRadioDialog(context,
                R.layout.dialog_refresh,
                R.style.RefreshDialogStyle,
                view -> {
                    TextView refreshDate = view.findViewById(R.id.refreshDate);
                    TextView refreshBate = view.findViewById(R.id.refreshBate);
                    TextView refreshMsgText = view.findViewById(R.id.refreshMsg);
                    TextView okBtn = view.findViewById(R.id.okBtn);
                    TextView noBtn = view.findViewById(R.id.noBtn);
                    //                    noBtn.setVisibility(isMust?View.INVISIBLE:View.VISIBLE);
                    okBtn.setOnClickListener(vyes -> {
                        clickListener.doYes();
                    });
                    if (isMust) {
                        noBtn.setText("本次更新为必更版本");
                        noBtn.setTextColor(context.getResources().getColor(R.color.red_cff51));
                        noBtn.setOnClickListener(vno -> {
                            clickListener.doYes();
                        });
                    } else {
                        noBtn.setText("暂不更新");
                        noBtn.setTextColor(context.getResources().getColor(R.color.text_grey));
                        noBtn.setOnClickListener(vno -> {
                            clickListener.doNo();
                        });
                    }

                    refreshDate.setText(refrestDate);
                    refreshBate.setText("v$v".replace("$v", v));
                    refreshMsgText.setText(refreshMsg == null ? "优化了用户体验!" : refreshMsg);
                });
        dialog.setCanceledOnTouchOutside(false);//设置点击阴影不消失
        return dialog;
    }

    /**
     * 获取更新对话框
     *
     * @param context       上下文关系
     * @param v             更新版本号
     * @param refrestDate   更新日期
     * @param refreshMsg    更新日志
     * @param isMust        是否必须更新
     * @param clickListener 点击事件
     * @return 返回
     */
    public static Dialog refreshDialog(Context context, String v, String refrestDate, String refreshMsg, boolean isMust, MsgDialogClickListener clickListener) {
        return refreshDialog(context, v, refrestDate, null, refreshMsg, isMust, clickListener);
    }

    /**
     * 显示一个图片等待对话框
     *
     * @param context 上下文关系
     * @return Dialog
     */
    public static Dialog showLoadingDialog(Context context, String title, @DrawableRes int drables) {
        Dialog dialog = DialogHelper.showRadioDialog(context, R.layout.dialog_loading_anim_layout, R.style.LoadingDialogStyle, view -> {
            TextView tv = view.findViewById(R.id.tipTxt);
            ImageView imageRes = view.findViewById(R.id.imageRes);
            tv.setText(title);
            imageRes.setBackgroundResource(drables);
            AnimationDrawable animationDrawable = (AnimationDrawable) imageRes.getBackground();
            animationDrawable.start();
        });
        dialog.setCanceledOnTouchOutside(false);//设置点击阴影不消失
        return dialog;
    }

    /**
     * 显示一个图片等待对话框
     *
     * @param context 上下文关系
     * @return Dialog
     */
    public static Dialog showLoadingDialog(Context context, @DrawableRes int drables) {
        return showLoadingDialog(context, "正在加载...", drables);
    }

    /**
     * 显示一个默认样式Loading对话框
     *
     * @param context
     * @param title
     * @param isCanceled 是否设置点击阴影不消失,true消失
     * @return
     */
    public static Dialog showLoadingDialog(Context context, String title, boolean isCanceled) {
        Dialog dialog = showLoadingDialog(context, title, DialogStyle.LoadingStyle.square_loading, DialogStyle.LoadingIconStyle.BallBeatIndicator);
        dialog.setCanceledOnTouchOutside(isCanceled);//设置点击阴影不消失
        return dialog;
    }

    /**
     * 显示一个Loading对话框
     *
     * @param context      上下文
     * @param title        文字显示
     * @param loadingStyle 样式
     * @param iconStyle    图标
     * @return Dialog
     */
    public static Dialog showLoadingDialog(Context context, String title, DialogStyle.LoadingStyle loadingStyle, DialogStyle.LoadingIconStyle iconStyle) {
        return DialogHelper.showRadioDialog(context,
                loadingStyle.getLayoutRes(),
                R.style.LoadingDialogStyle,
                view -> {
                    AVLoadingIndicatorView avLoadingView = view.findViewById(R.id.avLoadLy);
                    TextView tipTextView = view.findViewById(R.id.tipTxt);
                    tipTextView.setText(title == null ? "加载中..." : title);
                    avLoadingView.setIndicator(iconStyle.getIndicatorName());
                });
    }
}
