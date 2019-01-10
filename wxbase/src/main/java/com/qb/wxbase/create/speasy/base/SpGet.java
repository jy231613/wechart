package com.qb.wxbase.create.speasy.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：lovey
 * 日    期：2019/1/4
 * 包    名：cn.secret.base.created.speasy2
 * 描    述：SpEasy获取类参数注解
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
@Retention(RetentionPolicy.RUNTIME)//生命周期一直存在
@Target( { ElementType.FIELD})//应用于参数
public @interface SpGet {
//    Class value();
}
