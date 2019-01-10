package com.qb.wxui.dialog.init;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：cn.fox.ui.dialog.init
 * 日    期：2018/9/17
 * 包    名：onese
 * 描    述：Fox的Dialog集成类
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class FoxDialog {
    /**
     * 配置类信息
     */
    private static DialogConfig config;
    public static void init(DialogConfig config){
        if (config==null)FoxDialog.config = new DialogConfig();
        else FoxDialog.config = config;
    }
    public static void init(){
        FoxDialog.config = new DialogConfig();
    }

    /**
     * 提示框
     */
    public static class Affirm{
    }

    /**
     * 文本输入框
     */
    public static class EdText{
    }

    /**
     * 列表框
     */
    public static class VList{
    }

    /**
     * 图片弹出框
     */
    public static class Image{
    }

    /**
     * 单选框
     */
    public static class Radio{
    }

    /**
     * 多选框
     */
    public static class Check{
    }

    /**
     * 等待对话框
     */
    public static class Loading{
    }
}
