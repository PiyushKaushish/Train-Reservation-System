package com.bookonrails.ooad.FrontendController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bookonrails.ooad.Model.Admin;
import com.bookonrails.ooad.Service.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping(path="/admin/admin")
@CrossOrigin(origins = "*")
public class AdminStaffManagementFrontendController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/add")
    public String addUser(Model m) {
        m.addAttribute("Admin",new Admin());
        return "admin/admin/add";
    }

    @PostMapping("/add")
    public String addadmin(@ModelAttribute("admin") Admin admin, Model model) {
        adminService.saveAdmin(admin);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/show")
    public String showadmins(Model m) {
        m.addAttribute("admins", adminService.findAll());
        return "admin/admin/show";
    }

    @PostMapping("/delete")
    public String deleteAdmin(@RequestParam("adminId") Long adminId) {
        adminService.deleteAdmin(adminId);
        return "redirect:/admin/admin/show";
    }

    @GetMapping("/admin-details/{username}")
    public String adminDetails(@RequestParam String username,Model m) {
        Admin u = adminService.findByUsername(username);
        m.addAttribute("Admin", u);
        return "admin/admin/detail";
    }
    
    @GetMapping("/search")
    public String getSearch(Model m){
        return "admin/admin/search";
    }

    @GetMapping("/search/{username}")
    public String searchAdmin(@RequestParam String username, Model m) {
        adminService.findByUsername(username);
        return "redirect:admin/admin/admin-details/"+username;
    }
    
    
    

}
