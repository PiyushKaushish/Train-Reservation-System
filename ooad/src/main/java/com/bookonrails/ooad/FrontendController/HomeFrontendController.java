package com.bookonrails.ooad.FrontendController;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/")
@CrossOrigin(origins = "*")
public class HomeFrontendController {
    private String appname="Book On Rails";
    @GetMapping("/")
    public String homePage(Model m){
        m.addAttribute("appname", appname);
        return "home";
    }

    @GetMapping("/about")
    public String aboutPage(Model m){
        m.addAttribute("appname", appname);
        return "about";
    }

    // contact us
    @GetMapping("/contact-us")
    public String contactPage(Model m){
        m.addAttribute("appname", appname);
        return "contact";
    }

    // // contact us post
    // @PostMapping("/contact")
    // public String contactPostPage(Model m){
    //     m.addAttribute("appname", appname);
    //     return "contact";
    // }

    //base
    @GetMapping("/base")
    public String basePage(Model m){
        return "base";
    }



}
