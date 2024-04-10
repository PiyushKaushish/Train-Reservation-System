package com.bookonrails.ooad.FrontendController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bookonrails.ooad.Model.Contact;
import com.bookonrails.ooad.Service.ContactService;

import jakarta.servlet.http.*;

@Controller
@RequestMapping(path="/")
@CrossOrigin(origins = "*")
public class HomeFrontendController {
    private String appname="Book On Rails";

    @Autowired
    private ContactService contactService;
    
    @GetMapping("/")
    public String homePage(Model m){
        m.addAttribute("appname", appname);
        return "home/home";
    }

    @GetMapping("/about")
    public String aboutPage(Model m){
        m.addAttribute("appname", appname);
        return "home/about";
    }

    // contact us
    @GetMapping("/contact-us")
    public String contactPage(Model m){
        m.addAttribute("title", "Contact us");
        m.addAttribute("Contact", new Contact());
        return "home/contact";
    }

    // contact us post
    @PostMapping("/contact-us")
    public String contactPostPage(@ModelAttribute("contact") Contact contact,Model m){
        contactService.saveContact(contact);
        m.addAttribute("message", "Your message has been sent successfully");
        return  "message";
    }

    //base
    @GetMapping("/base")
    public String basePage(Model m, HttpServletRequest request){
        return "base";
    }

}
