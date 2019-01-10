package com.qb.wxbase.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qb.wxbase.R;
import com.qb.wxbase.create.foxbind.FoxFindBind;
import com.qb.wxbase.create.foxbus.FxBus;
import com.qb.wxbase.create.speasy.Sp;

import static com.qb.wxbase.util.baseutil.StrUtil.uuid;


/**
 * Summarize:Fragment基类
 * User:贾恒飞
 * Date:2018/3/28
 * Time:21:21
 * Email:17515250730@163.com
 * Created with AndroidStudio3.0
 */

public abstract class BaseFragment extends Fragment{
    private String tagName = null;
    protected final String BASE_INTENT_HEADER = "BASE_INTENT_HEADER";//默认的intent传参的头

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(gainViewRes(), container, false);
        //初始化SpEasy自动注入
        Sp.inSet(this.getClass(),this);
        //绑定视图解析器
        FoxFindBind.bind(this.getClass(),this, view);
        init(view);
        //绑定FxBus
        FxBus.bind(this.getClass(),this);
        view = create(view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected String[] cancelRequest(){return null;};

    protected void init(View view){};
    protected abstract int gainViewRes();
    protected abstract View create(View view);
    public String tagName(){
        if (tagName==null)tagName = uuid();
        return tagName;
    };//每个Fragment都有的一个唯一标识,用于Fragment的操作

    /**
     * 显示Toast
     * @param msg
     */
    protected void showToast(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 设置顶部导航栏颜色
     * @return 颜色
     */
    public int topBarColor(){
        return getResources().getColor(R.color.admin_white);
    }

    /**
     * 页面跳转
     * @param cls 目标类
     */
    protected void goActivity(Class cls,String... values){
        Intent intent = new Intent(getActivity(),cls);
        for (int i = 0;i<values.length;i++){
            intent.putExtra(BASE_INTENT_HEADER+i,values[i]);
        }
        startActivity(intent);
    }

    /**
     * 显示Toast
     * @param view
     */
    protected void showToast(View view){
        Toast toast = new Toast(getContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
    }
}
