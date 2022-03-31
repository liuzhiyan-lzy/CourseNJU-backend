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

    /**
     * @path 教务员主页 - 用户管理 - 创建账号 - 单个创建
     * @input userInfo {
     *     type,         // 1: student, 2: teacher
     *     user_id,
     *     user_name,
     *     identity_id,
     *     user_sex,     // 1: male, 2: female
     *     college,
     *     email
     * }
     * @return User
     */
    @RequestMapping("/register")
    public CommonResult Register() {
        Map<String, String[]> userInfo = request.getParameterMap();
        String userId = userInfo.get("user_id")[0];
        if (userId.equals("") || userService.isExist(userId))
            return CommonResult.validateFailed("用户已存在");
        User user = new User(userId);
        user.setPassword(userInfo.get("user_id")[0]);
        user.setType(Integer.parseInt(userInfo.get("type")[0]));
        setUserInfo(userInfo, user);
        userService.addUser(user);
        return CommonResult.success(user, "注册成功");
    }

    /**
     * @path 教务员主页 - 用户管理 - 创建账号 - 批量导入
     * @input file  // Excel 学生信息
     * @return List<User>
     */
    @RequestMapping("/import")
    public CommonResult Import() {
        // TODO handle file
        return CommonResult.success(null);
    }

    /**
     * @path 首页 - 登录
     * @input {
     *     user_id,
     *     password
     * }
     * @return User
     */
    @RequestMapping("/login")
    public CommonResult Login() {
        String userId = request.getParameter("user_id");
        String password = request.getParameter("password");
        if (!userService.isExist(userId))
            return CommonResult.validateFailed("用户不存在");
        User user = userService.getUserById(userId);
        if (!password.equals(user.getPassword()))
            return CommonResult.validateFailed("密码错误");
        return CommonResult.success(user, "登录成功");
    }

    /**
     * @path Debug
     * @input user_id
     * @return null
     */
    @RequestMapping("/delete")
    public CommonResult Delete() {
        String userId = request.getParameter("user_id");
        User user = userService.getUserById(userId);
        if (user == null)
            return CommonResult.validateFailed("用户不存在");
        userService.deleteUser(userId);
        return CommonResult.success(null, "删除用户成功");
    }

    /**
     * @path 教务员主页 - 用户管理 - 查看详情 - 更新信息
     * @input user_info {
     *     user_id,
     *     password,
     *     user_name,
     *     identity_id,
     *     user_sex,     // 1: male, 2: female
     *     college,
     *     email
     * }
     * @return User
     */
    @RequestMapping("/update")
    public CommonResult Update() {
        Map<String, String[]> userInfo = request.getParameterMap();
        String userId = userInfo.get("user_id")[0];
        if (!userService.isExist(userId))
            return CommonResult.validateFailed("用户不存在");
        User user = userService.getUserById(userId);
        user.setPassword(userInfo.get("password")[0]);
        setUserInfo(userInfo, user);
        userService.updateUser(user);
        return CommonResult.success(user, "更新用户成功");
    }

    /**
     * @path {
     *     学生主页 - 个人信息 - 修改密码
     *     教师主页 - 个人信息 - 修改密码
     * }
     * @input {
     *     user_id,
     *     password
     * }
     * @return User
     */
    @RequestMapping("/update-pwd")
    public CommonResult UpdatePwd() {
        String userId = request.getParameter("user_id");
        if (!userService.isExist(userId))
            return CommonResult.validateFailed("用户不存在");
        User user = userService.getUserById(userId);
        String password = request.getParameter("password");
        if (password.equals(""))
            return CommonResult.validateFailed("密码为空");
        user.setPassword(password);
        return CommonResult.success(user, "修改密码成功");
    }

    /**
     * @path {
     *     教务员主页 - 用户管理 - 查看详情
     *     学生主页 - 个人信息
     *     教师主页 - 个人信息
     * }
     * @input user_id
     * @return User
     */
    @RequestMapping("/get")
    public CommonResult GetUserById() {
        String userId = request.getParameter("user_id");
        if (!userService.isExist(userId))
            return CommonResult.validateFailed("用户不存在");
        User user = userService.getUserById(userId);
        return CommonResult.success(user, "查询用户成功");
    }

    /**
     * @path 教务员主页 - 用户管理
     * @input null
     * @return List<User>
     */
    @RequestMapping("/list")
    public CommonResult GetAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users == null)
            return CommonResult.failed("查询所有用户错误");
        return CommonResult.success(users, "查询所有用户成功");
    }

    private void setUserInfo(Map<String, String[]> userInfo, User user) {
        user.setUserName(userInfo.get("user_name")[0]);
        user.setUserSex(Integer.parseInt(userInfo.get("user_sex")[0]));
        user.setIdentityId(userInfo.get("identity_id")[0]);
        user.setCollege(userInfo.get("college")[0]);
        user.setEmail(userInfo.get("email")[0]);
    }
}
