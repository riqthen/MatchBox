package com.example.administrator.matchbox.utils;

import com.example.administrator.matchbox.bean.CountriesBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2016/11/24.
 */
// TODO 注册时候的国家列表
public class CountriesUtils {

    private static List<CountriesBean> list;

    public static List<CountriesBean> getAllCountriesList() {
        if (list == null) {
            list = getCountriesList();
        }
        return list;
    }

    //获取国家列表的方法
    private static List<CountriesBean> getCountriesList() {
        List<CountriesBean> list = new ArrayList<>();
        for (Map.Entry<Character, ArrayList<String[]>> ent : SMSSDK.getGroupedCountryList().entrySet()) {
            ArrayList<String[]> cl = ent.getValue();
            for (String[] paire : cl) {
                CountriesBean bean = new CountriesBean(paire[0], paire[1]);
                list.add(bean);
            }
        }
        LogUtils.e("国家和区号" + list.toString());
        return list;
    }
    /*
    StringBuffer stringBuffer = new StringBuffer();
    for (Map.Entry<Character, ArrayList<String[]>> ent : SMSSDK.getGroupedCountryList().entrySet()) {
        ArrayList<String[]> cl = ent.getValue();
        for (String[] paire : cl) {
            stringBuffer.append("国家(" + paire[0] + ")---(" + "区号：" + paire[1] + ")\n");
            Log.e("----------", "国家(" + paire[0] + ")---(" + "区号：" + paire[1] + ")\n");
        }
    }
    */
}


