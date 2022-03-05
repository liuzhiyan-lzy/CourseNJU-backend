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
        user.setUsername(username);
        user.setPassword(password);

        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithm = "md5";
        String pwdAfterHash = new SimpleHash(algorithm, password, salt, times).toString();

        user.setSalt(salt);
        user.setPassword(pwdAfterHash);
        userService.addUser(user);
        return "Success";
    }

}
