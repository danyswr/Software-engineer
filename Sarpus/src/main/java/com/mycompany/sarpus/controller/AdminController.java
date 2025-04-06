package com.mycompany.sarpus.controller;

import com.mycompany.sarpus.model.Feedback;
import com.mycompany.sarpus.model.User;
import com.mycompany.sarpus.service.FeedbackService;
import com.mycompany.sarpus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        List<User> users = userService.getAllUsers();
        List<Feedback> allFeedback = feedbackService.getAllFeedback();
        
        model.addAttribute("users", users);
        model.addAttribute("feedback", allFeedback);
        model.addAttribute("pendingCount", feedbackService.getFeedbackByStatus("PENDING").size());
        model.addAttribute("inProgressCount", feedbackService.getFeedbackByStatus("IN_PROGRESS").size());
        model.addAttribute("resolvedCount", feedbackService.getFeedbackByStatus("RESOLVED").size());
        
        return "admin/dashboard";
    }

    @GetMapping("/users")
    public String userManagement(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/users";
    }

    @GetMapping("/feedback")
    public String feedbackManagement(Model model) {
        List<Feedback> allFeedback = feedbackService.getAllFeedback();
        model.addAttribute("feedback", allFeedback);
        return "admin/feedback";
    }

    @PostMapping("/feedback/{id}/status")
    public String updateFeedbackStatus(@PathVariable Long id, @RequestParam String status) {
        feedbackService.updateFeedbackStatus(id, status);
        return "redirect:/admin/feedback";
    }

    @GetMapping("/login")
    public String adminLogin() {
        return "admin/login";
    }
}

