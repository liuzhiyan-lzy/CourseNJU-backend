package com.example.coursenju.controller;

import com.example.coursenju.entity.Grade;
import com.example.coursenju.entity.GradeReview;
import com.example.coursenju.service.GradeReviewService;
import com.example.coursenju.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class GradeReviewController extends BaseController {
    @Autowired
    private GradeReviewService reviewService;
    @Autowired
    private GradeService gradeService;

    /**
     * @path 学生主页 - 提交反馈 - 提交反馈
     * @input {
     *     grade_id,
     *     objection_describe
     * }
     * @return GradeReview
     */
    @RequestMapping("/add")
    public CommonResult addGradeReview() {
        String gradeId = request.getParameter("grade_id");
        if (gradeId.equals("") || !gradeService.isExist(gradeId))
            return CommonResult.validateFailed("成绩单错误");
        Grade grade = gradeService.getGradeById(gradeId);
        String objectionDescribe = request.getParameter("objection_describe");
        if (objectionDescribe.equals(""))
            return CommonResult.validateFailed("反馈不能为空");
        GradeReview review = reviewService.addGradeReview(gradeId, objectionDescribe);
        gradeService.review(grade);
        return CommonResult.success(review, "添加反馈成功");
    }

    /**
     * @path 学生主页 - 反馈详情 - 撤销反馈
     * @input grade_id
     * @return Grade
     */
    @RequestMapping("/delete")
    public CommonResult deleteGradeReview() {
        String gradeId = request.getParameter("grade_id");
        if (gradeId.equals("") || !gradeService.isExist(gradeId))
            return CommonResult.validateFailed("成绩单错误");
        Grade grade = gradeService.getGradeById(gradeId);
        gradeService.delete(grade);
        reviewService.deleteGradeReview(gradeId);
        return CommonResult.success(grade, "撤销反馈成功");
    }

    /**
     * @path 学生主页 - 反馈详情
     * @input grade_id
     * @return GradeReview
     */
    @RequestMapping("/get")
    public CommonResult getGradeReview() {
        String gradeId = request.getParameter("grade_id");
        if (gradeId.equals("") || !gradeService.isExist(gradeId))
            return CommonResult.validateFailed("成绩单错误");
        GradeReview review = reviewService.getGradeReviewByGradeId(gradeId);
        if (review == null)
            return CommonResult.validateFailed("反馈不存在");
        return CommonResult.success(review, "查询反馈成功");
    }

    /**
     * @path 教师主页 - 课程详情 - 成绩单详情 - 处理反馈
     * @input {
     *     grade_id,
     *     review_opinion,
     *     is_update_grade
     * }
     * @return GradeReview
     */
    @RequestMapping("/teacher")
    public CommonResult teacherHandleReview() {
        String gradeId = request.getParameter("grade_id");
        if (gradeId.equals("") || !gradeService.isExist(gradeId))
            return CommonResult.validateFailed("成绩单错误");
        String reviewOpinion = request.getParameter("review_opinion");
        if (reviewOpinion.equals(""))
            return CommonResult.validateFailed("处理意见不能为空");
        boolean isUpdateGrade = request.getParameter("is_update_grade").equals("1");
        GradeReview review = reviewService.teacherHandle(gradeId, reviewOpinion, isUpdateGrade);
        return CommonResult.success(review, "处理反馈成功");
    }

    /**
     * @path 学生主页 - 反馈详情 - 继续反馈
     * @input {
     *     grade_id,
     *     objection_describe
     * }
     * @return GradeReview
     */
    @RequestMapping("/objection")
    public CommonResult studentObjection() {
        String gradeId = request.getParameter("grade_id");
        if (gradeId.equals("") || !gradeService.isExist(gradeId))
            return CommonResult.validateFailed("成绩单错误");
        String objectionDescribe = request.getParameter("objection_describe");
        if (objectionDescribe.equals(""))
            return CommonResult.validateFailed("反馈不能为空");
        GradeReview review = reviewService.studentHandle(gradeId, objectionDescribe, false);
        return CommonResult.success(review, "继续反馈成功");
    }

    /**
     * @path 学生主页 - 反馈详情 - 确认成绩
     * @input grade_id
     * @return GradeReview
     */
    @RequestMapping("/confirm")
    public CommonResult studentConfirm() {
        String gradeId = request.getParameter("grade_id");
        if (gradeId.equals("") || !gradeService.isExist(gradeId))
            return CommonResult.validateFailed("成绩单错误");
        Grade grade = gradeService.getGradeById(gradeId);
        gradeService.confirm(grade);
        GradeReview review = reviewService.studentHandle(gradeId, "", true);
        return CommonResult.success(review, "确认成功");
    }
}
