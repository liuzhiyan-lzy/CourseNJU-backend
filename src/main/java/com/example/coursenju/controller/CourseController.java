package com.example.coursenju.controller;

import com.example.coursenju.entity.Course;
import com.example.coursenju.entity.Grade;
import com.example.coursenju.entity.User;
import com.example.coursenju.service.CourseService;
import com.example.coursenju.service.GradeService;
import com.example.coursenju.service.UserService;
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
    @Autowired
    private UserService userService;
    @Autowired
    private GradeService gradeService;

    /**
     * @path 教务员主页 - 课程管理 - 添加课程
     * @input courseInfo {
     *     course_id,
     *     course_name,
     *     teacher_id,
     *     capacity
     * }
     * @return Course
     */
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

    /**
     * @path Debug
     * @input course_id
     * @return null
     */
    @RequestMapping("/delete")
    public CommonResult DeleteCourse() {
        String courseId = request.getParameter("course_id");
        if (courseId.equals("") || !courseService.isExist(courseId))
            return CommonResult.validateFailed("课程不存在");
        courseService.deleteCourse(courseId);
        return CommonResult.success(null, "删除课程成功");
    }

    /**
     * @path 教务员主页 - 课程管理 - 查看详情 - 更改课程信息
     * @input courseInfo {
     *     course_id,
     *     course_name,
     *     teacher_id,
     *     capacity
     * }
     * @return Course
     */
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

    /**
     * @path 教务员主页 - 课程管理 - 查看详情 - 添加学生 - 单个添加
     * @input {
     *     course_id,
     *     student_id
     * }
     * @return Grade
     */
    @RequestMapping("/add-student")
    public CommonResult AddStudent() {
        String courseId = request.getParameter("course_id");
        if (courseId.equals("") || !courseService.isExist(courseId))
            return CommonResult.validateFailed("课程不存在");
        Course course = courseService.getCourseById(courseId);
        String studentId = request.getParameter("student_id");
        if (studentId.equals("") || !userService.isExist(studentId))
            return CommonResult.validateFailed("学号不存在");
        User user = userService.getUserById(studentId);
        Grade grade = gradeService.addGrade(course, user);
        return CommonResult.success(grade, "添加学生成功");
    }

    /**
     * @path 教务员主页 - 课程管理 - 查看详情 - 添加学生 - 批量导入
     * @input {
     *     course_id,
     *     file        // 学号名单 Excel
     * }
     * @return List<Grade>
     */
    @RequestMapping("/add-students")
    public CommonResult AddStudents() {
        // TODO handle file

        // TODO add grades
        return CommonResult.success(null, "批量添加学生成功");
    }

    /**
     * @path 教务员主页 - 课程管理 - 查看详情
     * @input course_id
     * @return Course
     */
    @RequestMapping("/get")
    public CommonResult GetCourseById() {
        String courseId = request.getParameter("course_id");
        if (courseId.equals("") || !courseService.isExist(courseId))
            return CommonResult.validateFailed("课程不存在");
        Course course = courseService.getCourseById(courseId);
        return CommonResult.success(course, "查询课程成功");
    }

    /**
     * @path 教师主页
     * @input user_id
     * @return List<Course>
     */
    @RequestMapping("/list-teacher")
    public CommonResult GetCourseByTeacherId() {
        String teacherId = request.getParameter("user_id");
        List<Course> courses = courseService.getCourseByTeacherId(teacherId);
        if (courses == null)
            return CommonResult.failed("根据教师查询课程错误");
        return CommonResult.success(courses, "根据教师查询课程成功");
    }

    /**
     * @path 教务员主页 - 课程管理
     * @input null
     * @return List<Course>
     */
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
    }
}
