package com.example.coursenju.service;

import com.example.coursenju.dao.GradeDao;
import com.example.coursenju.entity.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    GradeDao gradeDao;

    public List<Grade> getGradesByCourseId(String courseId) {
        return gradeDao.findByCourseId(courseId);
    }

    public List<Grade> getGradesByStudentId(String studentId) {
        return gradeDao.findByStudentId(studentId);
    }

    public Grade getGradeByGradeId(String gradeId) {
        return gradeDao.findByGradeId(gradeId);
    }

    public boolean isExist(String gradeId) {
        Grade grade = getGradeByGradeId(gradeId);
        return grade != null;
    }

    public void addGrade(Grade grade) {
        gradeDao.save(grade);
    }

    public Grade getGradeByStudentIdAndCourseId(String studentId, String courseId) {
        return gradeDao.findByStudentIdAndCourseId(studentId, courseId);
    }

    public void deleteGrade(Grade grade) {
        gradeDao.delete(grade);
    }
}
