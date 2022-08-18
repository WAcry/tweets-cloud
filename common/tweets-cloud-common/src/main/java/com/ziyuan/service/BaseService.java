package com.ziyuan.service;

import com.ziyuan.enums.CookieKey;
import com.ziyuan.enums.KafkaTopic;
import com.ziyuan.enums.Limits;
import com.ziyuan.enums.RedisPrefix;


public class BaseService {

    // Redis Prefix & Limits
    public static final String USERNAME_INFO = RedisPrefix.USERNAME_INFO.value;
    public static final Integer USERNAME_INFO_TTL = Limits.USERNAME_INFO_TTL.value;
    public static final String USERID_INFO = RedisPrefix.USERID_INFO.value;
    public static final Integer USERID_INFO_TTL = Limits.USERID_INFO_TTL.value;
    public static final String USER_TOKEN = RedisPrefix.USER_TOKEN.value;
    public static final Integer USER_TOKEN_TTL = Limits.USER_TOKEN_TTL.value;
    public static final String TWEET_INFO = RedisPrefix.TWEET_INFO.value;
    public static final Integer TWEET_INFO_TTL = Limits.TWEET_INFO_TTL.value;
    public static final String TWEET_ID_LIST = RedisPrefix.TWEET_ID_LIST.value;
    public static final Integer TWEET_ID_LIST_MAX_COUNT = Limits.TWEET_ID_LIST_MAX_COUNT.value;
    public static final String STARS = RedisPrefix.STARS.value;
    public static final String FANS = RedisPrefix.FANS.value;
    public static final Integer FANS_TTL = Limits.FANS_TTL.value;
    public static final String FOLLOWING = RedisPrefix.FOLLOWING.value;
    public static final Integer FOLLOWING_TTL = Limits.FOLLOWING_TTL.value;
    public static final String INBOX_ID_LIST = RedisPrefix.INBOX_ID_LIST.value;
    public static final Integer INBOX_ID_LIST_MAX_COUNT = Limits.INBOX_ID_LIST_MAX_COUNT.value;
    public static final String MORE_TWEET_ID_LIST_IN_DB = RedisPrefix.MORE_TWEET_ID_LIST_IN_DB.value;
    public static final String MORE_INBOX_ID_LIST_IN_DB = RedisPrefix.MORE_INBOX_ID_LIST_IN_DB.value;
    public static final String LIKED = RedisPrefix.LIKED.value;
    public static final Integer LIKED_TTL = Limits.LIKED_TTL.value;

    // Kafka Topics
    public static final String INSERT_USER = KafkaTopic.INSERT_USER.value;
    public static final String INSERT_TWEET = KafkaTopic.INSERT_TWEET.value;
    public static final String INSERT_INBOX_MSG = KafkaTopic.INSERT_INBOX_MSG.value;
    public static final String DELETE_TWEET = KafkaTopic.DELETE_TWEET.value;

    public static final String ADD_RELATION = KafkaTopic.ADD_RELATION.value;
    public static final String DELETE_RELATION = KafkaTopic.DELETE_RELATION.value;

    public static final String ADD_LIKE = KafkaTopic.ADD_LIKE.value;
    public static final String DELETE_LIKE = KafkaTopic.DELETE_LIKE.value;

    // Cookie Key
    public static final String TOKEN_COOKIE = CookieKey.TOKEN_COOKIE.value;
    public static final String USER_COOKIE = CookieKey.USER_COOKIE.value;
}
