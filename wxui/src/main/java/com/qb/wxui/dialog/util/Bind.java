package com.qb.wxui.dialog.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Set;

/**
 * 绑定操作接口
 */
public class Bind {

    /**
     *绑定视图
     */
    public interface BindView{
        /**
         * 视图绑定方法
         * @param view 视图对象
         */
        void bindView(View view);
    }

    /**
     * 绑定数据
     */
    public interface BindData{
        /**
         * 数据绑定方法
         * @param recyclerView RecyclerView列表对象
         */
        void bindData(RecyclerView recyclerView);
    }

    /**
     * 提示按钮点击监听
     */
    public interface OnAffirmClickListener {
        /**
         * 确定
         */
        void onConfirm();

        /**
         * 取消
         */
        void onCancel();
    }

    /**
     * 单选监听
     */
    public interface OnRadioSelectListener{
        /**
         * 单项选择方法
         * @param view 视图对象
         * @param pos 选择后的索引
         */
        void onRadioSelect(View view, int pos);
    }

    /**
     * 多选监听
     */
    public interface OnCheckedSelectListener{
        /**
         * 多项选择方法
         * @param view 视图对象
         * @param checkItems 选中项列表
         * @param unCheckItems 非选中项列表
         */
        void onCheckedSelect(View view, Set<String> checkItems, Set<String> unCheckItems);
    }

    /**
     * 文本框
     */
    public interface OnEdTextListener{
        /**
         * 点击确定后的输入结果回调
         * @param chat 输入的字符串
         */
        void onTextChangeOk(String chat);

        /**
         * 无效的输入方式
         * @param isCancel 是否是点击的取消按钮
         *                 true:用户主动的点击了取消按钮
         *                 false:用户输入的内容为空时,或者空字符,或者输入空格字符时,并且用户点击了确定按钮
         */
        void onTextChangeCancel(boolean isCancel);
    }

    /**
     * 按钮点击事件
     */
    public interface OnclickListener extends View.OnClickListener{
    }
}