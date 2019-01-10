package com.qb.wxui.dialog;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qb.wxui.R;

/**
 * Toast封装类,相当于提示信息
 */
public class MyToast {

    private static Toast toast;

    /**
     * 初始化toast
     *
     * @param context
     * @param resId
     * @param msg
     * @return
     */
    private static Toast initToast(Context context, @DrawableRes int resId, @NonNull String msg) {

        View view = LayoutInflater.from(context).inflate(R.layout.my_toast, null);
        ImageView img_toast = (ImageView) view.findViewById(R.id.img_toast);
        TextView tv_toast = (TextView) view.findViewById(R.id.tv_toast);
        img_toast.setImageResource(resId);
        tv_toast.setText(msg);

        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);

        return toast;
    }


    /**
     * showToast
     *
     * @param context
     * @param resId
     * @param msg
     */
    private static void show(Context context, @DrawableRes int resId, @NonNull String msg) {
        if (toast == null) {
            initToast(context, resId, msg);
        } else {
            changeText(resId, msg);
        }
        toast.show();
    }


    /**
     * 改变toast的内容
     *
     * @param msg
     */
    private static void changeText(@DrawableRes int resId, @NonNull String msg) {
        TextView textView = (TextView) toast.getView().findViewById(R.id.tv_toast);
        ImageView imageView = (ImageView) toast.getView().findViewById(R.id.img_toast);
        imageView.setImageResource(resId);
        textView.setText(msg);
    }


    /**
     * 给外面调用的三种toast
     */

    //成功toast
    public static void showSuccess(Context context, @NonNull String msg) {
        show(context, R.drawable.ic_toast_correct, msg);
    }

    //失败toast
    public static void showError(Context context, @NonNull String msg) {
        show(context, R.drawable.ic_toast_error, msg);
    }


}
