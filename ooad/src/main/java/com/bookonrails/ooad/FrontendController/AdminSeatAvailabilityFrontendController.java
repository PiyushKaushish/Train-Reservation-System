package com.bookonrails.ooad.FrontendController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bookonrails.ooad.Model.*;
import com.bookonrails.ooad.Service.SeatAvailabilityService;


@Controller
@RequestMapping(path="/admin/seat-availability")
@CrossOrigin(origins = "*")
public class AdminSeatAvailabilityFrontendController {
    @Autowired
    private SeatAvailabilityService seatAvailabilityService;

    @GetMapping("/add")
    public String addSeatAvailability(Model m) {
        m.addAttribute("SeatAvailability",new SeatAvailability());
        return "admin/seat-availability/add";
    }

    @PostMapping("/add")
    public String addSeatAvailability(@ModelAttribute("SeatAvailability") SeatAvailability seatAvailability, Model model) {
        seatAvailabilityService.addSeatAvailabitity(seatAvailability);
        return "redirect:/admin/seat-availability/show";
    }

    @GetMapping("/show")
    public String showSeatAvailabilities(Model model) {
        model.addAttribute("seatAvailabilities", seatAvailabilityService.getAllSeatAvailabilities());
        return "admin/seat-availability/show"; 
    }

    @PostMapping("/delete")
    public String deleteSeatAvailability(@RequestParam("seatAvailabilityId") Long id) {
        seatAvailabilityService.deleteSeatAvailabitity(seatAvailabilityService.getSeatAvailibity(id));
        return "redirect:/admin/seat-availability/show";
    }

    @GetMapping("/update/{seatAvailabilityId}")
    public String updateSeatAvailabilityForm(@PathVariable Long seatAvailabilityId, Model model) {
        SeatAvailability seatAvailability = seatAvailabilityService.getSeatAvailibity(seatAvailabilityId);
        model.addAttribute("seatAvailability", seatAvailability);
        return "admin/seat-availability/update"; 
    }

    @PostMapping("/update/{seatAvailabilityId}")
    public String updateSeatAvailability(@PathVariable Long seatAvailabilityId, @ModelAttribute("seatAvailability") SeatAvailability updatedSeatAvailability) {
        updatedSeatAvailability.setId(seatAvailabilityId); 
        seatAvailabilityService.updateSeatAvailibity(updatedSeatAvailability);
        return "redirect:/admin/seat-availability/show";
    }
    
}
