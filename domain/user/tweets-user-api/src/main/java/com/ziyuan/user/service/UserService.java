package com.ziyuan.user.service;

import com.ziyuan.user.pojo.User;
import com.ziyuan.user.pojo.bo.UserBO;
import com.ziyuan.user.pojo.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FeignClient("tweets-user-service")
@RequestMapping("user")
public interface UserService {

    @GetMapping("auth")
    public boolean auth(@RequestParam String userId, @RequestParam String token);

    @GetMapping("/vo/id")
    public UserVO getUserVOById(@RequestParam String userId);

    @GetMapping("/vo/username")
    public UserVO getUserVOByUsername(@RequestParam String username);

    @GetMapping("/name")
    public User getUserByName(@RequestParam String username);

    @GetMapping("/id")
    public User getUserById(@RequestParam String id);

    @PostMapping("/login")
    public UserVO login(@RequestBody UserBO userBO,
                        HttpServletRequest request,
                        HttpServletResponse response) throws Exception;

    /**
     * @param userBO
     * @return null if failed to sign up
     */
    @PostMapping("/signup")
    public void signup(@RequestBody UserBO userBO) throws Exception;

    @GetMapping("/exists")
    public boolean isUsernameExist(@RequestParam String username);

}
