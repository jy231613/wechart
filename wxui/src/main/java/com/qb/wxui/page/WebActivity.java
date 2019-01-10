package com.qb.wxui.page;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qb.wxbase.app.BaseActivity;
import com.qb.wxbase.create.foxbus.FoxBus;
import com.qb.wxui.R;
import com.qb.wxui.dialog.JarvanDialog;
import com.qb.wxui.dialog.util.DialogStyle;

import static com.qb.wxui.dialog.util.DialogStyle.LoadingStyle.square_loading;


/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：zcapp
 * 日    期：2018/8/20 0020--11:42--星期一
 * 包    名：cn.app.zc.ui.acticity
 * 描    述：网页访问页
 * Create by Administrator from AndroidStudio3.1
 * ================================================
 */
public class WebActivity extends BaseActivity {
    private ImageView leftIcon;
    private TextView title;
    private ImageView rightIcon;
    private LinearLayout topBar;
    private ProgressBar pbWeb;
    private WebView web;

    private WebEvent webEvent;

    /**
     * web页传参唯一标识字段
     */
    public static final String WEB_UNIQUE_IDENTIFICATION = "WEB_UNIQUE_IDENTIFICATION";

    /**
     * 使用FoxBus传参,获取一个WebEvent对象,tag为本类的web唯一标识WEB_UNIQUE_IDENTIFICATION
     * 对象说明
     * --地址格式
     *      网络地址如:http://www.jiahengfei.cn
     *      本地地址如:file:///android_asset/index.html
     */

    @Override
    protected int gainContentView() {
        return R.layout.activity_web;
    }

    @Override
    protected boolean isShowTopBar() {
        return true;
    }

    @Override
    protected void create() {
        //初始化连接对象
        webEvent = (WebEvent) FoxBus.getDefault().gainObj(WEB_UNIQUE_IDENTIFICATION);
        if (webEvent==null){
            showToast("无效的网络连接");
            finish();
        }
        initSet();
        loadWebPage();
        //设置监听
        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void goBack() {
        if (web.canGoBack()){
            web.goBack();
        }else{
            finish();
        }
    }

    /**
     * 初始化设置
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void initSet() {
        WebSettings webSettings = web.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        webSettings.setDomStorageEnabled(true);//重要!!!!!!!!https--适应html5
    }

    private void loadWebPage() {
        web.setWebViewClient(new WebViewClient(){
            //再本页打开网页
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                if (webEvent.getType() == WebType.Open_the_local_browser)return false;
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                if (error.getPrimaryError() == SslError.SSL_INVALID) {
                    handler.proceed();
                } else {
                    handler.cancel();
                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                showEmpty(true);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                showEmpty(false);
                if (webEvent.getJsStr()!=null){
                    web.loadUrl("javascript:"+webEvent.getJsStr());
                    super.onPageFinished(view, url);
                }
            }
        });
        web.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                alterProgress(newProgress);//设置进度
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (webEvent.getTitle()==null){
                    setTitle(title);//设置标题
                }else{
                    setTitle(webEvent.getTitle());//设置标题
                }
            }
        });
        web.loadUrl(webEvent.getUrl());
    }

    @Override
    protected void init() {
        super.init();
        leftIcon = findViewById(R.id.left_icon);
        title = findViewById(R.id.title);
        rightIcon = findViewById(R.id.right_icon);
        topBar = findViewById(R.id.topBar);
        pbWeb = findViewById(R.id.pb_web);
        web = findViewById(R.id.web);
    }

    @Override
    protected void destory() {
        super.destory();
        web.clearCache(true);//清理缓存
        web.clearHistory();//清空本地浏览记录
        if (web != null) {
            web.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            web.clearHistory();
            ((ViewGroup) web.getParent()).removeView(web);
            web.destroy();
            web = null;
        }
    }

    /**
     * 设置进度条
     * @param number 进度
     */
    public void alterProgress(int number) {
        pbWeb.setProgress(number);
        if (number==100){
            pbWeb.setVisibility(View.GONE);
        }else {
            pbWeb.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置标题
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title.setText(title);
    }

    private Dialog loadingDialog;
    public void showEmpty(boolean isShow) {
        if (isShow){
            loadingDialog = JarvanDialog.showLoadingDialog(
                    foxContext,
                    "正在载入...",
                    square_loading,
                    DialogStyle.LoadingIconStyle.BallBeatIndicator
            );
            try{loadingDialog.show();}catch (Exception ignored){}
        }else{
            if (loadingDialog!=null)loadingDialog.dismiss();
        }
    }

    /**
     * 网络连接类型
     */
    public enum WebType{
        web_page,//网络页面
        local_page,//本地页面
        Open_the_local_browser//打开外部浏览器
    }
}
