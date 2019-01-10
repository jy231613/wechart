package com.qb.wxui.inter;

/**
 * Summarize:Enum工具类
 * User:贾恒飞
 * Date:2018/3/29
 * Time:16:06
 * Email:17515250730@163.com
 * Created with AndroidStudio3.0
 */

public interface EnumUtils {
    //方位
    enum Direction{
        Left,//左
        Center,//中
        Right,//右
        Front,//前(上)
        Rear//后(下)
    }

    //点击区域划分
    enum ItemClick{
        Base,//根布局
        Title,//标题
        Img,//图片
        Tag,//特殊标记
        SomeAll,//更多
        Button
    }

}
