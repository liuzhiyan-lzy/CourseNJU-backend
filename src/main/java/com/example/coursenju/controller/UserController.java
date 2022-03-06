package com.example.coursenju.controller;

import com.example.coursenju.entity.User;
import com.example.coursenju.service.UserService;
import org.apache.catalina.connector.Response;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("register")
    @ResponseBody
    public String Register(@RequestParam(value = "username") String username,
                           @RequestParam(value = "password") String password) {
        boolean isExist = userService.isExist(username);
        if (isExist)
            return "用户名已存在";

        User user = new User();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        password = HtmlUtils.htmlEscape(password);
        user.setPassword(password);

        userService.addUser(user);
        return "Success";
    }

}
