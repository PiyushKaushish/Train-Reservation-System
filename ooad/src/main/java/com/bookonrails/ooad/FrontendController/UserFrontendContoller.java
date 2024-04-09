package com.bookonrails.ooad.FrontendController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bookonrails.ooad.Model.User;
import com.bookonrails.ooad.Service.UserService;


@Controller
@RequestMapping(path="/users")
@CrossOrigin(origins = "*")
public class UserFrontendContoller {
    @Autowired
    UserService userService;
    
    @GetMapping(path="/login")
    public String login(Model m){
        m.addAttribute("message", "Please login");
        m.addAttribute("User", new User());
        return "login";
    }

    @PostMapping(path="/submitForm")
    public @ResponseBody User submitForm(@ModelAttribute("User") User user, Model model) {
        
        String username = user.getUsername();
        String password = user.getPassword();
        
        User u= userService.login(username, password);
        return u; // Return user for now
    }

    @GetMapping(path="/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_user"; // Return the signup form page
    }

    @PostMapping(path="/signup")
    public String signup(@ModelAttribute("user") User user, Model model) {
        userService.signUp(user);
        // You may add success message or redirect to login page here
        return "redirect:/users/login"; // Redirect to login page after successful signup
    }

    
}
