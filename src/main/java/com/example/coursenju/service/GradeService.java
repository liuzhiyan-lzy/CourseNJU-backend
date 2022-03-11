package com.example.coursenju.service;

import com.example.coursenju.entity.Grade;
import com.example.coursenju.mapper.GradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class GradeService {
    @Autowired
    private GradeMapper gradeMapper;

    public void addGrade(Grade grade) {
        gradeMapper.addGrade(grade);
    }

    public void addGradesFromFile(File grades) {
        // TODO
    }

    public void deleteGrade(String gradeId) {
        gradeMapper.deleteGrade(gradeId);
    }

    public void updateGrade(Grade grade) {
        gradeMapper.updateGrade(grade);
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
