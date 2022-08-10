package com.example.demoi18n.Enum;

public enum LoginFrom {
    mobileLinkin(0), // 验证码登陆
    oneKeyLinkin(1), // 一键登录
    wechat(2), // 微信
    weibo(3), // 微博
    qq(4), // QQ
    internal(5), // 内部注册
    virtual_friend(6),
    email(7),
    facebook(10),
    apple(11),
    google(12),
    tiktok(13),
    other(99); // 其他

    private final int code;

    LoginFrom(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static LoginFrom findByValue(int value){
        switch (value) {
            case 0:
                return LoginFrom.mobileLinkin;
            case 1:
                return LoginFrom.oneKeyLinkin;
            case 2:
                return LoginFrom.wechat;
            case 3:
                return LoginFrom.weibo;
            case 4:
                return LoginFrom.qq;
            case 5:
                return LoginFrom.internal;
            case 6:
                return LoginFrom.virtual_friend;
            case 7:
                return LoginFrom.email;
            case 10:
                return LoginFrom.facebook;
            case 11:
                return LoginFrom.apple;
            case 12:
                return LoginFrom.google;
            case 13:
                return LoginFrom.tiktok;
            default:
                return LoginFrom.other;
        }

    }

}
