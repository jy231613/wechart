package com.qb.wxbase.util.easyutil;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qb.wxbase.R;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：TreeHole
 * 日    期：2018/7/11 0011--11:52--星期三
 * 包    名：cn.secret.base.utils
 * 描    述：Glide图片加载工具
 * Create by Administrator from AndroidStudio3.1
 * ================================================
 */
public class GlideUtils {
    public static void load(Context context, String imgUrl, ImageView imageView){
        if (imgUrl!=null&&!"".equals(imgUrl)){
            //使用Glide加载列表图片
            Glide.with(context)
                    .load(imgUrl)
                    .asBitmap()
                    .priority(Priority.NORMAL)//加载优先级为正常的
                    .placeholder(R.drawable.ysf_image_placeholder_loading)//图片加载出来前，显示的图片
                    .error(R.drawable.ysf_image_placeholder_fail)//图片加载失败后，显示的图片
                    .into(imageView);
        }
    }

    public static void loadNoCache(Context context, String imgUrl, ImageView imageView){
        if (imgUrl!=null&&!"".equals(imgUrl)){
            //使用Glide加载列表图片
            Glide.with(context)
                    .load(imgUrl)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)//禁用磁盘缓存
                    .priority(Priority.NORMAL)//加载优先级为正常的
                    .placeholder(R.drawable.ysf_image_placeholder_loading)//图片加载出来前，显示的图片
                    .error(R.drawable.ysf_image_placeholder_fail)//图片加载失败后，显示的图片
                    .into(imageView);
        }
    }

    public static void loadNoCache(Context context, String imgUrl, ImageView imageView,@DrawableRes int draw){
        if (imgUrl!=null&&!"".equals(imgUrl)){
            //使用Glide加载列表图片
            Glide.with(context)
                    .load(imgUrl)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)//禁用磁盘缓存
                    .priority(Priority.NORMAL)//加载优先级为正常的
                    .placeholder(draw)//图片加载出来前，显示的图片
                    .error(draw)//图片加载失败后，显示的图片
                    .into(imageView);
        }
    }

    public static void load(Context context, String imgUrl, ImageView imageView,@DrawableRes int draw){
        if (imgUrl!=null&&!"".equals(imgUrl)){
            //使用Glide加载列表图片
            Glide.with(context)
                    .load(imgUrl)
                    .asBitmap()
                    .priority(Priority.NORMAL)//加载优先级为正常的
                    .placeholder(draw)//图片加载出来前，显示的图片
                    .error(draw)//图片加载失败后，显示的图片
                    .into(imageView);
        }
    }

    public static void loadLow(Context context, String imgUrl, ImageView imageView){
        if (imgUrl!=null&&!"".equals(imgUrl)){
            //使用Glide加载列表图片
            Glide.with(context)
                    .load(imgUrl)
                    .asBitmap()
                    .priority(Priority.LOW)//加载优先级为较低的
                    .placeholder(R.drawable.ysf_image_placeholder_loading)//图片加载出来前，显示的图片
                    .error(R.drawable.ysf_image_placeholder_fail)//图片加载失败后，显示的图片
                    .into(imageView);
        }
    }
    public static void loadHige(Context context, String imgUrl, ImageView imageView){
        if (imgUrl!=null&&!"".equals(imgUrl)){
            //使用Glide加载列表图片
            Glide.with(context)
                    .load(imgUrl)
                    .asBitmap()
                    .priority(Priority.HIGH)//加载优先级为较高的
                    .placeholder(R.drawable.ysf_image_placeholder_loading)//图片加载出来前，显示的图片
                    .error(R.drawable.ysf_image_placeholder_fail)//图片加载失败后，显示的图片
                    .into(imageView);
        }
    }
}
