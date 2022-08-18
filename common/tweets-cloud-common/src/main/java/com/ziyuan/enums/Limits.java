package com.ziyuan.enums;

public enum Limits {
    USERNAME_INFO_TTL(60 * 60 * 24 * 7),
    USERID_INFO_TTL(60 * 60 * 24 * 7),
    USER_TOKEN_TTL(60 * 60 * 24 * 7),
    TWEET_INFO_TTL(60 * 60 * 24 * 7),
    TWEET_ID_LIST_MAX_COUNT(100),
    INBOX_ID_LIST_MAX_COUNT(100),
    FANS_TTL(1000),
    FOLLOWING_TTL(60 * 60 * 24 * 7),
    LIKED_TTL(60 * 60 * 24 * 7),
    LIKED_COUNT_TTL(60 * 60 * 24 * 7);

    public final Integer value;

    Limits(Integer value) {
        this.value = value;
    }
}
