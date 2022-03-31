package com.example.coursenju.service;

import com.example.coursenju.entity.Course;
import com.example.coursenju.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseMapper courseMapper;

    public void addCourse(Course course) {
        courseMapper.addCourse(course);
    }

    public void deleteCourse(String courseId) {
        courseMapper.deleteCourse(courseId);
    }

    public void updateCourse(Course course) {
        courseMapper.updateCourse(course);
    }

    public Course getCourseById(String courseId) {
        return courseMapper.getCourseById(courseId);
    }

    public boolean isExist(String courseId) {
        Course course = courseMapper.getCourseById(courseId);
        return course != null;
    }

    public List<Course> getCourseByTeacherId(String teacherId) {
        return courseMapper.getCourseByTeacherId(teacherId);
    }

    public List<Course> getAllCourses() {
        return courseMapper.getAllCourses();
    }
}
