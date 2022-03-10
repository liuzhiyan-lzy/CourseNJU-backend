package com.example.coursenju.mapper;

import com.example.coursenju.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void addUser(User user);
    void deleteUser(String userId);
    void updateUser(User user);
    User getUserById(String userId);
    List<User> getAllUsers();
}
