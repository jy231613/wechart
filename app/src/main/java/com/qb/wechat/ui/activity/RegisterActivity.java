package com.qb.wechat.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qb.wechat.R;
import com.qb.wechat.net.Net;
import com.qb.wxbase.app.BaseActivity;
import com.qb.wxbase.app.FoxBaseManagement;
import com.qb.wxbase.create.foxbind.Find;
import com.qb.wxbase.create.speasy.spbus.SpBus;
import com.qb.wechat.net.NetHttpCallBack;
import com.qb.wxbase.listener.TextWatcherIn;
import com.qb.wxbase.util.easyutil.Encryption;
import com.qb.wxui.widget.EdText;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechart
 * 日    期：2019/1/31
 * 包    名：com.qb.wechat.ui.activity
 * 描    述：注册页面
 * Create by zFox from AndroidStudio3.2
 * ================================================
 */
public class RegisterActivity extends BaseActivity {
    @Find(R.id.xClick) ImageView xClick;
    @Find(R.id.nicknameEdText) EdText nicknameEdText;
    @Find(R.id.phoneEdText) EdText phoneEdText;
    @Find(R.id.passwordEdText) EdText passwordEdText;
    @Find(R.id.selectImageClick) RelativeLayout selectImageClick;
    @Find(R.id.guoJiaText) TextView guoJiaText;
    @Find(R.id.guoJiaClick) LinearLayout guoJiaClick;
    @Find(R.id.registerBtn) Button registerBtn;
    @Find(R.id.bottomText) TextView bottomText;
    @Find(R.id.phoneDelClick) ImageView phoneDelClick;
    @Find(R.id.passwordDelClick) ImageView passwordDelClick;

    /**
     * 用户头像地址
     */
    private String userPic = "";

    @Override
    protected boolean isShowTopBar() {
        return true;
    }

    @Override
    protected boolean isOpenSwipeBack() {
        return true;
    }

    @Override
    protected int gainContentView() {
        return R.layout.activity_register;
    }

    @Override
    protected void create() {
        registerBtn.setEnabled(false);//先禁用当前按钮
        //初始化公共监听
        TextWatcherIn textWatcherIn = new TextWatcherIn() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isEnabled();
            }
        };
        phoneEdText.addTextChangedListener(textWatcherIn);
        passwordEdText.addTextChangedListener(textWatcherIn);
    }

    /**
     * 是否禁用
     */
    private void isEnabled(){
        //删除按钮
        if (phoneEdText.getTextStr().equals(""))phoneDelClick.setVisibility(View.GONE);
        else phoneDelClick.setVisibility(View.VISIBLE);
        if (passwordEdText.getTextStr().equals(""))passwordDelClick.setVisibility(View.GONE);
        else passwordDelClick.setVisibility(View.VISIBLE);
        //是否禁用按钮
        if (nicknameEdText.getTextStr().equals("")||phoneEdText.getTextStr().equals("")||passwordEdText.getTextStr().equals("")){
            registerBtn.setEnabled(false);
        }else{
            registerBtn.setEnabled(true);
        }
    }

    /**
     * 注册
     */
    public void register(View view) {
        String nicknameStr = nicknameEdText.getTextStr();
        String phoneStr = phoneEdText.getTextStr();
        String passwordStr = passwordEdText.getTextStr();

        Net.register(nicknameStr, userPic, phoneStr, Encryption.getEncryption().md5(passwordStr), new NetHttpCallBack(this) {
            @Override
            public void success(String data) {
                //注册成功
                SpBus.postId(Integer.valueOf(data));//发送id
                SpBus.postPhone(phoneStr);
                FoxBaseManagement.getFoxManagement().exitActivity(RegisterActivity.this);
            }
        });
    }

    //x的监听
    public void phoneDel(View view) {
        phoneEdText.setText("");
    }
    public void passwordDel(View view) {
        passwordEdText.setText("");
    }
}
