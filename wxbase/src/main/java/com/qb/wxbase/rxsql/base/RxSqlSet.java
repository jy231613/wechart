package com.qb.wxbase.rxsql.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechart
 * 日    期：2019/1/19
 * 包    名：com.qb.wxbase.rxsql
 * 描    述：标注用于获取查询值得方法
 * Create by zFox from AndroidStudio3.2
 * ================================================
 */
@Retention(RetentionPolicy.RUNTIME)//生命周期一直存在
@Target( { ElementType.METHOD})//应用于方法
public @interface RxSqlSet {
    Class<?> value();
    String where() default "";
    String[] values() default {};
    int ids() default 0;
}
