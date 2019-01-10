package com.qb.wxbase.create.foxbind;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.qb.wxbase.create.foxbus.Improved.FxManage;
import com.qb.wxbase.create.foxbus.Improved.base.FxGet;

import java.lang.reflect.Field;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/8
 * 包    名：com.qb.wxbase.create.foxbind
 * 描    述：fox绑定视图
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class FoxFindBind {

    /**
     * 绑定方法
     * @param cls 类
     * @param obj 当前对象
     * @param view 当前视图
     */
    public static void bind(Class cls,Object obj,View view){
        try {
            //扫描成员变量
            Field[] fields = cls.getDeclaredFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    Find find = field.getAnnotation(Find.class);
                    if (find != null) {
                        //存在注解
                        field.setAccessible(true);//开启操作权限
                        field.set(obj,view.findViewById(find.value()));//赋值
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 绑定方法
     * @param cls 类
     * @param obj 当前对象
     * @param activity 当前视图
     */
    public static void bind(Class cls,Object obj,Activity activity){
        try {
            //扫描成员变量
            Field[] fields = cls.getDeclaredFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    Find find = field.getAnnotation(Find.class);
                    if (find != null) {
                        //存在注解
                        field.setAccessible(true);//开启操作权限
                        field.set(obj,activity.findViewById(find.value()));//赋值
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
