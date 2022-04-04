package com.example.coursenju.entity;

public class GradeReview {

    private int id;

    private String gradeId;

    /**
     *  1: 学生提出异议, 待教师处理
     *  2: 教师已处理, 意见为驳回
     *  3: 教师已处理, 并已修改成绩
     *  0: 已确认成绩
     */
    private int reviewStatus;

    private String objectionDescribe;

    private String reviewOpinion;

    public GradeReview() {}

    public GradeReview(String gradeId) {
        this.gradeId = gradeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public int getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(int reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getObjectionDescribe() {
        return objectionDescribe;
    }

    public void setObjectionDescribe(String objectionDescribe) {
        this.objectionDescribe = objectionDescribe;
    }

    public String getReviewOpinion() {
        return reviewOpinion;
    }

    public void setReviewOpinion(String reviewOpinion) {
        this.reviewOpinion = reviewOpinion;
    }
}
