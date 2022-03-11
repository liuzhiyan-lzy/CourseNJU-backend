package com.example.coursenju.mapper;

import com.example.coursenju.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {
    void addCourse(Course course);
    void deleteCourse(String courseId);
    void updateCourse(Course course);
    Course getCourseById(String courseId);
    List<Course> getCourseByTeacherId(String teacherId);
    List<Course> getAllCourses();
}
