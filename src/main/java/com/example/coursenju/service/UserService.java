package com.example.coursenju.service;

import com.example.coursenju.dao.UserDao;
import com.example.coursenju.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User getUserByUserId(String userId) {
        return userDao.findByUserId(userId);
    }

    public boolean isExist(String userId) {
        User user = getUserByUserId(userId);
        return user != null;
    }

    public void addUser(User user) {
        userDao.save(user);
    }
}
