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
 * 描    述：标识表的主键,一个Table类中必须有至少一个SystemId注解,否则会抛出异常,如果存在多个,会赋相同的值;
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
@Retention(RetentionPolicy.RUNTIME)//生命周期一直存在
@Target( { ElementType.FIELD})//应用于参数
public @interface SystemId {
    /**
     * 是否自增,默认为true
     * @return true自增
     */
    boolean value() default true;
}
