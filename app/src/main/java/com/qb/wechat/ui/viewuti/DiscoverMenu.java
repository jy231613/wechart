package com.qb.wechat.ui.viewuti;

import com.qb.wechat.R;
import com.qb.wechat.ui.viewuti.model.DiscoverModel;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/9
 * 包    名：com.qb.wechat.ui.viewuti
 * 描    述：
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class DiscoverMenu {
    private static ArrayList<DiscoverModel> discoverModels = new ArrayList<>();

    public static ArrayList<DiscoverModel> getDiscoverModels() {
        if (discoverModels==null||discoverModels.size()==0){
            discoverModels.add(new DiscoverModel(
                    "朋友圈",
                    true,
                    R.mipmap.icon_pengyouquan,
                    0,
                    0,
                    null

            ));

            discoverModels.add(new DiscoverModel(
                    "扫一扫",
                    false,
                    R.mipmap.icon_saoyisao,
                    0,
                    0,
                    null

            ));
            discoverModels.add(new DiscoverModel(
                    "摇一摇",
                    true,
                    R.mipmap.icon_yaoyiyao,
                    0,
                    0,
                    null

            ));

            discoverModels.add(new DiscoverModel(
                    "看一看",
                    false,
                    R.mipmap.icon_kanyian,
                    0,
                    0,
                    null

            ));
            discoverModels.add(new DiscoverModel(
                    "搜一搜",
                    true,
                    R.mipmap.icon_souyisou,
                    0,
                    0,
                    null

            ));

            discoverModels.add(new DiscoverModel(
                    "附近的人",
                    false,
                    R.mipmap.icon_fujinderen,
                    0,
                    0,
                    null

            ));
            discoverModels.add(new DiscoverModel(
                    "漂流瓶",
                    true,
                    R.mipmap.icon_piaoliuping,
                    0,
                    0,
                    null

            ));

            discoverModels.add(new DiscoverModel(
                    "购物",
                    false,
                    R.mipmap.icon_gouwu,
                    0,
                    0,
                    null

            ));
            discoverModels.add(new DiscoverModel(
                    "游戏",
                    true,
                    R.mipmap.icon_youxi,
                    0,
                    0,
                    null

            ));

            discoverModels.add(new DiscoverModel(
                    "小程序",
                    true,
                    R.mipmap.icon_xiaochengxu,
                    0,
                    0,
                    null

            ));
        }
        return discoverModels;
    }
}
