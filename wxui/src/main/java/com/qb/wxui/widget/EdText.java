package com.qb.wxui.widget;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.qb.wxbase.util.apkutil.SystemUtils;
import com.qb.wxui.dialog.JarvanDialog;
import com.qb.wxui.dialog.util.Bind;
import com.qb.wxui.dialog.util.RecyclerBean;
import com.qb.wxui.widget.listener.OnViewLongWithDialogListener;

import java.util.Objects;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：cn.fox.ui.widget
 * 日    期：2018/9/21
 * 包    名：onese
 * 描    述：
 *      屏蔽掉了系统原生的长按监听事件.
 *      自定义了长按之后的复制粘贴操作.
 *      增加了一些新特性:
 *          1.调用setText()方法之后,光标自动挪动到末尾;
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class EdText extends android.support.v7.widget.AppCompatEditText {
    private OnViewLongWithDialogListener onViewLongWithDialogListener;
    private Dialog dialog;

    public EdText(Context context) {
        super(context);
        init(context);
    }

    public EdText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EdText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化组件
     * @param context 上下文对象
     */
    private void init(Context context) {
        /**
         * 赋予本组件一个全新的长按监听事件,并且封闭掉了系统默认的长按监听事件.
         * 本监听可以在引用组件后通过setOnLongClickListener()方法重置.
         * setOnLongClickListener()方法会覆盖掉本方法.
         */
        this.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                /**
                 * 长按后默认功能为弹出复制粘贴对话框.
                 * 本对话框提供一个自带复制粘贴功能的弹窗,并在内部默认进行了实现.
                 * 可以使用本页面的setOnEdTextLongListener()方法在此监听的基础上进行继续书写,丰富长按监听的操作.
                 */
                dialog = JarvanDialog.showRecyclerListDialog(
                        context,
                        new RecyclerBean(
                                "复制",
                                new Bind.OnclickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        if (EdText.this.getText()!=null){
                                            SystemUtils.copyFor(context,EdText.this.getText().toString());
                                        }
                                    }
                                }
                        ),new RecyclerBean(
                                "粘贴",
                                new Bind.OnclickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        EdText.this.setText(SystemUtils.copySet(context));
                                    }
                                }
                        )
                );
                /**
                 * 如果设置了组件长按监听续,就会调用这个方法onLong.
                 * dialog对象会传递到onLong中.
                 */
                if (onViewLongWithDialogListener !=null) onViewLongWithDialogListener.onLong(dialog);
                return true;
            }
        });
    }

    /**
     * 重写setText()方法,在设置字体之后把光标移动到末尾
     * @param text 文本
     * @param type 类型
     */
    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (text!=null)this.setSelection(this.getText().toString().length());//移动光标
    }

    /**
     * 获取字符串
     * @return String
     */
    public String getTextStr(){
        try{
            return Objects.requireNonNull(getText()).toString();
        }catch (NullPointerException e){
            return "";
        }
    }

    /**
     * 获取int
     * @return int
     */
    public int getNumber(){
        try{
            return Integer.valueOf(Objects.requireNonNull(getText()).toString());
        }catch (NullPointerException | NumberFormatException e){
            return 0;
        }
    }

    /**
     * 设置beforeOnLongClick之后的操作
     * @param onViewLongWithDialogListener OnViewLongWithDialogListener
     */
    public void setOnViewLongWithDialogListener(OnViewLongWithDialogListener onViewLongWithDialogListener) {
        this.onViewLongWithDialogListener = onViewLongWithDialogListener;
    }
}
