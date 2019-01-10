package com.qb.wxbase.create.foxbus;

import android.util.Log;

import com.qb.wxbase.create.foxbus.Improved.FxEventBean;
import com.qb.wxbase.create.foxbus.Improved.FxManage;
import com.qb.wxbase.create.foxbus.Improved.base.FxEvent;
import com.qb.wxbase.create.foxbus.Improved.base.FxGet;
import com.qb.wxbase.create.speasy.SpEasy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：lovey
 * 日    期：2019/1/5
 * 包    名：cn.secret.base.created.foxbus.Improved
 * 描    述：FoxBus增强版,使用注解与反射刷新页面数据
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class FxBus {

    /**
     * 当前页绑定事件
     * @param obj 当前对象
     *            注意:
     *              绑定成员变量时建议在变量调用之前执行绑定操作;
     *              绑定方法建议在视图加载完成之后绑定;
     */
    public static void bind(Class cls,Object obj){
        try{
            //扫描成员变量
            Field[] fields = cls.getDeclaredFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    FxGet fxGet = field.getAnnotation(FxGet.class);
                    if (fxGet != null) {
                        //存在注解
                        field.setAccessible(true);//开启操作权限
                        if (FxManage.getFxManage().get(fxGet.value())!=null)
                        field.set(obj, FxManage.getFxManage().get(fxGet.value()));
                    }
                }
            }
            //扫描方法
            Method[] methods = cls.getDeclaredMethods();
            if (methods!=null&&methods.length>0){
                for (Method method:methods) {
                    FxEvent fxEvent = method.getAnnotation(FxEvent.class);
                    if (fxEvent!=null){
                        //存在注解
                        method.setAccessible(true);
                        if (FxManage.getFxManage().get(fxEvent.value())!=null)
                        method.invoke(obj,FxManage.getFxManage().get(fxEvent.value()));
                    }
                }
            }
        }catch (IllegalAccessException e){
            //调用默认的异常处理处理此异常
            SpEasy.getSingle().getDispose().disposeIllegalAccess(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送事件
     * @param beans 对象集
     */
    public static void post(FxEventBean... beans){
        if (beans.length>10){
            FxManage.cache = beans.length;
        }else FxManage.cache = 10;
        for (FxEventBean bean:beans) {
            FxManage.getFxManage().add(bean);
        }
    }
}
