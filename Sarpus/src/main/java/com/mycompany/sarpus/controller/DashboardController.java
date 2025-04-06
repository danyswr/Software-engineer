package com.mycompany.sarpus.controller;

import com.mycompany.sarpus.model.Feedback;
import com.mycompany.sarpus.model.User;
import com.mycompany.sarpus.service.FeedbackService;
import com.mycompany.sarpus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class DashboardController {
    
    @Autowired
    private FeedbackService feedbackService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        List<Feedback> userFeedback = feedbackService.getFeedbackByUser(user);
        
        model.addAttribute("user", user);
        model.addAttribute("feedback", userFeedback);
        model.addAttribute("newFeedback", new Feedback());
        
        return "dashboard";
    }
    
    @PostMapping("/feedback/create")
    public String createFeedback(@ModelAttribute Feedback feedback, Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        feedback.setUser(user);
        feedbackService.createFeedback(feedback);
        
        return "redirect:/dashboard";
    }
}

