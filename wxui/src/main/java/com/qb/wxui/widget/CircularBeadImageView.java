package com.qb.wxui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;

import com.qb.wxui.R;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/9
 * 包    名：com.qb.wxui.widget
 * 描    述：自定义有圆角效果的图片
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class CircularBeadImageView extends android.support.v7.widget.AppCompatImageView {
    private float width;
    private float height;

    //参数
    private int defaultRadius = 0;
    private int radius;
    private int leftTopRadius;
    private int rightTopRadius;
    private int rightBottomRadius;
    private int leftBottomRadius;

    //路径
    private Path path;

    /**
     * 读取配置信息
     * @param context 上下文
     * @param attrs 属性
     */
    private void init(Context context, AttributeSet attrs) {
        path = new Path();
        // 读取配置
        @SuppressLint("CustomViewStyleable")
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Circular_Bead_Image_View);
        radius = array.getDimensionPixelOffset(R.styleable.Circular_Bead_Image_View_radius, defaultRadius);
        leftTopRadius = array.getDimensionPixelOffset(R.styleable.Circular_Bead_Image_View_left_top_radius, defaultRadius);
        rightTopRadius = array.getDimensionPixelOffset(R.styleable.Circular_Bead_Image_View_right_top_radius, defaultRadius);
        rightBottomRadius = array.getDimensionPixelOffset(R.styleable.Circular_Bead_Image_View_right_bottom_radius, defaultRadius);
        leftBottomRadius = array.getDimensionPixelOffset(R.styleable.Circular_Bead_Image_View_left_bottom_radius, defaultRadius);

        //如果四个角的值没有设置，那么就使用通用的radius的值。
        if (defaultRadius == leftTopRadius) {
            leftTopRadius = radius;
        }
        if (defaultRadius == rightTopRadius) {
            rightTopRadius = radius;
        }
        if (defaultRadius == rightBottomRadius) {
            rightBottomRadius = radius;
        }
        if (defaultRadius == leftBottomRadius) {
            leftBottomRadius = radius;
        }
        array.recycle();
    }

    public CircularBeadImageView(Context context) {
        super(context);
        init(context,null);
    }

    public CircularBeadImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public CircularBeadImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //这里做下判断，只有图片的宽高大于设置的圆角距离的时候才进行裁剪
        int maxLeft = Math.max(leftTopRadius, leftBottomRadius);
        int maxRight = Math.max(rightTopRadius, rightBottomRadius);
        int minWidth = maxLeft + maxRight;
        int maxTop = Math.max(leftTopRadius, rightTopRadius);
        int maxBottom = Math.max(leftBottomRadius, rightBottomRadius);
        int minHeight = maxTop + maxBottom;
        if (width >= minWidth && height > minHeight) {
            //四个角：右上，右下，左下，左上
            path.moveTo(leftTopRadius, 0);
            path.lineTo(width - rightTopRadius, 0);
            path.quadTo(width, 0, width, rightTopRadius);

            path.lineTo(width, height - rightBottomRadius);
            path.quadTo(width, height, width - rightBottomRadius, height);

            path.lineTo(leftBottomRadius, height);
            path.quadTo(0, height, 0, height - leftBottomRadius);

            path.lineTo(0, leftTopRadius);
            path.quadTo(0, 0, leftTopRadius, 0);

            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }
}
