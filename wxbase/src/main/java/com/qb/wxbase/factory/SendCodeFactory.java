package com.qb.wxbase.factory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：zcapp
 * 日    期：2018/8/21 0021--16:18--星期二
 * 包    名：cn.app.zc.utils
 * 描    述：发送验证码倒计时封装类
 * Create by Administrator from AndroidStudio3.1
 * ================================================
 */
public class SendCodeFactory {
    private static int TIMER_MIAO = 60;//默认延时60秒
    private static int miao = TIMER_MIAO;//延时操作对象
    private static Disposable kongZhiQi;//延时操作对象
    private static boolean isRequestCode = true;//是否允许获取验证码

    public static String errorInfo = "";//错误信息

    public static void setTimerMiao(int timerMiao) {
        TIMER_MIAO = timerMiao;
        miao = TIMER_MIAO;
    }

    /**
     * 发送短信
     * @param phone 手机号
     * @param listener 监听事件
     */
    public static void sendCode(String phone,OnSendCodeListener listener){
        if (isRequestCode) {
            listener.request(phone,listener);
        } else {
            //计时中
            listener.inRequestCode(miao);
        }
    }

    /**
     * 计时操作
     * @param listener 监听事件
     */
    public static void timerDispose(OnSendCodeListener listener) {
//        if (!kongZhiQi.isDisposed())return;
        isRequestCode = false;
        kongZhiQi = Observable.interval(1,TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (miao == 0) {
                        //计时结束
                        isRequestCode = true;
                        miao = TIMER_MIAO;//重置时间
                        kongZhiQi.dispose();//停止计时器
                        listener.onFinnish();
                    } else {
                        //每秒操作
                        miao--;
                        listener.onMiao(miao);
                    }
                });
    }

    /**
     * 销毁操作
     */
    public static void destroy(){
        isRequestCode = true;
        miao = TIMER_MIAO;//重置时间
        if (kongZhiQi!=null)kongZhiQi.dispose();//解绑
    }

    /**
     * 验证码发送监听
     */
    public interface OnSendCodeListener{
        /**
         * 正在计时监听
         * @param miao 当前倒计时秒数
         */
        void inRequestCode(int miao);

        /**
         * 每秒操作
         * @param miao 当前倒计时秒数
         */
        void onMiao(int miao);

        /**
         * 计时结束操作
         */
        void onFinnish();

        /**
         * 验证码网络请求
         * @param phone 手机号
         * @param listener 当前监听
         */
        void request(String phone, OnSendCodeListener listener);
    }

}
