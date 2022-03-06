package com.example.coursenju.controller;

import com.example.coursenju.entity.Grade;
import com.example.coursenju.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.example.coursenju.util.Math.getRandomString;

@Controller
@RequestMapping("/grade/")
public class GradeController {

    @Autowired
    GradeService gradeService;

    @CrossOrigin
    @GetMapping("course")
    @ResponseBody
    public ResponseData GetGradesByCourse(@RequestBody Map<String, String> courseInfo) {
        if (!courseInfo.containsKey("course_id") || courseInfo.get("course_id").equals(""))
            return ResponseDataUtil.buildError("Course ID error");
        String courseId = courseInfo.get("course_id");
        List<Grade> grades = gradeService.getGradesByCourseId(courseId);
        return ResponseDataUtil.buildSuccess(grades);
    }

    @CrossOrigin
    @GetMapping("student")
    @ResponseBody
    public ResponseData GetGradesByStudent(@RequestBody Map<String, String> studentInfo) {
        if (!studentInfo.containsKey("student_id") || studentInfo.get("student_id").equals(""))
            return ResponseDataUtil.buildError("Student ID error");
        String studentId = studentInfo.get("student_id");
        List<Grade> grades = gradeService.getGradesByStudentId(studentId);
        return ResponseDataUtil.buildSuccess(grades);
    }

    @CrossOrigin
    @PostMapping("add")
    @ResponseBody
    public ResponseData AddGrade(@RequestBody Map<String, String> gradeInfo) {
        String gradeId = getRandomString(10);
        boolean isExist = gradeService.isExist(gradeId);
        while (isExist) {
            gradeId = getRandomString(10);
            isExist = gradeService.isExist(gradeId);
        }

        Grade grade = new Grade();
        grade.setGradeId(gradeId);
        setGradeInfo(gradeInfo, grade);
        gradeService.addGrade(grade);
        return ResponseDataUtil.buildSuccess(grade);
    }

    @CrossOrigin
    @PostMapping("update")
    @ResponseBody
    public ResponseData UpdateGrade(@RequestBody Map<String, String> gradeInfo) {
        String gradeId = deleteGrade(gradeInfo);
        if (gradeId.equals(""))
            return ResponseDataUtil.buildError("Grade Not Exists");
        Grade newGrade = new Grade();
        newGrade.setGradeId(gradeId);
        setGradeInfo(gradeInfo, newGrade);
        gradeService.addGrade(newGrade);
        return ResponseDataUtil.buildSuccess(newGrade);
    }

    @CrossOrigin
    @PutMapping("delete")
    @ResponseBody
    public ResponseData DeleteGrade(@RequestBody Map<String, String> gradeInfo) {
        String gradeId = deleteGrade(gradeInfo);
        if (gradeId.equals(""))
            return ResponseDataUtil.buildError("Grade Not Exists");
        return ResponseDataUtil.buildSuccess();
    }

    private String deleteGrade(Map<String, String> gradeInfo) {
        String studentId = gradeInfo.get("student_id");
        String courseId = gradeInfo.get("course_id");
        Grade grade = gradeService.getGradeByStudentIdAndCourseId(studentId, courseId);
        if (grade == null)
            return "";
        String gradeId = grade.getGradeId();
        gradeService.deleteGrade(grade);
        return gradeId;
    }

    private void setGradeInfo(Map<String, String> gradeInfo, Grade grade) {
        grade.setCourseId(gradeInfo.get("course_id"));
        grade.setStudentId(gradeInfo.get("student_id"));
        grade.setTeacherId(gradeInfo.get("teacher_id"));
        grade.setStudentName(gradeInfo.get("student_name"));
        grade.setTotalScore(Integer.parseInt(gradeInfo.get("total_score")));
        grade.setUsualScore(Integer.parseInt(gradeInfo.get("usual_score")));
        grade.setMidScore(Integer.parseInt(gradeInfo.get("mid_score")));
        grade.setFinalScore(Integer.parseInt(gradeInfo.get("final_score")));
    }
}
