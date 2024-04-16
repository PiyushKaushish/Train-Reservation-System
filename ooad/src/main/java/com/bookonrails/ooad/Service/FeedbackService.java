package com.bookonrails.ooad.Service;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookonrails.ooad.Interface.FeedbackManagement;
import com.bookonrails.ooad.Model.Feedback;
import com.bookonrails.ooad.Repository.FeedbackRepository; 

@Service
public class FeedbackService implements FeedbackManagement{
    
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback getFeedbackById(Long id) {
    Optional<Feedback> feedbackOptional = feedbackRepository.findById(id);
    return feedbackOptional.orElse(new Feedback());
}

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }


}
