package com.ziyuan.controller;

import com.ziyuan.enums.CookieKey;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    public static final String USER_COOKIE = CookieKey.USER_COOKIE.value;
    public static final String TOKEN_COOKIE = CookieKey.TOKEN_COOKIE.value;
}
