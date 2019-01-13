package com.qb.wxbase.create.sql.note;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/13
 * 包    名：com.qb.wxbase.create.sql.note
 * 描    述：标识当前类创建为数据库表文件
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
@Retention(RetentionPolicy.RUNTIME)//生命周期一直存在
@Target( { ElementType.TYPE})//应用于类
public @interface Table {
    /**
     * 表名称,默认使用类名称
     * @return 表名
     */
    String value() default "";
}
