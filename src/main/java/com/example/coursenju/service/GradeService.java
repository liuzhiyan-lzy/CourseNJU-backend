package com.example.coursenju.service;

import com.example.coursenju.entity.Course;
import com.example.coursenju.entity.Grade;
import com.example.coursenju.entity.User;
import com.example.coursenju.mapper.GradeMapper;
import com.example.coursenju.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    @Autowired
    private GradeMapper gradeMapper;

    /**
     * 根据课程和学生添加成绩单
     */
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

    /**
     * 添加成绩单
     */
    public void addGrade(Grade grade) {
        gradeMapper.addGrade(grade);
    }

    /**
     * 删除成绩单
     */
    public void deleteGrade(String gradeId) {
        gradeMapper.deleteGrade(gradeId);
    }

    /**
     * 更新成绩单
     */
    public void updateGrade(Grade grade) {
        gradeMapper.updateGrade(grade);
    }

    /**
     * 更新成绩单中的学生姓名，修改个人信息时调用
     */
    public void updateStudentName(String user_id, String user_name) {
        List<Grade> grades = getGradesByStudentId(user_id);
        for (Grade grade : grades) {
            grade.setStudentName(user_name);
            updateGrade(grade);
        }
    }

    /**
     * 更新成绩单中的课程名，修改课程信息时调用
     */
    public void updateCourseName(String course_id, String course_name) {
        List<Grade> grades = getGradesByCourseId(course_id);
        for (Grade grade : grades) {
            grade.setCourseName(course_name);
            updateGrade(grade);
        }
    }

    /**
     * 更新成绩单状态为已确认
     */
    public void confirm(Grade grade) {
        grade.setGradeStatus(3);
        updateGrade(grade);
    }

    /**
     * 更新成绩单状态为反馈中
     */
    public void review(Grade grade) {
        grade.setGradeStatus(2);
        updateGrade(grade);
    }

    /**
     * 撤销成绩单反馈
     */
    public void delete(Grade grade) {
        grade.setGradeStatus(1);
        updateGrade(grade);
    }

    /**
     * 根据ID查找成绩单
     */
    public Grade getGradeById(String gradeId) {
        return gradeMapper.getGradeById(gradeId);
    }

    /**
     * 判断成绩单是否存在
     */
    public boolean isExist(String gradeId) {
        Grade grade = gradeMapper.getGradeById(gradeId);
        return grade != null;
    }

    /**
     * 根据课程查找成绩单
     */
    public List<Grade> getGradesByCourseId(String courseId) {
        return gradeMapper.getGradesByCourseId(courseId);
    }

    /**
     * 根据学生查找成绩单
     */
    public List<Grade> getGradesByStudentId(String studentId) {
        return gradeMapper.getGradesByStudentId(studentId);
    }

    /**
     * 获取所有成绩单
     */
    public List<Grade> getAllGrades() {
        return gradeMapper.getAllGrades();
    }
}
