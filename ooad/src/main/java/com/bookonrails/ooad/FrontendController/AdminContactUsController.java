package com.bookonrails.ooad.FrontendController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import com.bookonrails.ooad.Model.Contact;
import com.bookonrails.ooad.Service.ContactService;
import org.springframework.beans.factory.annotation.Qualifier;

@Controller
public class AdminContactUsController {

    private final ContactService contactService;

    public AdminContactUsController(@Qualifier("frontendContactService") ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/admin/contact-us-view")
    public String viewContactQueries(Model model) {
        // Retrieve contact queries from the service
        List<Contact> contactQueries = contactService.getAllContacts();
        // Add contact queries to the model
        model.addAttribute("contactQueries", contactQueries);
        // Return the name of the Thymeleaf template to be rendered
        return "admin/contact-us-view";
    }
}
