package com.example.coursenju.controller;

import com.example.coursenju.dao.CourseDao;
import com.example.coursenju.entity.Course;
import com.example.coursenju.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/course/")
public class CourseController {

    @Autowired
    CourseService courseService;

    @CrossOrigin
    @PostMapping("add")
    @ResponseBody
    public ResponseData AddCourse(@RequestBody Map<String, String> courseInfo) {
        boolean isExist = courseService.isExist(courseInfo.get("course_id"));
        if (isExist)
            return ResponseDataUtil.buildError("CourseID exists");
        Course course = new Course();
        setCourseInfo(courseInfo, course);
        courseService.addCourse(course);
        return ResponseDataUtil.buildSuccess(course);
    }

    @CrossOrigin
    @PostMapping("update")
    @ResponseBody
    public ResponseData UpdateCourse(@RequestBody Map<String, String> courseInfo) {
        if (!courseInfo.containsKey("course_id") || courseInfo.get("course_id").equals("") || !courseService.isExist(courseInfo.get("course_id")))
            return ResponseDataUtil.buildError("ID not exist");
        Course old = courseService.getCourseByCourseId(courseInfo.get("course_id"));
        Course course = new Course();
        setCourseInfo(courseInfo, course);
        courseService.updateCourse(old, course);
        return ResponseDataUtil.buildSuccess(course);
    }

    @CrossOrigin
    @PostMapping("delete")
    @ResponseBody
    public ResponseData DeleteCourse(@RequestBody Map<String, String> courseInfo) {
        if (!courseInfo.containsKey("user_id") || courseInfo.get("user_id").equals("") || !courseService.isExist(courseInfo.get("user_id")))
            return ResponseDataUtil.buildError("ID not exist");
        Course course = courseService.getCourseByCourseId(courseInfo.get("user_id"));
        courseService.deleteCourse(course);
        return ResponseDataUtil.buildSuccess();
    }

    @CrossOrigin
    @GetMapping("get")
    @ResponseBody
    public ResponseData GetCourseByID(@RequestBody Map<String, String> courseInfo) {
        if (!courseInfo.containsKey("user_id") || courseInfo.get("user_id").equals("") || !courseService.isExist(courseInfo.get("user_id")))
            return ResponseDataUtil.buildError("ID not exist");
        return ResponseDataUtil.buildError(courseService.getCourseByCourseId(courseInfo.get("user_id")));
    }


    private void setCourseInfo(Map<String, String> courseInfo, Course course) {
        course.setCourseId(courseInfo.get("course_id"));
        course.setName(courseInfo.get("name"));
        course.setCapacity(Integer.parseInt(courseInfo.get("capacity")));
        course.setTeacherNum(courseInfo.get("teacher_num"));
        course.setStatus(Integer.parseInt(courseInfo.get("status")));
    }
}
