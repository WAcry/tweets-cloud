package com.ziyuan.snowflake.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("tweets-snowflake-service")
public interface SnowFlakeService {
    @GetMapping("sid")
    public String sid();
}
