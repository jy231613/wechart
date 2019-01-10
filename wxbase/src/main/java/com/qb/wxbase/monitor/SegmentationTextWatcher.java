package com.qb.wxbase.monitor;

import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * ================================================
 * 作    者：贾恒飞
 * 项    目：base
 * 日    期：2018/7/28 0028
 * 包    名：cn.secret.base.monitor
 * 描    述：文本控件EditText的监听器,提供的效果为每四个字符添加分隔符空格
 * ================================================
 */
public class SegmentationTextWatcher implements TextWatcher {
        private StringBuilder sBuilder=new StringBuilder();
    private int beforeChangeLength;
    private boolean isChanged;
    private int spaceNumber = 0;

    private EditText editText;

        public SegmentationTextWatcher(EditText editText){
            this.editText = editText;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int onTextChangeLength = s.length();
            sBuilder.append(s.toString());//保存当前textView显示的内容
            int changedType = 0;
            if(onTextChangeLength ==beforeChangeLength|| onTextChangeLength <= 3||isChanged){
                changedType =0;
                isChanged=false;
            }else if(onTextChangeLength <beforeChangeLength){
                //当前改变长度比改变前长度 小，删除
                changedType =1;
                isChanged=true;
            }else if(onTextChangeLength >beforeChangeLength){
                //当前改变长度比改变前长度 小，输入
                changedType =2;
                isChanged=true;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            beforeChangeLength=s.length();
            if (sBuilder.length() > 0) {//清空sBuilder的缓存内容
                sBuilder.delete(0, sBuilder.length());
            }
            spaceNumber = 0;//计算当前textView的空格符号的数量
            int length=s.length();
            int max=(int) Math.ceil(length/4);//每4 个字符 加一个分隔符
            for(int i=1;i<max;i++){
                if(length>=i*(4+3)&&length<=(i*(4+3)+4)){//加三个空格
                    spaceNumber=i;
                    break;
                }
            }
        }
        @Override
        public void afterTextChanged(Editable s) {
            if(editText==null){
                return;
            }
            editText.removeTextChangedListener(this);
            if(isChanged){
                int location = editText.getSelectionEnd();
                int index = 0;//清除当前空格
                while (index < sBuilder.length()) {
                    if (sBuilder.charAt(index) == ' ') {
                        sBuilder.deleteCharAt(index);
                    } else {
                        index++;
                    }
                }
                //textView内容改变之后，再重新计算下 需要添加空格的 位置
                index = 0;
                int spaceNum = 0;
                while (index < sBuilder.length()) {
                    if ((index == 4 || index == 11 || index == 18)) {
                        String textSpace = " ";
                        sBuilder.insert(index, textSpace);
                        spaceNum++;
                    }
                    index++;
                }
                //计算添加空格后，重新计算下光标的位置，
                if (spaceNum > spaceNumber) {
                    location += (spaceNum - spaceNumber)*3;
                }

                char[] tempChar = new char[sBuilder.length()];
                sBuilder.getChars(0, sBuilder.length(), tempChar, 0);
                String str = sBuilder.toString();
                str = str.toUpperCase();

                if (str.length() > 25){
                    str = str.substring(0,25);
                }

                if (location > str.length()) {
                    location = str.length();
                } else if (location < 0) {
                    location = 0;
                }

                editText.setText(str);
                Editable etable = editText.getText();
                Selection.setSelection(etable, location);
                isChanged = false;
            }else{
                String cardText= editText.getText().toString();
                if(!TextUtils.isEmpty(cardText)){
                    int position=editText.getSelectionEnd();
                    cardText=cardText.toUpperCase();
                    editText.setText(cardText);
                    Editable etable = editText.getText();
                    Selection.setSelection(etable, position);
                }
            }
            editText.addTextChangedListener(this);
        }
}
