package com.example.administrator.matchbox.bean;

/**
 * Created by Administrator on 2016/11/29.
 */

public class RegisterBean {

    private String message;
    private String result;
    private int userId;

    @Override
    public String toString() {
        return "RegisterBean{" +
                "message='" + message + '\'' +
                ", result='" + result + '\'' +
                ", userId=" + userId +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
