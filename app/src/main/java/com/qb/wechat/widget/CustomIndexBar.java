package com.qb.wechat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.qb.wechat.R;
import com.qb.wechat.util.ChartArray;

import java.util.List;

/**
 * 右侧滑动栏
 */
public class CustomIndexBar extends View {
    private int height, width;
    //view的宽高
    private int indexHeight;
    // 每个字的高度
    private Paint mPaint;
    private Rect mBounds;
    private int bgColor;
    // 背景颜色
    private int textSize;
    //字体大小
    private int touchPosition;
    private List<String> mIndexDatas;

    private OnSollerFormCustom onSollerFormCustom;

    public interface OnSollerFormCustom{
        void onDown(int pos);
        void onUp(int pos);
        void onMove(int pos);
    }

    public CustomIndexBar(Context context) {
        this(context, null);
    }

    public CustomIndexBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        //手动调用第3个构造函数
    }

    public CustomIndexBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //取出自定义属性值以及其他一些初始化操作
        init(context, attrs, defStyleAttr);
    }

    private Context context;
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomIndexBar, defStyleAttr, 0);
//        for (int i = 0; i < typedArray.getIndexCount(); i++) {
//            int attr = typedArray.getIndex(i);
//            switch (attr) {
//                case R.styleable.CustomIndexBar_textSize:
//                    textSize = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, context.getResources().getDisplayMetrics()));
//                    break;
//                case R.styleable.CustomIndexBar_backgroundColor:
//                    bgColor = typedArray.getColor(attr, Color.BLACK);
//                    break;
//            }
//        }
//        typedArray.recycle();
        this.context = context;
        textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18, context.getResources().getDisplayMetrics());
        bgColor = context.getResources().getColor(R.color.no5_color);
        mIndexDatas = ChartArray.array();
        //数据源
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mBounds = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        //获取每一个的高度
        indexHeight = height / mIndexDatas.size();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setTextSize(textSize);
        for (int i = 0; i < mIndexDatas.size(); i++) {
            mPaint.getTextBounds(mIndexDatas.get(i), 0, mIndexDatas.get(i).length(), mBounds);
            canvas.drawText(mIndexDatas.get(i), width / 2 - mBounds.width() / 2, getPaddingTop() + i * indexHeight + indexHeight - (indexHeight / 2 - mBounds.height() / 2), mPaint);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //设置ACTION_DOWN时的背景颜色。
                setBackgroundColor(bgColor);
                mPaint.setColor(context.getResources().getColor(R.color.admin_white));
                //计算点击位置
                touchPosition = (int) ((event.getY() - getPaddingTop()) / indexHeight);
                if (touchPosition <= 0) {
                    touchPosition = 0;
                } else {
                    if (touchPosition >= mIndexDatas.size() - 1) {
                        touchPosition = mIndexDatas.size() - 1;
                    }
                }
                if (onSollerFormCustom!=null)onSollerFormCustom.onDown(touchPosition);
                break;
            case MotionEvent.ACTION_MOVE:
                touchPosition = (int) ((event.getY() - getPaddingTop()) / indexHeight);
                if (touchPosition <= 0) {
                    touchPosition = 0;
                } else {
                    if (touchPosition >= mIndexDatas.size() - 1) {
                        touchPosition = mIndexDatas.size() - 1;
                    }
                }
                if (onSollerFormCustom!=null)onSollerFormCustom.onMove(touchPosition);
                break;
            case MotionEvent.ACTION_UP:
                setBackgroundResource(android.R.color.transparent);
                mPaint.setColor(context.getResources().getColor(R.color.black));
                if (onSollerFormCustom!=null)onSollerFormCustom.onUp(touchPosition);
                break;
        }
        return true;
    }

    public OnSollerFormCustom getOnSollerFormCustom() {
        return onSollerFormCustom;
    }

    public void setOnSollerFormCustom(OnSollerFormCustom onSollerFormCustom) {
        this.onSollerFormCustom = onSollerFormCustom;
    }
}