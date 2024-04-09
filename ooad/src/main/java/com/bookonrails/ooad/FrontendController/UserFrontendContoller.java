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
}
