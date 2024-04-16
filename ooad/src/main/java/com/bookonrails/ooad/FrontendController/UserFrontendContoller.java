package com.bookonrails.ooad.FrontendController;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookonrails.ooad.Model.Ticket;
import com.bookonrails.ooad.Model.User;
import com.bookonrails.ooad.Service.TicketService;
import com.bookonrails.ooad.Service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(path="/users")
@CrossOrigin(origins = "*")
public class UserFrontendContoller {
    @Autowired
    UserService userService;
    String errorMessage;
    @Autowired
    TicketService ticketService;
    
    @GetMapping(path="/login")
    public String login(Model m){
        m.addAttribute("message", "Login Here...");
        m.addAttribute("User", new User());
        return "user/login";
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
            return "redirect:/users/dashboard"; // 
        } else {
            // If login fails, return an error message or redirect back to the login page
            errorMessage = "Invalid username or password. Please try again.";
            return "redirect:/users/login?error="+errorMessage;
        }
    }

    @GetMapping(path="/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("message", "Register Here...");
        model.addAttribute("User", new User());
        return "user/signup"; // Return the signup form page
    }

    @SuppressWarnings("unused")
    @PostMapping(path="/signup")
    public String signup(@ModelAttribute("user") User user, Model model, HttpServletResponse response) {
        User u= userService.signUp(user);
        String username= u.getUsername();
        if (u != null) {
            // If login is successful, set a cookie to maintain the login state
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(7 * 24 * 60 * 60); // Cookie expires in 7 days
            cookie.setPath("/"); // Set cookie path to root
            response.addCookie(cookie);
            
            // Redirect the user to home page or any other page
            return "redirect:/users/dashboard"; // 
        } 
        
        // If login fails, return an error message or redirect back to the login page
        errorMessage = "Please fill all details properly";
        return "redirect:/users/signup?error="+errorMessage;   
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

    @GetMapping(path="/dashboard")
    public String dashboard(Model model,HttpServletRequest request) {
        // Return the user dashboard page
        // Request username Cookie
        Cookie[] cookies = request.getCookies();
        String username = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }
            }
        }
        User u= userService.getUserByUsername(username);
        if(u == null){
            return "redirect:/users/login";
        }
        model.addAttribute("user", u);
        model.addAttribute("message",u.getFirstName()+"'s Dashboard");
        return "user/dashboard";
    }

    @PostMapping(path="/update")
    public String update(@ModelAttribute("user") User user, Model model,HttpServletRequest request) {
        // Update the user details
        // Request username Cookie
        Cookie[] cookies = request.getCookies();
        String username = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }
            }
        }
        User u= userService.getUserByUsername(username);
        if(u == null){
            return "redirect:/users/login";
        }
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setEmail(user.getEmail());
        u.setPhoneNumber(user.getPhoneNumber());
        u.setAddress(user.getAddress());
        userService.updateUser(u);
        return "redirect:/users/dashboard";
    }  
    
    // change-password
    @GetMapping(path="/change-password")
    public String changePassword(Model model,HttpServletRequest request) {
        model.addAttribute("message", "Change Password Here...");
        Cookie[] cookies = request.getCookies();
        String username = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }
            }
        }
        User u= userService.getUserByUsername(username);
        if(u == null){
            return "redirect:/users/login";
        }

        return "user/change-password";
    }

    @PostMapping(path="/change-password")
    public String changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword,@RequestParam("confirmPassword") String confirmPassword ,Model model,HttpServletRequest request) {
        
        Cookie[] cookies = request.getCookies();
        String username = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }
            }
        }
        User u= userService.getUserByUsername(username);
        if(u == null){
            return "redirect:/users/login";
        }
        if(u.getPassword().equals(oldPassword)){
            if(newPassword.equals(confirmPassword)){
            userService.changePassword(u, newPassword);
            return "redirect:/users/dashboard";
            }else{
                return "redirect:/users/change-password?error=Password%20Mismatch";
            }
        }
        return "redirect:/users/change-password?error=Invalid%20Password";
    }

    @GetMapping(path="/my-trips")
    public String myTrips(Model model,HttpServletRequest request) {
        // Return the user dashboard page
        // Request username Cookie
        Cookie[] cookies = request.getCookies();
        String username = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }
            }
        }
        User u= userService.getUserByUsername(username);
        if(u == null){
            return "redirect:/users/login";
        }
        List<Ticket> tickets=ticketService.getTicketByUser(u);
        Comparator<Ticket> ticketComparator = new Comparator<Ticket>() {
            @Override
            public int compare(Ticket t1, Ticket t2) {
                // Compare ticketId in descending order
                return t2.getId().compareTo(t1.getId());
            }
        };
        // sort ticket by ticketId in decending order
        Collections.sort(tickets, ticketComparator);
        model.addAttribute("user", u);
        model.addAttribute("message",u.getFirstName()+"'s Trips");
        model.addAttribute("tickets",tickets );
        return "user/my-trips";
    }
    
}
