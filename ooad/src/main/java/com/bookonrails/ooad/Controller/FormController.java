package com.bookonrails.ooad.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/form")
public class FormController {

    @GetMapping("/showForm")
    public String showForm(Model model) {
        model.addAttribute("form", new Form()); // Assuming 'Form' is your model object
        return "form";
    }

    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute("form") Form form, Model model) {
        // Process the form data
        System.out.println("Name: " + form.getName());
        System.out.println("Email: " + form.getEmail());

        model.addAttribute("name", form.getName());
        model.addAttribute("email", form.getEmail());

        // Return the logical view name of the success page
        return "success"; // Assuming "success" is the logical view name of your success page
    }

}