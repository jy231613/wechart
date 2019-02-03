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
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;

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
     * 获取ip地址v4
     * @param context 上下文对象
     * @return Ip
     */
    public static String getIP(Context context){
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
                {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        }
        catch (SocketException ex){
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 获取ip地址v6
     * @param context 上下文对象
     * @return Ip
     */
    public static String getIP6(Context context){
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
                {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        }
        catch (SocketException ex){
            ex.printStackTrace();
        }
        return null;
    }

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
     * 得到input流
     * @param context  上下文
     * @param fileName 文件名
     * @return String
     */
    public static InputStream getXmlInPut(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取文件内容
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
        return new ByteArrayInputStream(stringBuilder.toString().getBytes());
    }

    /**
     * 获取包下所有类
     * @param packageName 包名
     * @param context 上下文
     * @return List<String>包名集合
     */
    public static List<String> getClassName(String packageName,Context context){
        List<String>classNameList=new ArrayList<>();
        try {
            DexFile df = new DexFile(context.getPackageCodePath());//通过DexFile查找当前的APK中可执行文件
            Enumeration<String> enumeration = df.entries();//获取df中的元素  这里包含了所有可执行的类名 该类名包含了包名+类名的方式
            while (enumeration.hasMoreElements()) {//遍历
                String className = enumeration.nextElement();
//                if (className.contains(packageName)) {//在当前所有可执行的类里面查找包含有该包名的所有类
//                    classNameList.add(className);
//                }
                classNameList.add(className);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  classNameList;
    }

    public static List<Class<?>> scan(Context ctx, String entityPackage) {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        try {
            PathClassLoader classLoader = (PathClassLoader) Thread
                    .currentThread().getContextClassLoader();
            DexFile dex = new DexFile(ctx.getPackageResourcePath());
            Enumeration<String> entries = dex.entries();
            while (entries.hasMoreElements()) {
                String entryName = entries.nextElement();
                if (entryName.contains(entityPackage)) {
//                    Class<?> entryClass = Class.forName(entryName, true,classLoader);
                    //疑问：<span style="font-size: 1em; line-height: 1.5;">Class.forName(entryName);这种方式不知道为什么返回null，哪位大神知道原因，请指点一下小弟吧  感激不尽</span>
//                    DatabaseTable annotation = entryClass.getAnnotation(DatabaseTable.class);
//                    if (annotation != null) {
//
//                    }
                }
                Class<?> entryClass = Class.forName(entryName, true,ctx.getClassLoader());
                classes.add(entryClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }

    /**
     * 扫描包路径下的所有class文件
     *
     * @param pkgName 包名
     * @param pkgPath 包对应的绝对地址
     * @param classes 保存包路径下class的集合
     */
    public static void findClassesByFile(String pkgName, String pkgPath, Set<Class<?>> classes) {
        File dir = new File(pkgPath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        // 过滤获取目录，or class文件
        File[] dirfiles = dir.listFiles(pathname -> pathname.isDirectory() || pathname.getName().endsWith("class"));
        if (dirfiles == null || dirfiles.length == 0) {
            return;
        }
        String className;
        Class clz;
        for (File f : dirfiles) {
            if (f.isDirectory()) {
                findClassesByFile(pkgName + "." + f.getName(),
                        pkgPath + "/" + f.getName(),
                        classes);
                continue;
            }
            // 获取类名，干掉 ".class" 后缀
            className = f.getName();
            className = className.substring(0, className.length() - 6);
            // 加载类
            clz = loadClass(pkgName + "." + className);
            if (clz != null) {
                classes.add(clz);
            }
        }
    }
    private static Class<?> loadClass(String fullClzName) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(fullClzName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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