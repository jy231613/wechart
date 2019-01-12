package com.qb.wechat.ui.activity;

import android.view.Window;

import com.qb.wechat.R;
import com.qb.wxbase.app.BaseActivity;
import com.qb.wxbase.app.FoxBaseManagement;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/11
 * 包    名：com.qb.wechat.ui.activity
 * 描    述：闪屏页
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected int gainContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void create() {
        //开始计时
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        timer.cancel();
                        FoxBaseManagement.getFoxManagement().beginActivity(MainActivity.class);
                        FoxBaseManagement.getFoxManagement().exitActivity(SplashActivity.this);
                    }
                });
            }
        };
        timer.schedule(task, 3000, 3000);
    }
}
