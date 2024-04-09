package com.bookonrails.ooad.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/hello")
@CrossOrigin(origins = "*")
public class HelloWorldController {
    private String message = "Hello Aditi";

    @GetMapping("/")
    public String welcome(Model model) {
        model.addAttribute("message", this.message);
        return "hello"; // This corresponds to the "hello.html" template
    }
}