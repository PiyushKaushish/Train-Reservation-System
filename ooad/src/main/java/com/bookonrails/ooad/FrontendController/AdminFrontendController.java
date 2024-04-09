package com.bookonrails.ooad.FrontendController;

import com.bookonrails.ooad.Model.Admin;
import com.bookonrails.ooad.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path="/admin")
@CrossOrigin(origins = "*")
public class AdminFrontendController {

    @Autowired
    private AdminService adminAuthenticationService;

    @GetMapping("/login")
    public String showLoginForm(Model m) {
        m.addAttribute("Admin",new Admin());
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") Admin admin, Model model, HttpServletResponse response) {
        Admin a=adminAuthenticationService.authenticate(admin);
        if (a!=null) {
            // If login is successful, set a cookie to maintain the login state
            Cookie cookie = new Cookie("username", a.getUsername());
            cookie.setMaxAge(7 * 24 * 60 * 60); // Cookie expires in 7 days
            cookie.setPath("/"); // Set cookie path to root
            response.addCookie(cookie);

            Cookie cookie1 = new Cookie("admin", "true");
            cookie1.setMaxAge(7 * 24 * 60 * 60); // Cookie1 expires in 7 days
            cookie1.setPath("/"); // Set cookie1 path to root
            response.addCookie(cookie1);
            
            return "redirect:/admin/dashboard";
        } else {
            String errormessage="Invalid username or password. Please try again.";
            return "redirect:/admin/login?error="+errormessage;
        }
    }

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "admin/dashboard";
    }

    @GetMapping(path="/logout")
    public String logout(Model model, HttpServletResponse response) {
        // Remove the username cookie to log the user out
        Cookie cookie = new Cookie("username", "");
        cookie.setMaxAge(0); // Expire immediately
        cookie.setPath("/"); // Set cookie path to root
        response.addCookie(cookie);
        Cookie cookie1 = new Cookie("admin", "");
        cookie1.setMaxAge(0);
        cookie1.setPath("/");
        response.addCookie(cookie1);
        
        // Redirect the user to the login page
        return "redirect:/admin/login";
    }
    

    // Define other methods for admin operations (view users, update ticket fare, etc.)
}
