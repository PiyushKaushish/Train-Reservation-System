package com.bookonrails.ooad.FrontendController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookonrails.ooad.Model.Feedback;
import com.bookonrails.ooad.Model.Ticket;
import com.bookonrails.ooad.Model.User;
import com.bookonrails.ooad.Service.FeedbackService;
import com.bookonrails.ooad.Service.TicketService;
import com.bookonrails.ooad.Service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(path="/feedback")
@CrossOrigin(origins = "*")
public class FeedbackFrontendController {
    
    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;
    @GetMapping("/give-feedback")
    public String feedback(@RequestParam("ticketId") String ticketId ,Model model, HttpServletRequest request) {
        // Get the cookie with the name "username"
        String username = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())) {
                username = cookie.getValue();
                break;
            }
        }}
        if (username != null) {
        // Set the username as a model attribute
        model.addAttribute("username", username);
        }

        model.addAttribute("ticketId", ticketId);
        return "user/feedback";
    }

    @PostMapping("user/feedback")
    public String submitFeedback(@RequestParam("rating") int rating,
                                 @RequestParam("comments") String comments,
                                 @RequestParam("username") String username,
                                 @RequestParam("ticketId") String ticketId,
                                 Model model,
                                 HttpServletRequest request) {
        if (username != null) {
            // Set the username as a model attribute
            model.addAttribute("message", "user not logged in");
        }
        Ticket t= ticketService.getTicketById(Long.parseLong(ticketId));
        User u = userService.getUserByUsername(username);
        Feedback feedback = new Feedback();
        feedback.setRating(rating);
        feedback.setComment(comments);
        feedback.setUser(u);
        feedback.setTicket(t);

        // Save the feedback object to the database
        feedbackService.saveFeedback(feedback);

        // Redirect back to the feedback page
        return "user/thankyou";
    }
}


