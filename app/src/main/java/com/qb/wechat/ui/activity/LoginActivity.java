package com.qb.wechat.ui.activity;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qb.wechat.R;
import com.qb.wechat.aax.UserSelf;
import com.qb.wechat.net.Net;
import com.qb.wxbase.app.BaseActivity;
import com.qb.wxbase.app.FoxBaseManagement;
import com.qb.wxbase.create.foxbind.Find;
import com.qb.wxbase.create.speasy.SpEasy;
import com.qb.wxbase.create.speasy.base.SpGet;
import com.qb.wxbase.create.speasy.spbus.EasyDataTrait;
import com.qb.wxbase.create.speasy.spbus.SpBus;
import com.qb.wxbase.json.Json;
import com.qb.wxbase.listener.TextWatcherIn;
import com.qb.wechat.net.NetHttpCallBack;
import com.qb.wxbase.util.easyutil.Encryption;
import com.qb.wxbase.util.easyutil.Verify;
import com.qb.wxui.dialog.WaChatDialog;
import com.qb.wxui.dialog.util.RecyclerBean;
import com.qb.wxui.widget.EdText;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/12
 * 包    名：com.qb.wechat.ui.activity.login
 * 描    述：登录页面
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class LoginActivity extends BaseActivity {
    @Find(R.id.xClick)ImageView xClick;
    @Find(R.id.phoneLoginView)LinearLayout phoneLoginView;
    @Find(R.id.chartNLoginView)LinearLayout chartNLoginView;
    @Find(R.id.checkedClick)TextView checkedClick;
    @Find(R.id.titleText)TextView titleText;
    @Find(R.id.loginBtn)Button loginBtn;
    @Find(R.id.phoneEdText)EdText phoneEdText;
    @Find(R.id.passwordEdText)EdText passwordEdText;
    @Find(R.id.chartNEdText)EdText chartNEdText;
    @Find(R.id.password2EdText)EdText password2EdText;
    @Find(R.id.phoneDelClick)ImageView phoneDelClick;
    @Find(R.id.passwordDelClick)ImageView passwordDelClick;
    @Find(R.id.chartNDelClick)ImageView chartNDelClick;
    @Find(R.id.password2DelClick)ImageView password2DelClick;

    //登录类型,默认手机号登录
    private int loginType = 0;

    private Dialog dialog;

    @SpGet
    private UserSelf userSelf;

    @Override
    protected int gainContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected boolean isShowTopBar() {
        return true;
    }

    @Override
    protected void create() {
        loginBtn.setEnabled(false);//先禁用当前按钮
        xClick.setVisibility(View.GONE);//隐藏x号按钮
        //初始化公共监听
        TextWatcherIn textWatcherIn = new TextWatcherIn() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isEnabled();
            }
        };
        //增加监听
        phoneEdText.addTextChangedListener(textWatcherIn);
        passwordEdText.addTextChangedListener(textWatcherIn);
        chartNEdText.addTextChangedListener(textWatcherIn);
        password2EdText.addTextChangedListener(textWatcherIn);
        //登录已有账号
        loginMySelf("Hello");
    }

    /**
     * 登录已有账号
     */
    private void loginMySelf(String msg) {
        //启动自动登录
        if (userSelf!=null){
            phoneEdText.setText(userSelf.getPhone());
            chartNEdText.setText(userSelf.getChartn());
        }else{
            showToast(msg);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        EasyDataTrait easyDataTrait = SpBus.reception();
        //接收注册的数据
        if (easyDataTrait.getId() != 0) {
            int id = easyDataTrait.getId();
            String phoneStr = easyDataTrait.getPhone();
            phoneEdText.setText(phoneStr);
            loginType = 0;//修改登录状态
            checkLoginType(null);
            SpBus.postId(0);//重置状态
        }
    }

    /**
     * 是否禁用按钮
     */
    private void isEnabled() {
        if (loginType == 0) {
            //删除按钮
            if (phoneEdText.getTextStr().equals(""))phoneDelClick.setVisibility(View.GONE);
            else phoneDelClick.setVisibility(View.VISIBLE);
            if (passwordEdText.getTextStr().equals(""))passwordDelClick.setVisibility(View.GONE);
            else passwordDelClick.setVisibility(View.VISIBLE);
            //是否禁用图标
            if (phoneEdText.getTextStr().equals("") || passwordEdText.getTextStr().equals("")) {
                loginBtn.setEnabled(false);//先禁用当前按钮
            } else {
                loginBtn.setEnabled(true);
            }
        } else {
            //删除按钮
            if (chartNEdText.getTextStr().equals(""))chartNDelClick.setVisibility(View.GONE);
            else chartNDelClick.setVisibility(View.VISIBLE);
            if (password2EdText.getTextStr().equals(""))password2DelClick.setVisibility(View.GONE);
            else password2DelClick.setVisibility(View.VISIBLE);
            //是否禁用图标
            if (chartNEdText.getTextStr().equals("") || password2EdText.getTextStr().equals("")) {
                loginBtn.setEnabled(false);//先禁用当前按钮
            } else {
                loginBtn.setEnabled(true);
            }
        }
    }

    /**
     * 选中登录状态
     */
    public void checkLoginType(View view) {
        if (loginType == 0) {
            //使用嗡嗡号登录
            loginType = 1;
            phoneLoginView.setVisibility(View.GONE);
            chartNLoginView.setVisibility(View.VISIBLE);
            titleText.setText(getResources().getString(R.string.login_button_chartN));
            checkedClick.setText(getResources().getString(R.string.login_text_login));
        } else {
            //使用手机号登录
            loginType = 0;
            phoneLoginView.setVisibility(View.VISIBLE);
            chartNLoginView.setVisibility(View.GONE);
            titleText.setText(getResources().getString(R.string.login_text_login));
            checkedClick.setText(getResources().getString(R.string.login_button_chartN));
        }
        isEnabled();//改变按钮状态
    }

    /**
     * 登录
     */
    public void login(View view) {
        String phoneStr = loginType == 0 ? phoneEdText.getTextStr() : chartNEdText.getTextStr();
        String passwordStr = loginType == 0 ? passwordEdText.getTextStr() : password2EdText.getTextStr();

        //正则验证
        if (!Verify.verifyPhone(phoneStr)&&loginType==0) {
            showToast("请输入正确的手机号");
            return;
        }
        if (!Verify.verifyPassword(passwordStr)) {
            showToast("请输入至少6位至多18位的密码");
            return;
        }

        //请求登录
        Net.login(loginType, phoneStr, Encryption.getEncryption().md5(passwordStr), new NetHttpCallBack(this,"登录失败") {
            @Override
            public void success(String data) {
                //登录成功
                UserSelf self = Json.obj(data, UserSelf.class);
                self.setPassword(passwordStr);
                SpEasy.getSingle().saveShared(self);
                //跳转页面
                FoxBaseManagement.getFoxManagement()
                        .beginActivity(MainActivity.class)
                        .exitActivity(LoginActivity.this);
            }
        });
    }

    /**
     * 更多功能
     */
    public void evenMore(View view) {
        //显示更多列表对话框
        dialog = WaChatDialog.showListDialog(this,
                new RecyclerBean("登录已有账号", v -> {
                    dialog.dismiss();
                    loginMySelf("暂无已有账号");
                }),
                new RecyclerBean("注册", v -> {
                    dialog.dismiss();
                    goActivity(RegisterActivity.class);
                }),
                new RecyclerBean("嗡嗡安全中心", v -> {
                    dialog.dismiss();
                    showToast("稍等...");
                })
        );
    }

    //X的监听
    public void phoneDel(View view) {
        phoneEdText.setText("");
    }
    public void passwordDel(View view) {
        passwordEdText.setText("");
    }
    public void chartNDel(View view) {
        chartNEdText.setText("");
    }
    public void password2xDel(View view) {
        password2EdText.setText("");
    }
}
