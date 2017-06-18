package com.example.administrator.matchbox.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/11/23.
 */

// TODO 格式化手机号码
public class NumberTextWatcher implements TextWatcher {
    private EditText numberEditText;
    private int beforeLen = 0;
    private int afterLen = 0;


    public NumberTextWatcher(EditText numberEditText) {
        this.numberEditText = numberEditText;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        beforeLen = s.length();
    }

    @Override
    public void afterTextChanged(Editable s) {
        String text = numberEditText.getText().toString();
        afterLen = text.length();

        if (afterLen > beforeLen) {
            if (text.length() == 4 || text.length() == 9) {
                numberEditText.setText(new StringBuffer(text).insert(text.length() - 1, " ").toString());   //insert是StringBuffer的方法
                numberEditText.setSelection(numberEditText.getText().length());
            }
        } else {
            if (text.startsWith(" ")) {
                numberEditText.setText(new StringBuffer(text).delete(afterLen - 1, afterLen).toString());
                numberEditText.setSelection(numberEditText.getText().length());
            }
        }
        if (textChangeListener != null)
            textChangeListener.onTextChanged(numberEditText.getText().toString().replace(" ", ""));
    }

    //创建接口的三个必备
    private TextChangeListener textChangeListener;

    public void setTextChangeListener(TextChangeListener textChangeListener) {
        this.textChangeListener = textChangeListener;
    }

    public interface TextChangeListener {
        public void onTextChanged(String text);
    }
}
