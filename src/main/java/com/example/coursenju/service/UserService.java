package com.example.coursenju.service;

import com.example.coursenju.dao.UserDao;
import com.example.coursenju.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User getUserByUserNumber(String userNumber) {
        return userDao.findByUserNumber(userNumber);
    }

    public boolean isExist(String userNumber) {
        User user = getUserByUserNumber(userNumber);
        return user != null;
    }

    public void addUser(User user) {
        userDao.save(user);
    }
}
