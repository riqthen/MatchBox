package com.example.administrator.matchbox.bean;

import com.example.administrator.matchbox.interfaces.IGetString;
import com.example.administrator.matchbox.utils.CharacterParser;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/24.
 */
//注册时的国家列表数据
public class CountriesBean implements IGetString ,Serializable{

    @Override
    public String toString() {
        return "CountriesBean{" +
                "country='" + country + '\'' +
                ", areaCode='" + areaCode + '\'' +
                '}';
    }

    private String country; //国家
    private String areaCode;    //区号

    public CountriesBean(String country, String areaCode) {
        this.country = country;
        this.areaCode = areaCode;
    }

    public CountriesBean() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    // pinyin4j
    @Override
    public String getString() {
        //返回拼音
        return CharacterParser.getInstance().getSelling(country);
    }
}
