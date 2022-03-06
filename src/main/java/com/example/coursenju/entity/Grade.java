package com.example.coursenju.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "grade")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    @Column(name = "grade_id")
    private String gradeId;

    @Column(name = "course_id")
    private String courseId;

    @Column(name = "student_id")
    private String studentId;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "teacher_id")
    private String teacherId;

    @Column(name = "total_score")
    private int totalScore;

    @Column(name = "usual_score")
    private int usualScore;

    @Column(name = "mid_score")
    private int midScore;

    @Column(name = "final_score")
    private int finalScore;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getUsualScore() {
        return usualScore;
    }

    public void setUsualScore(int usualScore) {
        this.usualScore = usualScore;
    }

    public int getMidScore() {
        return midScore;
    }

    public void setMidScore(int midScore) {
        this.midScore = midScore;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }
}
