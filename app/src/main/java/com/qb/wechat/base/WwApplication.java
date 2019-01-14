package com.qb.wechat.base;

import com.qb.wxbase.app.BaseApplication;
import com.qb.wxbase.create.speasy.SpEasy;
import com.qb.wxbase.create.sql.FS;
import com.qb.wxbase.create.sql.base.SqlScan;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/8
 * 包    名：com.qb.wechat.base
 * 描    述：
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class WwApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化SpEasy
        SpEasy.init(this);
        //初始化foxSql组件
        FS.init(this);
    }
}
