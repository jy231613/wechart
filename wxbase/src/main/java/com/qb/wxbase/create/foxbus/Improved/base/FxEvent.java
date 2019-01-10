package com.qb.wxbase.create.foxbus.Improved.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：lovey
 * 日    期：2019/1/5
 * 包    名：cn.secret.base.created.foxbus.Improved
 * 描    述：数据传递注解(数据绑定在方法上)
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
@Retention(RetentionPolicy.RUNTIME)//生命周期一直存在
@Target( { ElementType.METHOD})//应用于方法
public @interface FxEvent {
    /**
     * tag标记
     * @return 字符串唯一标识
     */
    String value() default "foxBus";
}
