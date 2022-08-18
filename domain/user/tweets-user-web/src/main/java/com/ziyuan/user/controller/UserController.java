package com.ziyuan.user.controller;

import com.ziyuan.controller.BaseController;
import com.ziyuan.user.pojo.bo.UserBO;
import com.ziyuan.user.pojo.vo.UserVO;
import com.ziyuan.user.service.UserService;
import com.ziyuan.utils.JSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public JSONResult signup(@RequestBody UserBO userBO) throws Exception {
        String username = userBO.getUsername();
        String password = userBO.getPassword();

        // Checks:
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return JSONResult.errorMsg("username and password must not be null");
        }
        if (username.length() > 30) {
            return JSONResult.errorMsg("username can be at most 30 chars");
        }
        if (password.length() < 6) {
            return JSONResult.errorMsg("password must be at least 6 characters");
        }
        if (password.length() > 30) {
            return JSONResult.errorMsg("password can be at most 30 chars");
        }
        if (userService.isUsernameExist(username)) {
            return JSONResult.errorMsg("username has been registered");
        }


        userService.signup(userBO);
        return JSONResult.ok();
    }

    @PostMapping("/login")
    public JSONResult login(@RequestBody UserBO userBO,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {

        String username = userBO.getUsername();
        String password = userBO.getPassword();

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return JSONResult.errorMsg("username and password must not be null");
        }

        UserVO userVO = userService.login(userBO, request, response);
        if (userVO == null) return JSONResult.errorMsg("username or password is incorrect");
        return JSONResult.ok(userVO);
    }
}
