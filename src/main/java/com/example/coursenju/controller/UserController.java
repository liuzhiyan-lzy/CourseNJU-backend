package com.example.coursenju.controller;

import com.example.coursenju.entity.User;
import com.example.coursenju.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public ResponseData Register() {
        Map<String, String[]> userInfo = request.getParameterMap();
        String userId = userInfo.get("user_id")[0];
        boolean isExist = userService.isExist(userId);
        if (isExist)
            return ResponseDataUtil.buildError("404", "用户已存在");
        User user = new User();
        user.setUserId(userId);
        setUserInfo(userInfo, user);
        userService.addUser(user);
        return ResponseDataUtil.buildSuccess("200", "注册成功");
    }

    @RequestMapping("/login")
    public ResponseData Login() {
        String userId = request.getParameter("user_id");
        String password = request.getParameter("password");
        User user = userService.getUserById(userId);
        if (user == null)
            return ResponseDataUtil.buildError("404", "用户不存在");
        if (!password.equals(user.getPassword()))
            return ResponseDataUtil.buildError("400", "密码错误");
        return ResponseDataUtil.buildSuccess("200", "登录成功", user);
    }

    @RequestMapping("/delete")
    public ResponseData Delete() {
        String userId = request.getParameter("user_id");
        User user = userService.getUserById(userId);
        if (user == null)
            return ResponseDataUtil.buildError("404", "用户不存在");
        userService.deleteUser(userId);
        return ResponseDataUtil.buildSuccess("200", "删除成功");
    }

    @RequestMapping("/update")
    public ResponseData Update() {
        Map<String, String[]> userInfo = request.getParameterMap();
        String userId = userInfo.get("user_id")[0];
        User user = userService.getUserById(userId);
        if (user == null)
            return ResponseDataUtil.buildError("404", "用户不存在");
        setUserInfo(userInfo, user);
        userService.updateUser(user);
        return ResponseDataUtil.buildSuccess("200", "更新成功");
    }

    @RequestMapping("/get")
    public ResponseData GetUserById() {
        String userId = request.getParameter("user_id");
        User user = userService.getUserById(userId);
        if (user == null)
            return ResponseDataUtil.buildError("404", "用户不存在");
        return ResponseDataUtil.buildSuccess("200", "查询成功", user);
    }

    @RequestMapping("/list")
    public ResponseData GetAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users == null)
            return ResponseDataUtil.buildError("500", "查询错误");
        return ResponseDataUtil.buildSuccess("200", "查询成功", users);
    }

    private void setUserInfo(Map<String, String[]> userInfo, User user) {
        user.setPassword(userInfo.get("password")[0]);
        user.setType(Integer.parseInt(userInfo.get("type")[0]));
        user.setUserName(userInfo.get("user_name")[0]);
        user.setUserSex(Integer.parseInt(userInfo.get("user_sex")[0]));
        user.setIdentityId(userInfo.get("identity_id")[0]);
        user.setCollege(userInfo.get("college")[0]);
        user.setEmail(userInfo.get("email")[0]);
    }
}
