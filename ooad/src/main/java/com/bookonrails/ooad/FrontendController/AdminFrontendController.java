// AdminController.java

package com.bookonrails.ooad.FrontendController;

import com.bookonrails.ooad.Service.AdminAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminFrontendController {

    @Autowired
    private AdminAuthenticationService adminAuthenticationService;

    @GetMapping("/admin/login")
    public String showLoginForm() {
        return "admin_login";
    }

    @PostMapping("/admin/login")
    public String login(String username, String password) {
        if (adminAuthenticationService.authenticate(username, password)) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/admin/login?error=true";
        }
    }

    @GetMapping("/admin/dashboard")
    public String showDashboard() {
        return "admin_dashboard";
    }

    // Define other methods for admin operations (view users, update ticket fare, etc.)
}
