package com.example.coursenju.controller;

import com.example.coursenju.entity.Grade;
import com.example.coursenju.service.GradeService;
import com.example.coursenju.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grade")
public class GradeController extends BaseController {
    @Autowired
    private GradeService gradeService;

    @RequestMapping("/add")
    public CommonResult AddGrade() {
        Map<String, String[]> gradeInfo = request.getParameterMap();
        addGrade(gradeInfo);
        return CommonResult.success(null, "添加成绩单成功");
    }

    @RequestMapping("/import")
    public CommonResult ImportGrades() {
        // TODO
        return CommonResult.success(null, "批量添加成绩单成功");
    }

    @RequestMapping("/delete")
    public CommonResult DeleteGrade() {
        String gradeId = request.getParameter("grade_id");
        if (gradeId.equals("") || !gradeService.isExist(gradeId))
            return CommonResult.validateFailed();
        gradeService.deleteGrade(gradeId);
        return CommonResult.success(null, "删除成绩单成功");
    }

    @RequestMapping("/update")
    public CommonResult UpdateGrade() {
        Map<String, String[]> gradeInfo = request.getParameterMap();
        String gradeId = gradeInfo.get("grade_id")[0];
        if (gradeId.equals("") || !gradeService.isExist(gradeId))
            return CommonResult.validateFailed("成绩单不存在");
        Grade grade = gradeService.getGradeById(gradeId);
        setGradeInfo(gradeInfo, grade);
        gradeService.updateGrade(grade);
        return CommonResult.success(grade, "更新成绩单成功");
    }

    @RequestMapping("/get")
    public CommonResult GetGradeById() {
        String gradeId = request.getParameter("grade_id");
        if (gradeId.equals("") || !gradeService.isExist(gradeId))
            return CommonResult.validateFailed("成绩单不存在");
        Grade grade = gradeService.getGradeById(gradeId);
        return CommonResult.success(grade, "查询成绩单成功");
    }

    @RequestMapping("get-course")
    public CommonResult GetGradesByCourseId() {
        String courseId = request.getParameter("course_id");
        if (courseId.equals(""))
            return CommonResult.validateFailed("课程不存在");
        List<Grade> grades = gradeService.getGradesByCourseId(courseId);
        if (grades == null)
            return CommonResult.failed("根据课程查询成绩单错误");
        return CommonResult.success(grades, "根据课程查询成绩单成功");
    }

    @RequestMapping("get-student")
    public CommonResult GetGradesByStudentId() {
        String studentId = request.getParameter("student_id");
        if (studentId.equals(""))
            return CommonResult.validateFailed("学号错误");
        List<Grade> grades = gradeService.getGradesByStudentId(studentId);
        if (grades == null)
            return CommonResult.failed("根据学号查询成绩单错误");
        return CommonResult.success(grades, "根据学号查询成绩单成功");
    }

    @RequestMapping("list")
    public CommonResult GetAllGrades() {
        List<Grade> grades = gradeService.getAllGrades();
        if (grades == null)
            return CommonResult.failed("查询所有成绩单错误");
        return CommonResult.success(grades, "查询所有成绩单成功");
    }

    private void addGrade(Map<String, String[]> gradeInfo) {
        String gradeId = Util.getRandomString(10);
        while (gradeService.isExist(gradeId))
            gradeId = Util.getRandomString(10);
        Grade grade = new Grade(gradeId);
        setGradeInfo(gradeInfo, grade);
        gradeService.addGrade(grade);
    }

    private void setGradeInfo(Map<String, String[]> gradeInfo, Grade grade) {
        grade.setCourseId(gradeInfo.get("course_id")[0]);
        grade.setStudentId(gradeInfo.get("student_id")[0]);
        grade.setStudentName(gradeInfo.get("student_name")[0]);
        grade.setTotalScore(Integer.parseInt(gradeInfo.get("total_score")[0]));
        grade.setUsualScore(Integer.parseInt(gradeInfo.get("usual_score")[0]));
        grade.setMidScore(Integer.parseInt(gradeInfo.get("mid_score")[0]));
        grade.setFinalScore(Integer.parseInt(gradeInfo.get("final_score")[0]));
    }
}
