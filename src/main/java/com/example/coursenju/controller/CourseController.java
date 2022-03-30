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
    public CommonResult AddCourse() {
        Map<String, String[]> courseInfo = request.getParameterMap();
        String courseId = courseInfo.get("course_id")[0];
        if (courseId.equals("") || courseService.isExist(courseId))
            return CommonResult.validateFailed("课程已存在");
        Course course = new Course(courseId);
        setCourseInfo(courseInfo, course);
        courseService.addCourse(course);
        return CommonResult.success(course, "添加课程成功");
    }

    @RequestMapping("/delete")
    public CommonResult DeleteCourse() {
        String courseId = request.getParameter("course_id");
        if (courseId.equals("") || !courseService.isExist(courseId))
            return CommonResult.validateFailed("课程不存在");
        courseService.deleteCourse(courseId);
        return CommonResult.success(null, "删除课程成功");
    }

    @RequestMapping("/update")
    public CommonResult UpdateCourse() {
        Map<String, String[]> courseInfo = request.getParameterMap();
        String courseId = courseInfo.get("course_id")[0];
        if (courseId.equals("") || !courseService.isExist(courseId))
            return CommonResult.validateFailed("课程不存在");
        Course course = courseService.getCourseById(courseId);
        setCourseInfo(courseInfo, course);
        courseService.updateCourse(course);
        return CommonResult.success(course, "更新课程成功");
    }

    @RequestMapping("/update-status")
    public CommonResult UpdateCourseStatus() {
        String courseId = request.getParameter("course_id");
        int status = Integer.parseInt(request.getParameter("course_status"));
        if (courseId.equals("") || !courseService.isExist(courseId))
            return CommonResult.validateFailed("课程不存在");
        courseService.updateCourseStatus(courseId, status);
        return CommonResult.success(null, "更新课程状态成功");
    }

    @RequestMapping("/get")
    public CommonResult GetCourseById() {
        String courseId = request.getParameter("course_id");
        if (courseId.equals("") || !courseService.isExist(courseId))
            return CommonResult.validateFailed("课程不存在");
        Course course = courseService.getCourseById(courseId);
        return CommonResult.success(course, "查询课程成功");
    }

    @RequestMapping("/get-teacher")
    public CommonResult GetCourseByTeacherId() {
        String teacherId = request.getParameter("user_id");
        List<Course> courses = courseService.getCourseByTeacherId(teacherId);
        if (courses == null)
            return CommonResult.failed("根据教师查询课程错误");
        return CommonResult.success(courses, "根据教师查询课程成功");
    }

    @RequestMapping("/list")
    public CommonResult GetAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        if (courses == null)
            return CommonResult.failed("查询所有课程错误");
        return CommonResult.success(courses, "查询所有课程成功");
    }

    private void setCourseInfo(Map<String, String[]> courseInfo, Course course) {
        course.setCourseName(courseInfo.get("course_name")[0]);
        course.setTeacherId(courseInfo.get("teacher_id")[0]);
        course.setCapacity(Integer.parseInt(courseInfo.get("capacity")[0]));
        course.setCourseStatus(Integer.parseInt(courseInfo.get("course_status")[0]));
    }
}
