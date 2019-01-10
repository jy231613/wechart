package com.qb.wxbase.create.speasy;

import android.content.Context;

import com.qb.wxbase.create.singlist.SingUltimately;
import com.qb.wxbase.create.speasy.base.SharedPreferencesTrait;
import com.qb.wxbase.create.speasy.base.SpGet;

import java.lang.reflect.Field;

import static com.qb.wxbase.create.speasy.SpEasy.getSingle;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：lovey
 * 日    期：2019/1/4
 * 包    名：cn.secret.base.created.speasy2
 * 描    述：
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class Sp {
    //静态标记,标记当前的对象
    private static SingUltimately<Class> clss;
    private static SingUltimately<Object> objs;

    /**
     * 初始化方法
     * @param context 上下文关系
     */
    public static void init(Context context){
        init(context);
    }
    public static void init(Context context, SpEasy.SpException spException){
        init(context,spException);
    }

    /**
     * 销毁标记
     * @param cls 标记1
     * @param object 标记2
     */
    public static void destroy(Class cls,Object object){
        clss.remove(cls);
        objs.remove(object);
    }

    /**
     * 设置
     * @param cls 当前类class
     * @param object 当前类对象
     */
    public static void inSet(Class cls,Object object){
        init(cls,object);
        try{
            Field[] fields = cls.getDeclaredFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    SpGet spGet = field.getAnnotation(SpGet.class);
                    if (spGet != null) {
                        //存在注解
                        field.setAccessible(true);//开启操作权限
                        Class tC = field.getType();
                        field.set(object, getSingle().obtainShared(tC));
                    }
                }
            }
        }catch (IllegalAccessException e){
            //调用默认的异常处理处理此异常
            getSingle().getDispose().disposeIllegalAccess(e);
        }
    }

    /**
     * 初始化
     * @param cls 标记1
     * @param object 标记2
     */
    private static void init(Class cls, Object object) {
        if (clss==null)clss = new SingUltimately<>();
        if (objs==null)objs = new SingUltimately<>();
        clss.add(cls);
        objs.add(object);
    }

    /**
     * 保存并刷新
     * @param <T> T
     */
    public static <T extends SharedPreferencesTrait> void inSave(){
        try{
            Field[] fields = clss.get(clss.size()-1).getDeclaredFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    SpGet spGet = field.getAnnotation(SpGet.class);
                    if (spGet != null) {
                        //存在注解
                        field.setAccessible(true);//开启操作权限
                        T t = (T) field.get(objs.get(objs.size()-1));
                        getSingle().saveShared(t);
                    }
                }
            }
        }catch (IllegalAccessException e){
            //调用默认的异常处理处理此异常
            getSingle().getDispose().disposeIllegalAccess(e);
        }
    }
}
