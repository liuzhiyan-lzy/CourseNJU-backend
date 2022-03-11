package com.example.coursenju.entity;

public class Grade {

    private int id;

    private String gradeId;

    private String courseId;

    private String studentId;

    private String studentName;

    private int totalScore;

    private int usualScore;

    private int midScore;

    private int finalScore;

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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public Grade() {

    }

    public Grade(String gradeId) {
        this.gradeId = gradeId;
    }

    public String toInfoString() {
        return "id: " +
                id +
                "\ngradeId: " +
                gradeId +
                "\ncourseId: " +
                courseId +
                "\nstudentId: " +
                studentId +
                "\nstudentName: " +
                studentName +
                "\ntotalScore: " +
                totalScore +
                "\nusualScore: " +
                usualScore +
                "\nmidScore: " +
                midScore +
                "\nfinalScore: " +
                finalScore;
    }
}
