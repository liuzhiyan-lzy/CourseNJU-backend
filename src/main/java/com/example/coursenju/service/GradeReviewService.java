package com.example.coursenju.service;

import com.example.coursenju.entity.Grade;
import com.example.coursenju.entity.GradeReview;
import com.example.coursenju.mapper.GradeReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeReviewService {
    @Autowired
    private GradeReviewMapper reviewMapper;

    public GradeReview addGradeReview(String gradeId, String objectionDescribe) {
        GradeReview review = new GradeReview(gradeId);
        review.setReviewStatus(1);
        review.setObjectionDescribe(objectionDescribe);
        reviewMapper.addGradeReview(review);
        return review;
    }

    public void deleteGradeReview(String gradeId) {
        reviewMapper.deleteGradeReview(gradeId);
    }

    public void updateGradeReview(GradeReview review) {
        reviewMapper.updateGradeReview(review);
    }

    public GradeReview teacherHandle(String gradeId, String reviewOpinion, boolean isUpdateGrade) {
        GradeReview review = getGradeReviewByGradeId(gradeId);
        review.setReviewOpinion(reviewOpinion);
        if (isUpdateGrade)
            review.setReviewStatus(3);
        else
            review.setReviewStatus(2);
        updateGradeReview(review);
        return review;
    }

    public GradeReview studentHandle(String gradeId, String objectionDescribe, boolean isConfirm) {
        GradeReview review = getGradeReviewByGradeId(gradeId);
        if (isConfirm) {
            review.setReviewStatus(0);
            updateGradeReview(review);
            return review;
        }
        review.setObjectionDescribe(objectionDescribe);
        review.setReviewStatus(1);
        updateGradeReview(review);
        return review;
    }

    public GradeReview getGradeReviewByGradeId(String gradeId) {
        return reviewMapper.getGradeReviewByGradeId(gradeId);
    }

    public boolean isExist(String gradeId) {
        GradeReview review = reviewMapper.getGradeReviewByGradeId(gradeId);
        return review != null;
    }

    public List<GradeReview> getAllGradeReviews() {
        return reviewMapper.getAllGradeReviews();
    }
}
