package com.bookonrails.ooad.FrontendController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookonrails.ooad.Model.Ticket;
import com.bookonrails.ooad.Service.TicketService;

import java.util.List;

@Controller
public class AdminTicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/admin/ticket")
    public String showTickets(Model model) {
        // Get all tickets from the service
        List<Ticket> tickets = ticketService.getAllTickets();
        // Add the tickets to the model
        model.addAttribute("tickets", tickets);
        // Return the view name for ticket details page
        return "admin/ticket";
    }
}
