package com.ziyuan.enums;
public enum CookieKey {
    USER_COOKIE("user"),
    TOKEN_COOKIE("token");

    public final String value;

    CookieKey(String value) {
        this.value = value;
    }
}
