package com.qb.wxbase.util.apkutil;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：cn.secret.base.util.apkutil
 * 日    期：2018/9/21
 * 包    名：onese
 * 描    述：系统工具调用类
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class SystemUtils {

    /**
     * 设置窗体透明度
     *
     * @param activity Activity
     * @param zo       alpha在0.0f到1.0f之间。0.0f完全不透明，1.0完全透明
     */
    public static void setWindowDiaphaneity(Activity activity, float zo) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = zo;
        activity.getWindow().setAttributes(lp);
    }

    /**
     * 设置黑暗度
     * @param activity Activity
     * @param dimAmount dimAmount在0.0f和1.0f之间，0.0f完全不暗，1.0f全暗
     */
    public static void setWindowDimAmount(Activity activity, float dimAmount) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.dimAmount = dimAmount;
        activity.getWindow().setAttributes(lp);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    /**
     * 设置背景模糊
     * @param activity Activity
     */
    public static void setFlags(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
    }

    /**
     * 获取粘贴数据
     *
     * @param context 上下文对象
     * @return 粘贴的第一条数据
     */
    public static String copySet(Context context) {
        // 获取系统剪贴板
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 获取剪贴板的剪贴数据集
        assert clipboard != null;
        ClipData clipData = clipboard.getPrimaryClip();
        if (clipData != null && clipData.getItemCount() > 0) {
            // 从数据集中获取（粘贴）第一条文本数据
            CharSequence text = clipData.getItemAt(0).getText();
            return text.toString();
        }
        return "";
    }

    /**
     * 把一条数据粘贴到系统的剪切板
     *
     * @param context 上下文对象
     * @param string  需要复制的数据
     */
    public static void copyFor(Context context, String string) {
        // 获取系统剪贴板
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建一个剪贴数据集，包含一个普通文本数据条目（需要复制的数据）
        ClipData clipData = ClipData.newPlainText(null, string);
        // 把数据集设置（复制）到剪贴板
        assert clipboard != null;
        clipboard.setPrimaryClip(clipData);
    }

    /**
     * 获取系统状态栏的高度
     *
     * @param context 上下文关系
     * @return int
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = context.getResources().getDimensionPixelOffset(resId);
        }
        return result;
    }

    /**
     * 通过设置全屏，设置状态栏透明
     *
     * @param activity Activity
     */
    public static void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                //设置状态栏为透明，否则在部分手机上会呈现系统默认的浅灰色
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以考虑设置为透明色
                //window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
//                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }

    /**
     * 得到json文件中的内容
     *
     * @param context  上下文
     * @param fileName 文件名
     * @return String
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 设置状态栏颜色
     *
     * @param statusColor 颜色
     * @param activity    Activity
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarColor(int statusColor, Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            //取消设置Window半透明的Flag
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //添加Flag把状态栏设为可绘制模式
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏为透明
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 设置顶部状态栏字体颜色
     *
     * @param activity       Activity
     * @param lightStatusBar true为黑色,false为白色
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static void setTopFontColor(Activity activity, boolean lightStatusBar) {
        Window window = activity.getWindow();
        View decor = window.getDecorView();
        int ui = decor.getSystemUiVisibility();
        if (lightStatusBar) {
            ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
        } else {
            ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体颜色为白色
        }
        decor.setSystemUiVisibility(ui);
    }
}