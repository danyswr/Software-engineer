package com.mycompany.sarpus.repository;

import com.mycompany.sarpus.model.Feedback;
import com.mycompany.sarpus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByUser(User user);
    List<Feedback> findByCategory(String category);
    List<Feedback> findByStatus(String status);
}

