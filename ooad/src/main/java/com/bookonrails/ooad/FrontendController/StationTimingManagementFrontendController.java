package com.bookonrails.ooad.FrontendController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.bookonrails.ooad.Model.StationTimings;
import com.bookonrails.ooad.Service.StationTimingsService;

@Controller
@RequestMapping("/admin/station-timings")
@CrossOrigin(origins = "*")
public class StationTimingManagementFrontendController {
    
    @Autowired
    private StationTimingsService stationTimingsService;

    @GetMapping("/add")
    public String addStationTimingForm(Model model) {
        model.addAttribute("stationTiming", new StationTimings());
        return "admin/station-timings/add"; 
    }

    @PostMapping("/add")
    public String addStationTiming(@ModelAttribute("stationTiming") StationTimings stationTiming) {
        stationTimingsService.addStationTimings(stationTiming);
        return "redirect:/admin/station-timings/show";
    }

    @GetMapping("/show")
    public String showStationTimings(Model model) {
        model.addAttribute("stationTimings", stationTimingsService.getAllStationTimings());
        return "admin/station-timings/show"; 
    }

    @GetMapping("/update/{id}")
    public String updateStationTimingForm(@PathVariable Long id, Model model) {
        StationTimings stationTiming = stationTimingsService.getStationTimingsById(id);
        model.addAttribute("stationTiming", stationTiming);
        return "admin/station-timings/update"; 
    }

    @PostMapping("/update/{id}")
    public String updateStationTiming(@PathVariable Long id, @ModelAttribute("stationTiming") StationTimings updatedStationTiming) {
        updatedStationTiming.setId(id); 
        stationTimingsService.updateStationTimings(id,updatedStationTiming);
        return "redirect:/admin/station-timings/show";
    }

    @PostMapping("/delete")
    public String deleteStationTiming(@RequestParam("timingId") Long id) {
        stationTimingsService.deleteStationTimings(id);
        return "redirect:/admin/station-timings/show";
    }

}
