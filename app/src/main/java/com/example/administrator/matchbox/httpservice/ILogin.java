package com.example.administrator.matchbox.httpservice;

import com.example.administrator.matchbox.bean.LoginBean;
import com.example.administrator.matchbox.utils.ServerInterface;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2016/11/30.
 */
// TODO Retrofit的使用
public interface ILogin {
    //    二级路径
    @POST(ServerInterface.USER_LOGIN)
    //    Http请求类型
    @FormUrlEncoded    //  这个是键值对，还有带文件上传Multipart，流类型Streaming（基本不用）
    //    请求参数
    Call<LoginBean> login(@Field("name") String username, @Field("password") String password);
}
