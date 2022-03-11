package com.example.coursenju.mapper;

import com.example.coursenju.entity.Grade;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GradeMapper {
    void addGrade(Grade grade);
    void deleteGrade(String gradeId);
    void updateGrade(Grade grade);
    Grade getGradeById(String gradeId);
    List<Grade> getGradesByCourseId(String courseId);
    List<Grade> getGradesByStudentId(String studentId);
    List<Grade> getAllGrades();
}
