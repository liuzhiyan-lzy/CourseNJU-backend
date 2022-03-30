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
    public CommonResult Register() {
        Map<String, String[]> userInfo = request.getParameterMap();
        String userId = userInfo.get("user_id")[0];
        if (userId.equals("") || userService.isExist(userId))
            return CommonResult.validateFailed("用户已存在");
        User user = new User(userId);
        setUserInfo(userInfo, user);
        userService.addUser(user);
        return CommonResult.success(user, "注册成功");
    }

    @RequestMapping("/login")
    public CommonResult Login() {
        String userId = request.getParameter("user_id");
        String password = request.getParameter("password");
        System.out.println("login: " + userId + " " + password);
        if (!userService.isExist(userId))
            return CommonResult.validateFailed("用户不存在");
        User user = userService.getUserById(userId);
        if (!password.equals(user.getPassword()))
            return CommonResult.validateFailed("密码错误");
        return CommonResult.success(user, "登录成功");
    }

    @RequestMapping("/delete")
    public CommonResult Delete() {
        String userId = request.getParameter("user_id");
        User user = userService.getUserById(userId);
        if (user == null)
            return CommonResult.validateFailed("用户不存在");
        userService.deleteUser(userId);
        return CommonResult.success(null, "删除用户成功");
    }

    @RequestMapping("/update")
    public CommonResult Update() {
        Map<String, String[]> userInfo = request.getParameterMap();
        String userId = userInfo.get("user_id")[0];
        if (!userService.isExist(userId))
            return CommonResult.validateFailed("用户不存在");
        User user = userService.getUserById(userId);
        setUserInfo(userInfo, user);
        userService.updateUser(user);
        return CommonResult.success(user, "更新用户成功");
    }

    @RequestMapping("/get")
    public CommonResult GetUserById() {
        String userId = request.getParameter("user_id");
        if (!userService.isExist(userId))
            return CommonResult.validateFailed("用户不存在");
        User user = userService.getUserById(userId);
        return CommonResult.success(user, "查询用户成功");
    }

    @RequestMapping("/list")
    public CommonResult GetAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users == null)
            return CommonResult.failed("查询所有用户错误");
        return CommonResult.success(users, "查询所有用户成功");
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
