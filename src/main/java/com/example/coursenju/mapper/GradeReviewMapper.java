package com.example.coursenju.mapper;

import com.example.coursenju.entity.GradeReview;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GradeReviewMapper {
    void addGradeReview(GradeReview review);
    void deleteGradeReview(String gradeId);
    void updateGradeReview(GradeReview review);
    GradeReview getGradeReviewByGradeId(String gradeId);
    List<GradeReview> getAllGradeReviews();
}
