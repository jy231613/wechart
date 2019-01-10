package com.qb.wxui.dialog.util;

import com.qb.wxui.R;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：src
 * 日    期：2018/8/25 0025--9:40--星期六
 * 包    名：cn.fox.ui.dialog.util
 * 描    述：对话框类型选择
 * Create by Administrator from AndroidStudio3.1
 * ================================================
 */
public class DialogStyle {

    /**
     * 等待对话框样式
     */
    public enum LoadingStyle{
        square_loading(R.layout.dialog_loading_square),
        rectangle_loading(R.layout.dialog_loading_rectangle);

        LoadingStyle(int layoutRes){
            this.layoutRes = layoutRes;
        }
        private int layoutRes;
        public int getLayoutRes() {
            return layoutRes;
        }
        public void setLayoutRes(int layoutRes) {
            this.layoutRes = layoutRes;
        }
    }

    /**
     * 等待对话框图标样式
     */
    public enum LoadingIconStyle{
        //roll1
        BallPulseIndicator("BallPulseIndicator"),
        BallGridPulseIndicator("BallGridPulseIndicator"),
        BallClipRotateIndicator("BallClipRotateIndicator"),
        BallClipRotatePulseIndicator("BallClipRotatePulseIndicator"),
        //roll2
        SquareSpinIndicator("SquareSpinIndicator"),
        BallClipRotateMultipleIndicator("BallClipRotateMultipleIndicator"),
        BallPulseRiseIndicator("BallPulseRiseIndicator"),
        BallRotateIndicator("BallRotateIndicator"),
        //roll3
        CubeTransitionIndicator("CubeTransitionIndicator"),
        BallZigZagIndicator("BallZigZagIndicator"),
        BallZigZagDeflectIndicator("BallZigZagDeflectIndicator"),
        BallTrianglePathIndicator("BallTrianglePathIndicator"),
        //roll4
        BallScaleIndicator("BallScaleIndicator"),
        LineScaleIndicator("LineScaleIndicator"),
        LineScalePartyIndicator("LineScalePartyIndicator"),
        BallScaleMultipleIndicator("BallScaleMultipleIndicator"),
        //roll5
        BallPulseSyncIndicator("BallPulseSyncIndicator"),
        BallBeatIndicator("BallBeatIndicator"),
        LineScalePulseOutIndicator("LineScalePulseOutIndicator"),
        LineScalePulseOutRapidIndicator("LineScalePulseOutRapidIndicator"),
        //roll6
        BallScaleRippleIndicator("BallScaleRippleIndicator"),
        BallScaleRippleMultipleIndicator("BallScaleRippleMultipleIndicator"),
        BallSpinFadeLoaderIndicator("BallSpinFadeLoaderIndicator"),
        LineSpinFadeLoaderIndicator("LineSpinFadeLoaderIndicator"),
        //roll7
        TriangleSkewSpinIndicator("TriangleSkewSpinIndicator"),
        PacmanIndicator("PacmanIndicator"),
        BallGridBeatIndicator("BallGridBeatIndicator"),
        SemiCircleSpinIndicator("SemiCircleSpinIndicator");
        LoadingIconStyle(String indicatorName){this.indicatorName = indicatorName;}
        private String indicatorName;
        public String getIndicatorName() {
            return indicatorName;
        }
        public void setIndicatorName(String indicatorName) {
            this.indicatorName = indicatorName;
        }
    }

    /**
     * 消息对话框样式
     */
    public enum MsgStyle{
    }

}
