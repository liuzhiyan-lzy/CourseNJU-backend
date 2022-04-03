package com.example.coursenju.service;

import com.example.coursenju.entity.Course;
import com.example.coursenju.entity.Grade;
import com.example.coursenju.entity.User;
import com.example.coursenju.mapper.GradeMapper;
import com.example.coursenju.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class GradeService {
    @Autowired
    private GradeMapper gradeMapper;

    public Grade addGrade(Course course, User user) {
        Grade grade = new Grade();
        String gradeId = Util.getRandomString(10);
        while (isExist(gradeId))
            gradeId = Util.getRandomString(10);
        grade.setGradeId(gradeId);
        grade.setCourseId(course.getCourseId());
        grade.setCourseName(course.getCourseName());
        grade.setStudentId(user.getUserId());
        grade.setStudentName(user.getUserName());
        gradeMapper.addGrade(grade);
        return grade;
    }

    public void addGrade(Grade grade) {
        gradeMapper.addGrade(grade);
    }

    public void deleteGrade(String gradeId) {
        gradeMapper.deleteGrade(gradeId);
    }

    public void updateGrade(Grade grade) {
        gradeMapper.updateGrade(grade);
    }

    public void updateStudentName(String user_id, String user_name) {
        List<Grade> grades = getGradesByStudentId(user_id);
        for (Grade grade : grades) {
            grade.setStudentName(user_name);
            updateGrade(grade);
        }
    }

    public void updateCourseName(String course_id, String course_name) {
        List<Grade> grades = getGradesByCourseId(course_id);
        for (Grade grade : grades) {
            grade.setCourseName(course_name);
            updateGrade(grade);
        }
    }

    public void confirm(Grade grade) {
        grade.setGradeStatus(3);
        updateGrade(grade);
    }

    public Grade getGradeById(String gradeId) {
        return gradeMapper.getGradeById(gradeId);
    }

    public boolean isExist(String gradeId) {
        Grade grade = gradeMapper.getGradeById(gradeId);
        return grade != null;
    }

    public List<Grade> getGradesByCourseId(String courseId) {
        return gradeMapper.getGradesByCourseId(courseId);
    }

    public List<Grade> getGradesByStudentId(String studentId) {
        return gradeMapper.getGradesByStudentId(studentId);
    }

    public List<Grade> getAllGrades() {
        return gradeMapper.getAllGrades();
    }
}
