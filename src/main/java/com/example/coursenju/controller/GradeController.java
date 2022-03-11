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
    public ResponseData AddGrade() {
        Map<String, String[]> gradeInfo = request.getParameterMap();
        addGrade(gradeInfo);
        return ResponseDataUtil.buildSuccess("200", "添加成绩单成功");
    }

    @RequestMapping("/import")
    public ResponseData ImportGrades() {
        // TODO
        return ResponseDataUtil.buildSuccess("200", "导入成绩单成功");
    }

    @RequestMapping("/delete")
    public ResponseData DeleteGrade() {
        String gradeId = request.getParameter("grade_id");
        if (gradeId.equals("") || !gradeService.isExist(gradeId))
            return ResponseDataUtil.buildError("404", "成绩单不存在");
        gradeService.deleteGrade(gradeId);
        return ResponseDataUtil.buildSuccess("200", "删除成绩单成功");
    }

    @RequestMapping("/update")
    public ResponseData UpdateGrade() {
        Map<String, String[]> gradeInfo = request.getParameterMap();
        String gradeId = gradeInfo.get("grade_id")[0];
        if (gradeId.equals("") || !gradeService.isExist(gradeId))
            return ResponseDataUtil.buildError("404", "成绩单不存在");
        Grade grade = gradeService.getGradeById(gradeId);
        setGradeInfo(gradeInfo, grade);
        gradeService.updateGrade(grade);
        return ResponseDataUtil.buildSuccess("200", "更新成绩单成功");
    }

    @RequestMapping("/get")
    public ResponseData GetGradeById() {
        String gradeId = request.getParameter("grade_id");
        if (gradeId.equals("") || !gradeService.isExist(gradeId))
            return ResponseDataUtil.buildError("200", "成绩单不存在");
        Grade grade = gradeService.getGradeById(gradeId);
        return ResponseDataUtil.buildSuccess("200", "查找成绩单成功", grade);
    }

    @RequestMapping("get-course")
    public ResponseData GetGradesByCourseId() {
        String courseId = request.getParameter("course_id");
        if (courseId.equals(""))
            return ResponseDataUtil.buildError("404", "未成功获取课程Id");
        List<Grade> grades = gradeService.getGradesByCourseId(courseId);
        if (grades == null)
            return ResponseDataUtil.buildError("400", "内部错误");
        return ResponseDataUtil.buildSuccess("200", "查找成绩单成功", grades);
    }

    @RequestMapping("get-student")
    public ResponseData GetGradesByStudentId() {
        String studentId = request.getParameter("student_id");
        if (studentId.equals(""))
            return ResponseDataUtil.buildError("404", "未成功获取学生Id");
        List<Grade> grades = gradeService.getGradesByStudentId(studentId);
        if (grades == null)
            return ResponseDataUtil.buildError("400", "内部错误");
        return ResponseDataUtil.buildSuccess("200", "查找成绩单成功", grades);
    }

    @RequestMapping("list")
    public ResponseData GetAllGrades() {
        List<Grade> grades = gradeService.getAllGrades();
        if (grades == null)
            return ResponseDataUtil.buildError("400", "内部错误");
        return ResponseDataUtil.buildSuccess("200", "查找成绩单成功", grades);
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
