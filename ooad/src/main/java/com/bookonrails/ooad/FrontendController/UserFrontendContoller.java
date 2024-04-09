package com.bookonrails.ooad.FrontendController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bookonrails.ooad.Model.User;
import com.bookonrails.ooad.Service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(path="/users")
@CrossOrigin(origins = "*")
public class UserFrontendContoller {
    @Autowired
    UserService userService;
    
    @GetMapping(path="/login")
    public String login(Model m){
        m.addAttribute("message", "Login Here...");
        m.addAttribute("User", new User());
        return "login";
    }

    @PostMapping(path="/loginForm")
    public String loginForm(@ModelAttribute("User") User user, Model model,  HttpServletResponse response) {
        
        String username = user.getUsername();
        String password = user.getPassword();
        
        User u= userService.login(username, password);
        if (u != null) {
            // If login is successful, set a cookie to maintain the login state
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(7 * 24 * 60 * 60); // Cookie expires in 7 days
            cookie.setPath("/"); // Set cookie path to root
            response.addCookie(cookie);
            
            // Redirect the user to home page or any other page
            return "redirect:/"; // 
        } else {
            // If login fails, return an error message or redirect back to the login page
            String errorMessage = "Invalid username or password. Please try again.";
            return "redirect:/users/login?error="+errorMessage;
        }
    }

    @GetMapping(path="/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup"; // Return the signup form page
    }

    @PostMapping(path="/signup")
    public String signup(@ModelAttribute("user") User user, Model model) {
        userService.signUp(user);
        // You may add success message or redirect to login page here
        return "redirect:/"; // Redirect to login page after successful signup
    }

    @GetMapping(path="/logout")
    public String logout(Model model, HttpServletResponse response) {
        // Remove the username cookie to log the user out
        Cookie cookie = new Cookie("username", "");
        cookie.setMaxAge(0); // Expire immediately
        cookie.setPath("/"); // Set cookie path to root
        response.addCookie(cookie);
        
        // Redirect the user to the login page
        return "redirect:/users/login";
    }
    
    
}
