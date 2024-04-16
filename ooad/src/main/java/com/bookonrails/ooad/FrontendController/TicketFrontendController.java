package com.bookonrails.ooad.FrontendController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookonrails.ooad.Model.Birth;
import com.bookonrails.ooad.Model.ClassType;
import com.bookonrails.ooad.Model.Gender;
import com.bookonrails.ooad.Model.Passenger;
import com.bookonrails.ooad.Model.SeatAvailability;
import com.bookonrails.ooad.Model.Station;
import com.bookonrails.ooad.Model.Ticket;
import com.bookonrails.ooad.Model.Train;
import com.bookonrails.ooad.Model.User;
import com.bookonrails.ooad.Service.PassengerService;
import com.bookonrails.ooad.Service.SeatAvailabilityService;
import com.bookonrails.ooad.Service.StationService;
import com.bookonrails.ooad.Service.TicketService;
import com.bookonrails.ooad.Service.TrainService;
import com.bookonrails.ooad.Service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path = "/ticket")
@CrossOrigin(origins = "*")
public class TicketFrontendController {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private SeatAvailabilityService seatAvailabilityService;

    @Autowired
    private StationService stationService;

    @PostMapping("/reserve")
    public String getPassengers(@RequestParam("passengerName[]") List<String> names,
            @RequestParam("passengerAge[]") List<Integer> age, @RequestParam("passengerGender[]") List<String> gender,
            @RequestParam("passengerBirth[]") List<String> birth,
            @RequestParam("isSeniorCitizen[]") List<String> isSeniorCitizen, @RequestParam("wantFood") String wantFood,
            @RequestParam("chooseFood") String chooseFood, @RequestParam("countForFood") int countForFood,
            Model m, HttpServletResponse response,
            HttpServletRequest request) {
        // request username cookie
        Cookie[] cookies = request.getCookies();
        // find username cookie
        String username = null;
        String trainNo = null;
        Long seatAvailabilityId = null;
        String srcStation = null;
        String destStation = null;
        String date = null;
        String classType = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                    // break;
                }
                if ("trainNo".equals(cookie.getName())) {
                    trainNo = cookie.getValue();
                }
                if ("seatAvailabilityId".equals(cookie.getName())) {
                    seatAvailabilityId = Long.parseLong(cookie.getValue());
                }
                if ("srcStation".equals(cookie.getName())) {
                    srcStation = cookie.getValue();
                }
                if ("destStation".equals(cookie.getName())) {
                    destStation = cookie.getValue();
                }
                if ("date".equals(cookie.getName())) {
                    date = cookie.getValue();
                }
                if ("classType".equals(cookie.getName())) {
                    classType = cookie.getValue();
                }

            }
        }
        User u = userService.getUserByUsername(username);
        Train train = trainService.getTrainByTrainNo(trainNo);
        SeatAvailability sa = seatAvailabilityService.findById(seatAvailabilityId);
        Station SRC = stationService.getStationByCode(srcStation);
        Station DEST = stationService.getStationByCode(destStation);
        if (u == null) {
            m.addAttribute("error", "You are not logged in!");
            return "error";
        }
        Ticket ticket = new Ticket();
        ticket.setUser(u);
        ticket.setTrain(train);
        ticket.setSeatAvailability(sa);
        ticket.setSRC(SRC);
        ticket.setDEST(DEST);
        ticket.setPNR(ticketService.generateUniquePNR());
        ticket.setDate(Date.valueOf(date));
        ticket.setClasses(ClassType.valueOf(classType));

        if (wantFood.equals("true")) {
            ticket.setWantFood(true);
            if (chooseFood.equals("Veg")) {
                ticket.setVeg(true);
            } else {
                ticket.setVeg(false);
            }
            ticket.setQuantity(countForFood);
            ticket.setFoodprice(ticket.calculateFoodPrice());
        }

        List<Passenger> p = new ArrayList<>();

        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i));
            Passenger pass = new Passenger();
            pass.setName(names.get(i));
            pass.setBirthpreference(Birth.valueOf(birth.get(i)));
            pass.setAge(age.get(i));
            pass.setGender(Gender.valueOf(gender.get(i)));
            pass.setUser(u);
            pass.setTicket(ticket);
            if (isSeniorCitizen.get(i).equals("true")) {
                pass.setSeniorCitizen(true);
            }
            p.add(pass);
        }
        if (p.size() > 0) {
            // List<Passenger> save_p = passengerService.saveAllPassenger(p);
            ticket.setPassengers(p);
            ticket.setTotalAmount();
        }
        Ticket saved_ticket = ticketService.saveTicket(ticket);

        // Set cookie for saved ticket
        Cookie ticketIdCookie = new Cookie("ticketId", saved_ticket.getId().toString());
        ticketIdCookie.setMaxAge(7 * 24 * 60 * 60);
        ticketIdCookie.setPath("/");
        response.addCookie(ticketIdCookie);

        m.addAttribute("ticket", saved_ticket);
        return "train/show_reservation";
    }

    @GetMapping("/show-ticket")
    public String showTicket(Model m,HttpServletRequest request)
    {
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
        m.addAttribute("ticket",t);
        return "train/ticket";
    }

    @GetMapping("/cancel-ticket")
    public String cancelTicket(@RequestParam("ticketId") String ticketId,Model m){
        Ticket t= ticketService.getTicketById(Long.parseLong(ticketId));
        ticketService.cancelTicket(t);
        
        double refund_amount = 0.0;
        if(t.calculateFinalPrice()>0.0){
            refund_amount=t.calculateFinalPrice() - t.getSeatAvailability().getCancellationCharge();
        }
        System.out.println(refund_amount);
        m.addAttribute("message", "The amount"+ refund_amount + " will be refunded in 10-15 working days");
        return "message";
    }

    @GetMapping("/view-ticket")
    public String viewTicket(@RequestParam("ticketId") String ticketId,HttpServletResponse response){
        // set cookie for ticketId
        Cookie ticketIdCookie = new Cookie("ticketId", ticketId);
        ticketIdCookie.setMaxAge(7 * 24 * 60 * 60);
        ticketIdCookie.setPath("/");
        response.addCookie(ticketIdCookie);

        return "redirect:/ticket/show-ticket";

    }

}
