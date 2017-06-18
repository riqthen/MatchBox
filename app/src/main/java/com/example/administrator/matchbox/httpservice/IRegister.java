package com.example.administrator.matchbox.httpservice;

import com.example.administrator.matchbox.bean.RegisterBean;
import com.example.administrator.matchbox.utils.ServerInterface;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2016/11/29.
 */
//说明见ILogin
public interface IRegister {
    //@POST GET ("path")
    @POST(ServerInterface.USER_REGISTER)
    @FormUrlEncoded
    //键值对
    Call<RegisterBean> register(@Field("user.name") String username, @Field("user.passWord") String password);
}
