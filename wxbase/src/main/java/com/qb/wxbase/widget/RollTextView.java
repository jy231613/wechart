package com.qb.wxbase.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Summarize:文字滚动的效果
 *          仿京东首页跑马灯效果
 * UserJson:贾恒飞
 * Date:2018/3/30
 * Time:19:56
 * Email:17515250730@163.com
 * Created with AndroidStudio3.0
 */
public class RollTextView extends android.support.v7.widget.AppCompatTextView {
    private int mDuration = 1000; //文字从出现到显示消失的时间(默认为1000)
    private int mInterval = 3000; //文字停留在中间的时长切换的间隔(默认为3000)
    private List<PaoMaModel> mTexts; //显示文字的数据源
    private int mY = 0; //文字的Y坐标
    private int mIndex = 0; //当前的数据下标
    private Paint mPaintBack; //绘制内容的画笔
    private Paint mPaintFront; //绘制前缀的画笔
    private boolean isMove = true; //文字是否移动
    private String TAG = "ADTextView";
    private boolean hasInit = false;//是否初始化刚进入时候文字的纵坐标

    private int frontSize = 25;//字体大小
    private int backSize = 25;//字体大小

    public interface onClickLitener {
        public void onClick(String mUrl);
    }

    private onClickLitener onClickLitener;

    public void setOnClickLitener(RollTextView.onClickLitener onClickLitener) {
        this.onClickLitener = onClickLitener;
    }

    public RollTextView(Context context) {
        super(context);
    }

    public RollTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //重写onTouchEvent事件,并且要返回true,表明当前的点击事件由这个组件自身来处理
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //调用回调,将当前数据源的链接传出去
                if (onClickLitener != null) {
                    onClickLitener.onClick(mTexts.get(mIndex).getmUrl());
                }
                break;
        }
        return true;
    }

    //设置数据源
    public void setmTexts(List mTexts) {
        this.mTexts = mTexts;
        this.invalidate();//刷新控件

    }

    //设置广告文字的停顿时间
    public void setmInterval(int mInterval) {
        this.mInterval = mInterval;
    }

    //设置文字从出现到消失的时长
    public void setmDuration(int mDuration) {
        this.mDuration = mDuration;
    }

    //设置前缀的文字颜色
    public void setFrontColor(int mFrontColor) {
        mPaintFront.setColor(mFrontColor);
    }

    //设置正文内容的颜色
    public void setBackColor(int mBackColor) {
        mPaintBack.setColor(mBackColor);
    }

    //设置前缀的文字大小
    public void setFrontSize(int size) {
        this.frontSize = size;
    }

    //设置正文内容的大小
    public void setBackSize(int size) {
        this.backSize = size;
    }

    //初始化默认值
    private void init() {
        mDuration = 500;
        mInterval = 1000;
        mIndex = 0;
        mPaintFront = new Paint();
        mPaintFront.setAntiAlias(true);
        mPaintFront.setDither(true);
        mPaintFront.setColor(Color.RED);


        mPaintBack = new Paint();
        mPaintBack.setAntiAlias(true);
        mPaintBack.setDither(true);
        mPaintBack.setColor(Color.BLACK);

        //根据SDK来优化字体大小
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            //设置选项卡字体大小SDK>19的情况
            mPaintFront.setTextSize(frontSize+15);
            mPaintBack.setTextSize(backSize+15);
        }else{
            //设置选项卡字体大小SDK<19的情况
            mPaintFront.setTextSize(frontSize);
            mPaintBack.setTextSize(backSize);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "onSizeChanged: " + h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mTexts != null&& mTexts.size() > 0) {
            //获取当前的数据
            PaoMaModel model = mTexts.get(mIndex);
            String font = model.getmFront();
            String back = model.getmBack();
            // 绘制前缀的外框
            Rect indexBound = new Rect();
            mPaintFront.getTextBounds(font, 0, font.length(), indexBound);

            //绘制内容的外框
            Rect contentBound = new Rect();
            mPaintBack.getTextBounds(back, 0, back.length(), contentBound);
            //刚开始进入的时候文字应该是位于组件的底部的 ,但是这个值是需要获取组件的高度和当前显示文字的情况下来判断的,
            // 所以应该放在onDraw内来初始化这个值,所以需要前面的是否初始化的属性,判断当mY==0并且未初始化的时候给mY赋值.
            if (mY == 0 && hasInit == false) {
                mY = getMeasuredHeight() - indexBound.top;
                hasInit = true;
            }
            //移动到最上面
            if (mY == 0 - indexBound.bottom) {
                Log.i(TAG, "onDraw: " + getMeasuredHeight());
                mY = getMeasuredHeight() - indexBound.top;//返回底部
                mIndex++;//换下一组数据
            }
            canvas.drawText(back, 0, back.length(), (indexBound.right - indexBound.left) + 20, mY, mPaintBack);
            canvas.drawText(font, 0, font.length(), 10, mY, mPaintFront);
            //移动到中间
            if (mY == getMeasuredHeight() / 2 - (indexBound.top + indexBound.bottom) / 2) {
                isMove = false;//停止移动
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        postInvalidate();//通知重绘
                        isMove = true;//设置移动为true
                    }
                }, mInterval);//停顿多少毫秒之后再次移动
            }
            //移动的处理与数据源的处理
            mY -= 1;//每次只移动一个像素,尽量保证平滑显示
            //循环使用数据
            if (mIndex == mTexts.size()) {
                mIndex = 0;
            }
            //如果是处于移动状态时的,则延迟绘制
            //计算公式为一个比例,一个时间间隔移动组件高度,则多少毫秒来移动1像素
            if (isMove) {
                postInvalidateDelayed(mDuration / getMeasuredHeight());
            }
        }

    }

    /**
     * 构建模型类
     */
    public static class PaoMaModel{
        private String mFront ; //前面的文字
        private String mBack ; //后面的文字
        private String mUrl ;//包含的链接

        public PaoMaModel(String mFront, String mBack, String mUrl) {
            this.mFront = mFront;
            this.mBack = "  "+mBack;
            this.mUrl = mUrl;
        }

        public void setmFront(String mFront) {
            this.mFront = mFront;
        }

        public void setmBack(String mBack) {
            this.mBack = mBack;
        }

        public void setmUrl(String mUrl) {
            this.mUrl = mUrl;
        }

        public String getmFront() {
            return mFront;
        }

        public String getmBack() {
            return mBack;
        }

        public String getmUrl() {
            return mUrl;
        }
    }

}
