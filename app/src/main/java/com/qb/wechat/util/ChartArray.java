package com.qb.wechat.util;

import com.qb.wechat.R;
import com.qb.wechat.bean.BaseAddressBean;
import com.qb.wechat.bean.UserInfoBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/11
 * 包    名：com.qb.wechat.util
 * 描    述：
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class ChartArray {

    public static ArrayList<String> array(){
        String[] strs = {
                "↑",
                "☆",
                "A",
                "B",
                "C",
                "D",
                "E",
                "F",
                "G",
                "H",
                "I",
                "J",
                "K",
                "L",
                "M",
                "N",
                "O",
                "P",
                "Q",
                "R",
                "S",
                "T",
                "U",
                "V",
                "W",
                "X",
                "Y",
                "Z",
                "#"
        };
        return new ArrayList<>(Arrays.asList(strs));
    }

    /**
     * 添加头部系统修饰
     * @param beans 源类
     * @return ArrayList<BaseAddressBean>
     */
     public static ArrayList<BaseAddressBean> configArray(List<BaseAddressBean> beans){
         ArrayList<BaseAddressBean> newArray = new ArrayList<>();
         BaseAddressBean bean = new BaseAddressBean();
         bean.setTopName("↑");
         ArrayList<UserInfoBean> beans1 = new ArrayList<>();
         beans1.add(new UserInfoBean("新的朋友",0, R.mipmap.add_new_user,true));
         beans1.add(new UserInfoBean("群聊",1, R.mipmap.group_user,true));
         beans1.add(new UserInfoBean("标签",2, R.mipmap.biaoqian_user,true));
         beans1.add(new UserInfoBean("公众号",3, R.mipmap.gongzhonghao_user,true));
         bean.setBeans(beans1);
         newArray.add(bean);
         newArray.addAll(beans);
         return newArray;
     }

}
