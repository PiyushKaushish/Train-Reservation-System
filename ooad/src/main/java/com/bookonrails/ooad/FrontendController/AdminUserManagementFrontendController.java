package com.bookonrails.ooad.FrontendController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bookonrails.ooad.Model.User;
import com.bookonrails.ooad.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping(path="/admin/user")
@CrossOrigin(origins = "*")
public class AdminUserManagementFrontendController {
    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public String addUser(Model m) {
        m.addAttribute("User",new User());
        return "admin/user/add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        userService.addUser(user);
        return "redirect:/admin/user/show";
    }

    @GetMapping("/show")
    public String showUsers(Model m) {
        m.addAttribute("users", userService.viewAllUsers());
        return "admin/user/show";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("userId") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/admin/user/show";

    }
    


    
}
