package com.example.coursenju.controller;

import com.example.coursenju.entity.User;
import com.example.coursenju.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("register")
    @ResponseBody
    public ResponseData Register(@RequestParam(value = "username") String username,
                                 @RequestParam(value = "password") String password,
                                 @RequestParam(value = "type") String type) {
        boolean isExist = userService.isExist(username);
        if (isExist)
            return ResponseDataUtil.buildError("Username exists");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if (type.equals(""))
            user.serType(1);
        else {
            int t = Integer.parseInt(type);
            user.serType(t);
        }

        userService.addUser(user);
        return ResponseDataUtil.buildSuccess(user);
    }

    @CrossOrigin
    @PostMapping("login")
    @ResponseBody
    public ResponseData Login(@RequestParam(value = "username") String username,
                              @RequestParam(value = "password") String password) {
        boolean isExist = userService.isExist(username);
        if (!isExist)
            return ResponseDataUtil.buildError("Not Register");

        User user = userService.getUserByUsername(username);
        if (!password.equals(user.getPassword()))
            return ResponseDataUtil.buildError("Wrong password");

        return ResponseDataUtil.buildSuccess(user);
    }
}
