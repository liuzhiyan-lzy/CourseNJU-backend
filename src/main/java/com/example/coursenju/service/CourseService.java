package com.example.coursenju.service;

import com.example.coursenju.dao.CourseDao;
import com.example.coursenju.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    CourseDao courseDao;

    public Course getCourseByCourseId(String courseId) {
        return courseDao.findByCourseId(courseId);
    }

    public boolean isExist(String courseId) {
        Course course = getCourseByCourseId(courseId);
        return course != null;
    }

    public void addCourse(Course course) {
        courseDao.save(course);
    }

    public void updateCourse(Course old, Course course) {
        courseDao.delete(old);
        courseDao.save(course);
    }

    public void deleteCourse(Course course) {
        courseDao.delete(course);
    }
}
