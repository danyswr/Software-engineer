package com.mycompany.sarpus.service;

import com.mycompany.sarpus.model.Feedback;
import com.mycompany.sarpus.model.User;
import com.mycompany.sarpus.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeedbackService {
    
    @Autowired
    private FeedbackRepository feedbackRepository;
    
    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }
    
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }
    
    public List<Feedback> getFeedbackByUser(User user) {
        return feedbackRepository.findByUser(user);
    }
    
    public List<Feedback> getFeedbackByCategory(String category) {
        return feedbackRepository.findByCategory(category);
    }
    
    public List<Feedback> getFeedbackByStatus(String status) {
        return feedbackRepository.findByStatus(status);
    }
    
    public Feedback updateFeedbackStatus(Long id, String status) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
        feedback.setStatus(status);
        return feedbackRepository.save(feedback);
    }
}

