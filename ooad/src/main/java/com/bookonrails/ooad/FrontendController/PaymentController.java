package com.bookonrails.ooad.FrontendController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookonrails.ooad.Model.Payment;
import com.bookonrails.ooad.Model.PaymentStatus;
import com.bookonrails.ooad.Model.Ticket;
import com.bookonrails.ooad.Service.PaymentService;
import com.bookonrails.ooad.Service.TicketService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path="/payment")
@CrossOrigin(origins = "*")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/make-payment")
    public String makePayment(  HttpServletRequest request, HttpServletResponse response) {
        Payment payment = new Payment();
        String ticketId = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("ticketId".equals(cookie.getName())) {
                    ticketId = cookie.getValue();
                    break;
                }
            }
        }
        Ticket t= ticketService.getTicketById(Long.parseLong(ticketId));
        payment.setTicket(t);
        Payment p= paymentService.savePayment(payment);
        t.setPayment(p);
        ticketService.updateTicket(t);

        // Set cookie for saved ticket
        Cookie paymentIdCookie = new Cookie("paymentId", p.getId().toString());
        paymentIdCookie.setMaxAge(7 * 24 * 60 * 60);
        paymentIdCookie.setPath("/");
        response.addCookie(paymentIdCookie);
        
        return "make-payment";
    }

    @GetMapping("/pay-now")
    public String payNow(HttpServletResponse response, HttpServletRequest request){
        String paymentId = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("paymentId".equals(cookie.getName())) {
                    paymentId = cookie.getValue();
                    break;
                }
            }
        }
        Payment payment = paymentService.getPaymentById(Long.parseLong(paymentId));
        Ticket t= payment.getTicket();
        t.setPaymentStatus(PaymentStatus.Success);
        Payment p= paymentService.updatePaymentStatus(Long.parseLong(paymentId), PaymentStatus.Success);
        t.setPayment(p);
        ticketService.allocateSeats(t);
        ticketService.updateTicket(t);
        return "redirect:/ticket/show-ticket";

    }
    
}
