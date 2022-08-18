package com.ziyuan.enums;

public enum KafkaTopic {
    INSERT_USER("insert_user"),
    INSERT_TWEET("insert_tweet"),
    INSERT_INBOX_MSG("insert_inbox_msg"),
    DELETE_TWEET("delete_tweet"),

    ADD_RELATION("add_relation"),
    DELETE_RELATION("delete_relation"),

    ADD_LIKE("add_like"),
    DELETE_LIKE("delete_like");

    public final String value;

    KafkaTopic(String value) {
        this.value = value;
    }
}
