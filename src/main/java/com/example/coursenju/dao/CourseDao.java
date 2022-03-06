package com.example.coursenju.dao;

import com.example.coursenju.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDao extends JpaRepository<Course, Integer> {
    Course findByCourseId(String courseId);
}
