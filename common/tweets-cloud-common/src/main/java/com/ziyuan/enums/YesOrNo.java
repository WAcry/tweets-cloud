package com.ziyuan.enums;

public enum YesOrNo {
    NO(1),
    YES(2);

    public final Integer value;

    YesOrNo(Integer value) {
        this.value = value;
    }
}
