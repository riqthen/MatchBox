package com.example.administrator.matchbox.weiget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.matchbox.R;
import com.example.administrator.matchbox.interfaces.IGetString;
import com.example.administrator.matchbox.utils.LogUtils;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Administrator on 2016/11/24.
 */

// TODO 国家选择列表的侧边ABC...
// 自定义View 可以不要OnDraw
public class IndexBar extends LinearLayout implements View.OnClickListener {
    //排序
    Set<String> set = new TreeSet<>();

    public IndexBar(Context context) {
        super(context);
        setOrientation(VERTICAL);
    }

    public IndexBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    //开始设置数据
    public void setIndex(List<? extends IGetString> list) {     //? extends IGetString表示，该集合的对象继承了这个接口之后，集合元素都有该接口下的方法
        this.removeAllViews();
        set.clear();
        for (IGetString iGetString : list) {
            if (iGetString != null) {   //如果接口对象不为空，则实现该接口的方法
                String str = iGetString.getString();
                LogUtils.e(str);
                if (!TextUtils.isEmpty(str)) {
                    set.add(str.substring(0, 1).toUpperCase());
                }
            }
        }
        //开始绘制
        TextView start = new TextView(getContext());    //创建TextView
        start.setText("☆");
        start.setGravity(Gravity.CENTER);
        start.setPadding(5, 5, 5, 5);
        start.setTextColor(getResources().getColor(R.color.textColor));
        start.setTag("☆");
        this.addView(start);
        start.setOnClickListener(this);
        for (String s : set) {
            TextView tv = new TextView(getContext());
            tv.setText(s);
            tv.setGravity(Gravity.CENTER);
            tv.setPadding(2, 2, 2, 2);
            tv.setTextSize(12);
            tv.setTextColor(getResources().getColor(R.color.textColor));
            tv.setOnClickListener(this);
            tv.setTag(s);   //setTag
            this.addView(tv);
        }
    }

    @Override
    public void onClick(View v) {
        if (onIndexClickListener != null) {
            String tag = v.getTag().toString();     //getTag 的用法
            onIndexClickListener.onIndexBarClick(tag);
        }
    }


    OnIndexClickListener onIndexClickListener;

    public void setOnIndexClickListener(OnIndexClickListener onIndexClickListener) {
        this.onIndexClickListener = onIndexClickListener;
    }

    public interface OnIndexClickListener {
        void onIndexBarClick(String letter);
    }

}
