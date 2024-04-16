package com.bookonrails.ooad.Interface;

import java.util.List;

import com.bookonrails.ooad.Model.Feedback;

public interface FeedbackManagement {
    Feedback saveFeedback(Feedback feedback);
    Feedback getFeedbackById(Long id);
    List<Feedback> getAllFeedback();
    void deleteFeedback(Long id);

}
