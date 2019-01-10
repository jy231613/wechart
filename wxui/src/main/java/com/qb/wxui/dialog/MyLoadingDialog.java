package com.qb.wxui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.qb.wxui.R;

public class MyLoadingDialog extends Dialog {

    public MyLoadingDialog(Context context) {
        super(context);
    }

    private MyLoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context context;
        private String message;
        private boolean isCancelable = true;
        private boolean isCancelOutside = false;

        public Builder(Context context) {
            this.context = context;
        }


        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }


        public Builder setCancelable(boolean isCancelable) {
            this.isCancelable = isCancelable;
            return this;
        }


        public Builder setCancelOutside(boolean isCancelOutside) {
            this.isCancelOutside = isCancelOutside;
            return this;
        }

        public MyLoadingDialog create() {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.my_dialog_loading, null);
            TextView msgText = (TextView) view.findViewById(R.id.tv_loading);
            msgText.setText(message);
            MyLoadingDialog loadingDialog = new MyLoadingDialog(context, R.style.my_dialog_loading);
            loadingDialog.setContentView(view);
            loadingDialog.setCancelable(isCancelable);
            loadingDialog.setCanceledOnTouchOutside(isCancelOutside);
            return loadingDialog;
        }
    }
}

//
//    在baseActivity中初始化
//
//    private MyLoadingDialog dialog;
//
//    public void showLoading(String str){
//        if (dialog == null) {
//            dialog = new MyLoadingDialog
//                    .Builder(this)
//                    .setMessage(str)
//                    .setCancelable(true)
//                    .setCancelOutside(false)
//                    .create();
//        }
//        TextView textView= (TextView) dialog.findViewById(R.id.tv_loading);
//        textView.setText(str);
//        dialog.show();
//    }
//
//    public void dismissLoading(){
//        if (dialog!=null&&dialog.isShowing()){
//            dialog.dismiss();
//        }
//    }
