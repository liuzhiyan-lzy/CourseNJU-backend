package com.example.coursenju.dao;

import com.example.coursenju.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeDao extends JpaRepository<Grade, Integer> {
    List<Grade> findByCourseId(String courseId);
    List<Grade> findByStudentId(String studentId);
    Grade findByGradeId(String gradeId);
    Grade findByStudentIdAndCourseId(String studentId, String courseId);
}
