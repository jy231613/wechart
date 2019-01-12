package com.qb.wxbase.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.qb.wxbase.BuildConfig;
import com.qb.wxbase.R;
import com.qb.wxbase.create.foxbind.FoxFindBind;
import com.qb.wxbase.create.foxbus.FxBus;
import com.qb.wxbase.create.speasy.Sp;
import com.qb.wxbase.util.apkutil.SystemUtils;
import com.qb.wxbase.util.uibase.DialogFor;
import com.qb.wxbase.widget.SwipeBackLayout;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Summarize:Activity基类
 * User:贾恒飞
 * Date:2018/3/28
 * Time:21:20
 * Email:17515250730@163.com
 * Created with AndroidStudio3.0
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected final String BASE_INTENT_HEADER = "BASE_INTENT_HEADER";//默认的intent传参的头
    public static boolean IS_RESET_KEY = false;
    protected Context foxContext;

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isOpenSwipeBack()) {
            SwipeBackLayout layout = (SwipeBackLayout) LayoutInflater.from(this).inflate(R.layout.layout_swipe_back, null);
            layout.attachToActivity(this);
        }
        afterSetView();
        setContentView(gainContentView());
        foxContext = this;
        //初始化SpEasy自动注入
        Sp.inSet(this.getClass(), this);
        //是否启用沉浸式状态栏
        if (isShowTopBar()) {
            SystemUtils.fullScreen(this);
        } else {
            SystemUtils.setStatusBarColor(setTopBarColor(), this);
        }
        //添加到FoxBaseManagement管理
        if (isToFoxManagement()) {
            if (isExitActivity()) {
                FoxBaseManagement.getFoxManagement().createActivityAfterExit(this);//判断是否在创建当前页时退出其他页面
            } else {
                FoxBaseManagement.getFoxManagement().createActivity(this);
            }
        }
        //绑定视图解析器
        FoxFindBind.bind(this.getClass(), this, this);
        //初始化方法
        init();
        //绑定FxBus
        FxBus.bind(this.getClass(), this);
        //创建视图方法
        create();
    }

    /**
     * 设置页面之前
     */
    protected void afterSetView(){};

    @Override
    public void startActivity(Intent intent) {
//        if (isOpenSwipeBack()) {
//            //Activity启动动画
//            overridePendingTransition(R.anim.base_sige_in, R.anim.base_sige_out);
//        }
        super.startActivity(intent);
    }

    /**
     * 是否开启右划退出Activity和启动Activity左滑
     *
     * @return true开启, 默认不开启
     */
    protected boolean isOpenSwipeBack() {
        return false;
    }

    /**
     * 进入当前Activity之前是否退出其他所有Activity
     *
     * @return false
     */
    protected boolean isExitActivity() {
        return false;
    }

    /**
     * 设置顶部导航栏颜色
     *
     * @return 颜色
     */
    protected int setTopBarColor() {
        return getResources().getColor(R.color.admin_white);
    }

    /**
     * 是否启用Fox的Activity管理器
     *
     * @return true
     */
    protected boolean isToFoxManagement() {
        return true;
    }

    protected boolean isSaveNet() {
        return true;
    }

    public Context getFoxContext() {
        return foxContext;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isToFoxManagement()) {
            FoxBaseManagement.getFoxManagement().bindNowActivity(this);
        }
        begin();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //页面销毁时取消标记
        Sp.destroy(this.getClass(), this);
        //退出当前Activity
        if (isToFoxManagement()) {
            FoxBaseManagement.getFoxManagement().exitActivity(this);
        }
        //增加销毁时操作
        destory();
    }

    protected abstract int gainContentView();//获取资源文件

    protected abstract void create();//创建方法

    protected void begin() {
    }

    /**
     * 默认为空方法,在create之前执行
     */
    protected void init() {
    }

    /**
     * 页面销毁时取消页面请求
     *
     * @return 当前页面的网络请求Tag数组
     */
    protected String[] cancelRequest() {
        return null;
    }

    /**
     * 空方法,销毁时执行
     */
    protected void destory() {
    }

    /**
     * 默认返回时执行finish()方法,重写可指定点击返回按钮事件操作,且重写时不要调用super();
     */
    protected void goBack() {
        finish();
    }

    /**
     * 是否实现沉浸式状态栏
     *
     * @return 默认true
     */
    protected boolean isShowTopBar() {
        return false;
    }

    /**
     * 是否注册EventBus,注册时要保证必须有一个@subscribe()注释的方法存在
     *
     * @return 默认不注册
     * @subscribe()的属性: POSTING (默认) 表示事件处理函数的线程跟发布事件的线程在同一个线程。
     * MAIN 表示事件处理函数的线程在主线程(UI)线程，因此在这里不能进行耗时操作。
     * BACKGROUND 表示事件处理函数的线程在后台线程，因此不能进行UI操作。如果发布事件的线程是主线程(UI线程)，
     * 那么事件处理函数将会开启一个后台线程，如果果发布事件的线程是在后台线程，那么事件处理函数就使用该线程。
     * ASYNC 表示无论事件发布的线程是哪一个，事件处理函数始终会新建一个子线程运行，同样不能进行UI操作。
     */
    protected boolean eventBus() {
        return false;
    }

    /**
     * Activity跳转方法
     *
     * @param cls
     * @return 返回Activity对象
     */
    protected FragmentActivity goActivity(Class cls, String... values) {
        Intent intent = new Intent(this, cls);
        for (int i = 0; i < values.length; i++) {
            intent.putExtra(BASE_INTENT_HEADER + i, values[i]);
        }
        startActivity(intent);
        return (FragmentActivity) this;
    }

    /**
     * 显示Toast
     *
     * @param msg 消息
     */
    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示Toast
     *
     * @param view 显示一个view
     */
    protected void showToast(View view) {
        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
    }

    /**
     * 监听Back键按下事件
     * 注意:
     * 返回值表示:是否能完全处理该事件
     * 在此处返回false,所以会继续传播该事件.
     * 在具体项目中此处的返回值视情况而定.
     * --默认提供两种模式,一种是弹出对话框提示,还有一种是默认连续点击两次退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (isBackTows()) {
                backForTows();
            } else if (isBackDialog()) {
                backForDialog();
            } else {
                goBack();
            }
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    /**
     * 是否启用点击两次返回键退出当前页模式
     *
     * @return 默认否
     */
    protected boolean isBackTows() {
        return false;
    }

    /**
     * 是否启用Dialog返回键退出当前页模式
     *
     * @return 默认否
     */
    protected boolean isBackDialog() {
        return false;
    }

    /**
     * Dialog返回键退出当前页模式
     */
    protected void backForDialog() {
        DialogFor.getConfirmDialog(
                getFoxContext(),
                "是否确定退出App?",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        FoxBaseManagement.getFoxManagement().exit();
                    }
                }
        ).show();
    }

    /**
     * 标记是否启动了一次
     */
    private boolean isOut = false;

    /**
     * 点击两次返回键退出当前页模式
     */
    private void backForTows() {
        if (isOut) {
            finish();
        } else {
            isOut = true;
            showToast("再按一次返回键退出");
            //开始计时
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new TimerTask() {
                        @Override
                        public void run() {
                            isOut = false;
                            this.cancel();
                        }
                    });
                }
            };
            timer.schedule(task, 2000, 2000);
        }
    }
}
