package com.example.coursenju.service;

import com.example.coursenju.mapper.UserMapper;
import com.example.coursenju.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void addUser(User user) {
        userMapper.addUser(user);
    }

    public void deleteUser(String userId) {
        userMapper.deleteUser(userId);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public User getUserById(String userId) {
        return userMapper.getUserById(userId);
    }

    public boolean isExist(String userId) {
        if (userId.equals(""))
            return false;
        User user = getUserById(userId);
        return user != null;
    }

    public boolean checkStudent(String userId) {
        User user = getUserById(userId);
        return user != null && user.getType() == 1;
    }

    public boolean checkTeacher(String userId) {
        User user = getUserById(userId);
        return user != null && user.getType() == 2;
    }

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }
}
