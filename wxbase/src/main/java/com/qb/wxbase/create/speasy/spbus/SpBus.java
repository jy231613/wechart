package com.qb.wxbase.create.speasy.spbus;

import android.util.Log;

import com.qb.wxbase.create.speasy.SpEasy;
import com.qb.wxbase.create.speasy.spbus.EasyDataTrait;

import java.lang.reflect.InvocationTargetException;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：cades
 * 日    期：2018/11/1
 * 包    名：cn.secret.base.created.foxbus
 * 描    述：记录简单数据
 *          对于数据采取替换方案
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class SpBus implements SpEasy.SpException {

    /**
     * 保存一个信息
     * @param object OBJ
     */
    public static void post(Object object){
        EasyDataTrait easyDataTrait = SpEasy.getSingle().obtainShared(EasyDataTrait.class);
        if (object.getClass() == int.class){
            easyDataTrait.setId((Integer) object);
        }else if (object.getClass() == String.class){
            easyDataTrait.setStr(String.valueOf(object));
        }else if (object.getClass() == double.class) {
            easyDataTrait.setMoney((Double) object);
        }else if (object.getClass() == boolean.class){
            easyDataTrait.setBool((Boolean) object);
        }
        SpEasy.getSingle().saveShared(easyDataTrait);
    }

    /**
     * 保存Id
     * @param id
     */
    public static void postId(int id){
        EasyDataTrait easyDataTrait = SpEasy.getSingle().obtainShared(EasyDataTrait.class);
        easyDataTrait.setId(id);
        SpEasy.getSingle().saveShared(easyDataTrait);
    }

    /**
     * 保存索引
     * @param index
     */
    public static void postIndex(int index){
        EasyDataTrait easyDataTrait = SpEasy.getSingle().obtainShared(EasyDataTrait.class);
        easyDataTrait.setIndex(index);
        SpEasy.getSingle().saveShared(easyDataTrait);
    }

    /**
     * 保存手机号
     * @param phone
     */
    public static void postPhone(String phone){
        EasyDataTrait easyDataTrait = SpEasy.getSingle().obtainShared(EasyDataTrait.class);
        easyDataTrait.setPhone(phone);
        SpEasy.getSingle().saveShared(easyDataTrait);
    }

    /**
     * 保存字符串
     * @param str
     */
    public static void postStr(String str){
        EasyDataTrait easyDataTrait = SpEasy.getSingle().obtainShared(EasyDataTrait.class);
        easyDataTrait.setStr(str);
        SpEasy.getSingle().saveShared(easyDataTrait);
    }

    /**
     * 保存状态
     * @param status
     */
    public static void postStatus(int status){
        EasyDataTrait easyDataTrait = SpEasy.getSingle().obtainShared(EasyDataTrait.class);
        easyDataTrait.setStatus(status);
        SpEasy.getSingle().saveShared(easyDataTrait);
    }

    /**
     * 保存类型
     * @param type
     */
    public static void postType(int type){
        EasyDataTrait easyDataTrait = SpEasy.getSingle().obtainShared(EasyDataTrait.class);
        easyDataTrait.setType(type);
        SpEasy.getSingle().saveShared(easyDataTrait);
    }

    /**
     * 保存地址
     * @param url
     */
    public static void postUrl(String url){
        EasyDataTrait easyDataTrait = SpEasy.getSingle().obtainShared(EasyDataTrait.class);
        easyDataTrait.setUrl(url);
        SpEasy.getSingle().saveShared(easyDataTrait);
    }

    /**
     * 保存地址
     * @param bool
     */
    public static void postBool(boolean bool){
        EasyDataTrait easyDataTrait = SpEasy.getSingle().obtainShared(EasyDataTrait.class);
        easyDataTrait.setBool(bool);
        SpEasy.getSingle().saveShared(easyDataTrait);
    }

    /**
     * 获取对象
     * @return
     */
    public static EasyDataTrait reception(){
        EasyDataTrait easyDataTrait = SpEasy.getSingle().obtainShared(EasyDataTrait.class);
        return easyDataTrait!=null?easyDataTrait:new EasyDataTrait();
    }

    @Override
    public void disposeIllegalAccess(IllegalAccessException e) {
        Log.d("SpBus", "disposeIllegalAccess: >>> IllegalAccessException");
    }

    @Override
    public void disposeInstantiation(InstantiationException e) {
        Log.d("SpBus", "disposeInstantiation: >>> InstantiationException");
    }

    @Override
    public void disposeInvocationTarget(InvocationTargetException e) {
        Log.d("SpBus", "disposeInvocationTarget: >>> InvocationTargetException");
    }
}
