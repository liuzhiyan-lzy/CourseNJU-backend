package com.example.coursenju.dao;

import com.example.coursenju.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByUserId(String userId);
}
