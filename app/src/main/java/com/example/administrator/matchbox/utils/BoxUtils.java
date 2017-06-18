package com.example.administrator.matchbox.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;

import com.example.administrator.matchbox.weiget.CursorDialog;
import com.example.administrator.matchbox.weiget.ProgressWheel;

/**
 * Created by Administrator on 2016/11/25.
 */
// TODO 等待时的进度圈Dialog
public class BoxUtils {
    public static final Dialog showProgressDialog(Context context) {
        RelativeLayout layout = new RelativeLayout(context);
        layout.setBackgroundColor(Color.TRANSPARENT);
        ProgressWheel view = new ProgressWheel(context);
        view.setBarColor(Color.rgb(0x55, 0x88, 0xFF));
        view.spin();
        view.setBackgroundColor(Color.TRANSPARENT);
        layout.addView(view);
        //给组件宽高的方法
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        view.setLayoutParams(params);
        Dialog dialog = new CursorDialog.Builder(context)
                .notFloating()
                .setView(layout)
                .builder();
        return dialog;
    }
}
