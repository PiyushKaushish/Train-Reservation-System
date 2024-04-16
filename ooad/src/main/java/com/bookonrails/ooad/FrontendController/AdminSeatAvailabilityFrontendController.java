package com.bookonrails.ooad.FrontendController;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bookonrails.ooad.Model.*;
import com.bookonrails.ooad.Service.SeatAvailabilityService;
import com.bookonrails.ooad.Service.TrainService;

@Controller
@RequestMapping(path = "/admin/seat-availability")
@CrossOrigin(origins = "*")
public class AdminSeatAvailabilityFrontendController {
    @Autowired
    private SeatAvailabilityService seatAvailabilityService;

    @Autowired
    private TrainService trainService;

    @GetMapping("/add")
    public String addSeatAvailability(Model m) {
        m.addAttribute("SeatAvailability", new SeatAvailability());
        return "admin/seat-availability/add";
    }

    @PostMapping("/add")
    public String addSeatAvailability(
            @RequestParam("trainNo") String trainNo,
            @RequestParam("date") String date,
            @RequestParam("classes") String classes,
            @RequestParam("no_of_coaches") int noOfCoaches,
            @RequestParam("basePrice") double basePrice,
            @RequestParam("farePerKM") double farePerKM,
            @RequestParam("seniorCitizenDiscount") double seniorCitizenDiscount,
            @RequestParam("cancellationCharge") double cancellationCharge,
            Model model) {
        SeatAvailability seatAvailability = new SeatAvailability(noOfCoaches);
        // get train
        Train t = trainService.getTrainByTrainNo(trainNo);
        if (t == null) {
            model.addAttribute("message", "Train not found with number: " + trainNo);
            return "message";
        } else {
            seatAvailability.setTrain(t);
        }
        seatAvailability.setDate(Date.valueOf(date));
        // map classes to enum ClassType
        switch (classes) {
            case "Sleeper":
                seatAvailability.setClasses(ClassType.Sleeper);
                break;
            case "AC3Tier":
                seatAvailability.setClasses(ClassType.AC3Tier);
                break;
            case "AC2Tier":
                seatAvailability.setClasses(ClassType.AC2Tier);
                break;
            default:
                model.addAttribute("message", "Invalid class type");
                return "message";
        }
        seatAvailability.setBasePrice(basePrice);
        seatAvailability.setFarePerKM(farePerKM);
        seatAvailability.setSeniorCitizenDiscount(seniorCitizenDiscount);
        seatAvailability.setCancellationCharge(cancellationCharge);
        seatAvailabilityService.addSeatAvailabitity(seatAvailability);
        return "redirect:/admin/seat-availability/show";
    }

    @GetMapping("/show")
    public String showSeatAvailabilities(Model model) {
        List<SeatAvailability> s = seatAvailabilityService.getAllSeatAvailabilities();
        // sort by date
        s.sort((a, b) -> a.getDate().compareTo(b.getDate()));
        // Remove those whose date is less than current date
        Date currentDate = new Date(System.currentTimeMillis());
        s.removeIf(seat -> seat.getDate().before(currentDate));
        model.addAttribute("seatAvailability",s );
        return "admin/seat-availability/show";
    }

    @GetMapping("/delete/{id}")
    public String deleteSeatAvailability(@PathVariable String id) {
        seatAvailabilityService.deleteSeatAvailabitity(seatAvailabilityService.getSeatAvailibity(Long.parseLong(id)));
        return "redirect:/admin/seat-availability/show";
    }

    @GetMapping("/update/{seatAvailabilityId}")
    public String updateSeatAvailabilityForm(@PathVariable Long seatAvailabilityId, Model model) {
        SeatAvailability seatAvailability = seatAvailabilityService.getSeatAvailibity(seatAvailabilityId);
        model.addAttribute("seatAvailability", seatAvailability);
        return "admin/seat-availability/update";
    }

    @PostMapping("/update/{seatAvailabilityId}")
    public String updateSeatAvailability(@PathVariable Long seatAvailabilityId,
            @ModelAttribute("seatAvailability") SeatAvailability updatedSeatAvailability) {
        updatedSeatAvailability.setId(seatAvailabilityId);
        seatAvailabilityService.updateSeatAvailibity(updatedSeatAvailability);
        return "redirect:/admin/seat-availability/show";
    }

}
