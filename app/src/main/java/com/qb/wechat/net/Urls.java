package com.qb.wechat.net;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/10
 * 包    名：com.qb.wechat.net
 * 描    述：请求地址库
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class Urls {
    private final static String IP_CONFIG = "47.110.77.160";
    private final static String PORT_CONFIG = ":9627";
    private final static String NAME_CONFIG = "/charts";
    private final static String Urls = "http://"+IP_CONFIG+PORT_CONFIG+NAME_CONFIG+"%z%c";

    /**
     * 获取Url
     * @param url 地址
     * @return 全地址
     */
    public static String getUrls(String url){
        return Urls.replace("%z%c",url);
    }

    //用户登录
    public final static String USER_LOGIN = "/login.u";
    //用户注册
    public final static String USER_REGISTER = "/register.u";
    //用户退出
    public final static String USER_LOGOUT = "/logout.u";
    //修改密码
    public final static String USER_ALTER_PASSWORD = "/alterPassword.u";
    //发送验证码信息
    public final static String USER_SEND_MESSAGE = "/sendMessage.u";
}
