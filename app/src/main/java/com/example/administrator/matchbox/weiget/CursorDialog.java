package com.example.administrator.matchbox.weiget;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.example.administrator.matchbox.R;

/**
 * Created by Administrator on 2016/11/23. 3
 */

/**
 * 使用Builder模式来建立
 * 1.内部类Builder
 */
//Dialog
public class CursorDialog extends Dialog {

    private CursorDialog(Context context) {
        super(context);
    }

    private CursorDialog(Context context, int themeResId) {
        super(context, themeResId);
    }


    //此处接口回调，如果没有onTaskFinish()方法，则调用onTaskFinish()方法。
    // 接口回调的意义，即如果没有A,则调用A
    //加载一个异步任务
    public void addTask(/*final Runnable runnable,*/ final Callback callback) {  //回调监听
        //两种方式
//        new Thread() {
//            @Override
//            public void run() {
//                runnable.run();
//                if (callback != null)
//                    callback.onTaskFinish();
//            }
//        }.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onTaskFinish();
                }
            }
        }).start();
    }

    public interface Callback {
        void onTaskFinish();
    }

    //重写show方法
    public void show(final int time) {
        super.show();   //父类的show方法，直接show，接下来添加延迟关闭的方法
        //延迟关闭
        final Handler handler = new Handler() { //主线程的，所以要Handler
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);   //发送任何消息都关闭
                dismiss();
            }
        };
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(1);
            }
        }.start();
    }


    public static class Builder {   //非静态的话，上下文可以getContext等
        //默认浮动，无标题栏，不全屏

        private final Context mContext;
        //参数

        private boolean isFloating = true;  //浮动 窗口化
        private String title;   //默认false
        private boolean isFull; //是否全屏 默认false

        private View layout;

        //Builder的构造方法
        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setLayout(int res) {
            layout = View.inflate(mContext, res, null);
            return this;
        }

        public Builder setView(View v) {
            layout = v;
            return this;
        }

        //是否浮动
        public Builder notFloating() {
            isFloating = false;
            return this;
        }

        //有title则是有title栏
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        //是否全屏
        public Builder full() {
            isFull = true;
            isFloating = false;
            return this;
        }

        public CursorDialog builder() {
            CursorDialog dialog;
            if (isFloating) {   //true 是浮动
                //浮动的
                if (TextUtils.isEmpty(title)) { //没有有title
                    //给Dialog设置样式
                    //浮动，没有标题栏，默认有状态栏
                    dialog = new CursorDialog(mContext, R.style.CursorDialogThemeNotTitle);
                } else {    //浮动而又标题栏，则是正常的Dialog
                    dialog = new CursorDialog(mContext);
                }
            } else {    //非浮动
                if (TextUtils.isEmpty(title)) { //没有标题栏
                    if (isFull) {   //即闪屏页
                        //非浮动，没有标题栏，没有状态栏
                        dialog = new CursorDialog(mContext, R.style.CursorDialogNotTitleFullTheme);
                    } else {
                        //非浮动，没有标题栏，有状态栏
                        dialog = new CursorDialog(mContext, R.style.CursorDialogNotFloatNotTitileTheme);
                    }
                } else {    //有标题栏
                    if (isFull) {
                        //非浮动，有标题栏，有状态栏
                        dialog = new CursorDialog(mContext, R.style.CursorDialogFullTheme);
                    } else {
                        //非浮动，有标题栏，没有状态栏
                        dialog = new CursorDialog(mContext, R.style.CursorDialogNotFloatTheme);
                    }
                }
            }
            if (layout == null) {
                throw new IllegalArgumentException("view不能为空");
            }
            dialog.setContentView(layout);  //给dialog设置视图
            return dialog;
        }

        //dialog的点击事件
        public Builder setViewClick(int id, View.OnClickListener listener) {
            View view = layout.findViewById(id);
            view.setOnClickListener(listener);
            return this;
        }

        //如果dialog里面是一个ListView
        public Builder setViewClick(int id, AdapterView.OnItemClickListener listener) {
            View view = layout.findViewById(id);
            if (view instanceof AdapterView) {  //这是view是AdapterView的子类的实例，或者是否是AdapterView的实例意思
                AdapterView v = (AdapterView) view;
                v.setOnItemClickListener(listener);
            }
            return this;
        }
    }


}