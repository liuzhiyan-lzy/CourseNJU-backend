package com.example.coursenju.entity;

public class Course {

    private int id;

    private String courseId;

    private String courseName;

    private String teacherId;

    private int capacity;

    /**
     * @value 0: 课程未开始
     * @value 1: 课程进行中
     * @value 2: 课程已结束, 未登分
     * @value 3: 课程已登分, 确认中
     * @value -1: 课程已结束
     */
    private int courseStatus;

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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(int courseStatus) {
        this.courseStatus = courseStatus;
    }

    public Course() {}

    public Course(String courseId) {
        this.courseId = courseId;
    }

    public String toInfoString() {
        return "id: " +
                id +
                "\ncourseId: " +
                courseId +
                "\ncourseName: " +
                courseName +
                "\nteacherId: " +
                teacherId +
                "\ncapacity: " +
                capacity +
                "\ncourseStatus: " +
                courseStatus;
    }
}
