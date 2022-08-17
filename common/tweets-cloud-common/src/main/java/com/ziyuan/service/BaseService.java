package com.ziyuan.service;

import com.ziyuan.enums.Limits;
import com.ziyuan.enums.RedisPrefix;


public class BaseService {
    public static final String USERNAME_INFO = RedisPrefix.USERNAME_INFO.value;
    public static final Integer USERNAME_INFO_REDIS_TTL = Limits.USERNAME_INFO_REDIS_TTL.value;
    public static final String USERID_INFO = RedisPrefix.USERID_INFO.value;
    public static final Integer USERID_INFO_REDIS_TTL = Limits.USERID_INFO_REDIS_TTL.value;
    public static final String TWEET_INFO = RedisPrefix.TWEET_INFO.value;
    public static final Integer TWEET_INFO_REDIS_TTL = Limits.TWEET_INFO_REDIS_TTL.value;
    public static final String TWEET_ID_LIST = RedisPrefix.TWEET_ID_LIST.value;
    public static final Integer TWEET_ID_LIST_MAX_COUNT = Limits.TWEET_ID_LIST_MAX_COUNT.value;
    public static final String STARS = RedisPrefix.STARS.value;
    public static final String FANS = RedisPrefix.FANS.value;
    public static final Integer FANS_MAX_COUNT = Limits.FANS_MAX_COUNT.value;
    public static final String FOLLOWING = RedisPrefix.FOLLOWING.value;
    public static final Integer FOLLOWING_REDIS_TTL = Limits.FOLLOWING_REDIS_TTL.value;
    public static final String INBOX_ID_LIST = RedisPrefix.INBOX_ID_LIST.value;
    public static final Integer INBOX_ID_LIST_MAX_COUNT = Limits.INBOX_ID_LIST_MAX_COUNT.value;
    public static final String MORE_TWEET_ID_LIST_IN_DB = RedisPrefix.MORE_TWEET_ID_LIST_IN_DB.value;
    public static final String MORE_INBOX_ID_LIST_IN_DB = RedisPrefix.MORE_INBOX_ID_LIST_IN_DB.value;
    public static final String LIKED = RedisPrefix.LIKED.value;
    public static final Integer LIKED_REDIS_TTL = Limits.LIKED_REDIS_TTL.value;
}
