// AdminController.java

package com.bookonrails.ooad.Controller;

import com.bookonrails.ooad.Interface.UserManagement;
import com.bookonrails.ooad.Model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    private final UserManagement userManagement;

    public AdminController(@Qualifier("adminServiceImpl") UserManagement userManagement) {
        this.userManagement = userManagement;
    }

    @GetMapping("/admin/view-users")
    public String viewUsers(Model model) {
        List<User> userList = userManagement.viewAllUsers();
        model.addAttribute("users", userList);
        return "view_users"; // Assuming there's a Thymeleaf template named "view_users.html"
    }
    @GetMapping("/admin/add-user")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add_user"; // Assuming there's a Thymeleaf template named "add_user.html"
    }

    @PostMapping("/admin/add-user")
    public String addUser(@ModelAttribute User user) {
        userManagement.addUser(user);
        return "redirect:/admin/view-users";
    }

    

    @PostMapping("/admin/delete-user")
    public String deleteUser(@RequestParam("userId") Long userId) {
        userManagement.deleteUser(userId);
        return "redirect:/admin/view-users";
    }

    // Add more methods for other admin operations as needed
}
