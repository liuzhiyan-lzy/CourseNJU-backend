package com.example.coursenju.controller;

import com.example.coursenju.entity.Course;
import com.example.coursenju.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController extends BaseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping("/add")
    public ResponseData AddCourse() {
        Map<String, String[]> courseInfo = request.getParameterMap();
        String courseId = courseInfo.get("course_id")[0];
        if (courseId.equals("") || courseService.isExist(courseId))
            return ResponseDataUtil.buildError("404", "课程已存在");
        Course course = new Course(courseId);
        setCourseInfo(courseInfo, course);
        courseService.addCourse(course);
        return ResponseDataUtil.buildSuccess("200", "添加课程成功");
    }

    @RequestMapping("/delete")
    public ResponseData DeleteCourse() {
        String courseId = request.getParameter("course_id");
        if (courseId.equals("") || !courseService.isExist(courseId))
            return ResponseDataUtil.buildError("404", "课程不存在");
        courseService.deleteCourse(courseId);
        return ResponseDataUtil.buildSuccess("200", "删除课程成功");
    }

    @RequestMapping("/update")
    public ResponseData UpdateCourse() {
        Map<String, String[]> courseInfo = request.getParameterMap();
        String courseId = courseInfo.get("course_id")[0];
        if (courseId.equals("") || !courseService.isExist(courseId))
            return ResponseDataUtil.buildError("404", "课程不存在");
        Course course = courseService.getCourseById(courseId);
        setCourseInfo(courseInfo, course);
        courseService.updateCourse(course);
        return ResponseDataUtil.buildSuccess("200", "更新课程成功");
    }

    @RequestMapping("/update-status")
    public ResponseData UpdateCourseStatus() {
        String courseId = request.getParameter("course_id");
        int status = Integer.parseInt(request.getParameter("course_status"));
        if (courseId.equals("") || !courseService.isExist(courseId))
            return ResponseDataUtil.buildError("404", "课程不存在");
        courseService.updateCourseStatus(courseId, status);
        return ResponseDataUtil.buildSuccess("200", "更新课程状态成功");
    }

    @RequestMapping("/get")
    public ResponseData GetCourseById() {
        String courseId = request.getParameter("course_id");
        if (courseId.equals("") || !courseService.isExist(courseId))
            return ResponseDataUtil.buildError("404", "课程不存在");
        Course course = courseService.getCourseById(courseId);
        return ResponseDataUtil.buildSuccess("200", "查询课程成功", course);
    }

    @RequestMapping("/get-teacher")
    public ResponseData GetCourseByTeacherId() {
        String teacherId = request.getParameter("user_id");
        List<Course> courses = courseService.getCourseByTeacherId(teacherId);
        if (courses == null)
            return ResponseDataUtil.buildError("400", "内部错误");
        return ResponseDataUtil.buildSuccess("200", "查询课程成功", courses);
    }

    @RequestMapping("/list")
    public ResponseData GetAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        if (courses == null)
            return ResponseDataUtil.buildError("400", "内部错误");
        return ResponseDataUtil.buildSuccess("200", "查询课程成功", courses);
    }

    private void setCourseInfo(Map<String, String[]> courseInfo, Course course) {
        course.setCourseName(courseInfo.get("course_name")[0]);
        course.setTeacherId(courseInfo.get("teacher_id")[0]);
        course.setCapacity(Integer.parseInt(courseInfo.get("capacity")[0]));
        course.setCourseStatus(Integer.parseInt(courseInfo.get("course_status")[0]));
    }
}
