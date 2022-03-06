package com.example.coursenju.controller;

import com.example.coursenju.entity.User;
import com.example.coursenju.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("register")
    @ResponseBody
    public ResponseData Register(@RequestBody Map<String, String> userInfo) {
        boolean isExist = userService.isExist(userInfo.get("user_id"));
        if (isExist)
            return ResponseDataUtil.buildError("UserId exists");

        User user = new User();
        user.setUserId(userInfo.get("user_id"));
        user.setPassword(userInfo.get("password"));
        user.setType(Integer.parseInt(userInfo.get("type")));
        user.setUserName(userInfo.get("user_name"));
        user.setUserSex(Integer.parseInt(userInfo.get("user_sex")));
        user.setIdentityId(userInfo.get("identity_id"));
        user.setCollege(userInfo.get("college"));
        user.setEmail(userInfo.get("email"));

        userService.addUser(user);
        return ResponseDataUtil.buildSuccess(user);
    }

    @CrossOrigin
    @PostMapping("login")
    @ResponseBody
    public ResponseData Login(@RequestBody Map<String, String> loginInfo) {
        String userId = loginInfo.get("user_id");
        String password = loginInfo.get("password");
        boolean isExist = userService.isExist(userId);
        if (!isExist)
            return ResponseDataUtil.buildError("Not Register");

        User user = userService.getUserByUserId(userId);
        if (!password.equals(user.getPassword()))
            return ResponseDataUtil.buildError("Wrong password");

        return ResponseDataUtil.buildSuccess(user);
    }
}
