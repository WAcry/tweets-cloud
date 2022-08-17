package com.ziyuan.enums;

public enum RedisPrefix {
    // K-V

    USERNAME_INFO("username:"),
    USERID_INFO("userid:"),
    USER_TOKEN("token:"),
    TWEET_INFO("tweet:"),

    // Set

    STARS("stars"), // contains all userIds of star users; never expire
    LIKED("liked:"),  // + tweetId, contains all userIds of liked users for a tweet
    LIKED_COUNT("likedcount:"), // + tweetId, contains the count of liked users for a tweet
    FANS("fans:"), // + userId, contains all userIds of fans for a user, limited by FANS_MAX_COUNT
    FOLLOWING("following:"), // + userId, contains all userIds of following for a user
    MORE_TWEET_ID_LIST("more:tweets"), // there's more tweets in MYSQL that are not in Redis; never expire
    MORE_INBOX_ID_LIST("more:inbox"), // there's more tweets in MYSQL that are not in Redis; never expire

    // Sorted Set

    TWEET_ID_LIST("tweets:"),
    // + userId, contains newest outbox-tweetIds of tweets for a user, limited by TWEET_ID_LIST_MAX_COUNT; never expire

    INBOX_ID_LIST("inbox:");
    // + userId, contains newest inbox-tweetIds of tweets for a user, limited by INBOX_ID_LIST_MAX_COUNT; never expire


    public final String value;

    RedisPrefix(String value) {
        this.value = value;
    }
}
