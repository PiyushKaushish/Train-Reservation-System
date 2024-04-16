package com.bookonrails.ooad.FrontendController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookonrails.ooad.Model.Ticket;
import com.bookonrails.ooad.Service.TicketService;

@Controller
@RequestMapping(path="/admin/ticket")
@CrossOrigin(origins = "*")
public class AdminTicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/show-ticket")
    public String showTickets(Model model) {
        // Get all tickets from the service
        List<Ticket> tickets = ticketService.getAllTickets();
        // Add the tickets to the model
        model.addAttribute("tickets", tickets);
        // Return the view name for ticket details page
        return "admin/showticket";
    }
    @GetMapping("/show-cancelled-ticket")
    public String showCancelledTickets(Model model) {
        // Get all tickets from the service
        List<Ticket> tickets = ticketService.getCancelledTickets();
        // Add the tickets to the model
        model.addAttribute("tickets", tickets);
        // Return the view name for ticket details page
        return "admin/showticket";
    }

    @GetMapping("/show-waiting-ticket")
    public String showWaitingTickets(Model model) {
        // Get all tickets from the service
        List<Ticket> tickets = ticketService.getWaitingListTickets();
        // Add the tickets to the model
        model.addAttribute("tickets", tickets);
        // Return the view name for ticket details page
        return "admin/showticket";
    }

    @GetMapping("/delete/{id}")
    public String deleteTicket(@PathVariable String id){
        ticketService.deleteTicket(Long.parseLong(id));
        return "redirect:/admin/ticket/show-ticket";
    }

    
}
