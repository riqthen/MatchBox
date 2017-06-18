package com.example.administrator.matchbox.interfaces;

import com.example.administrator.matchbox.bean.UserBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */

public interface IUser {
    int TYPE_ATT = 1;
    int TYPE_FANS = 2;


    //所有的用户操作都在这里
    //注册
    //登录
    //获取用户资料
    //修改资料
    //修改密码
    void register(String username, String password, IBeanCallback<Integer> callback);

    void login(String username, String password, IBeanCallback<Integer> callback);

    void cancel();

    void update(String userId, String nickName, String path, String myInfo, String sex, IBeanCallback callback);

    void getUserInfo(String userId, IBeanCallback<UserBean> callback);

    //关注好友
    void attUser(String userId, String friendId, IBeanCallback callback);

    //获取我关注的 或者 关注我的
    void getAllAttention(int type, String userId, IBeanCallback<List<UserBean>> callback);


}
