package com.bookonrails.ooad.FrontendController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bookonrails.ooad.Model.*;
import com.bookonrails.ooad.Service.RouteService;
import com.bookonrails.ooad.Service.StationService;
import com.bookonrails.ooad.Service.StationTimingsService;

import java.sql.Time;
import java.util.*;

@Controller
@RequestMapping("/admin/station-timings")
@CrossOrigin(origins = "*")
public class AdminStationTimingManagementFrontendController {
    
    @Autowired
    private StationTimingsService stationTimingsService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private StationService stationService;

    @GetMapping("/add")
    public String addStationTimingForm(Model model) {
        model.addAttribute("stations", stationService.getAllStations());
        return "admin/station-timings/add"; 
    }

    @PostMapping("/add")
    public String addStationTiming(@RequestParam("trainNumber") String trainNumber, @RequestParam("stationCodes[]") List<String> stationCodes, @RequestParam("arrivalTimes[]") List<String> arrivalTimes, @RequestParam("departureTimes[]") List<String> departureTimes, @RequestParam("distances[]") List<Double> distances) {
        Route r= routeService.getRouteByTrainNo(trainNumber);
        List<StationTimings> stationTimingsList = new ArrayList<>();
        for (int i = 0; i < stationCodes.size(); i++) {
            StationTimings stationTiming = new StationTimings();
            Station s =  stationService.getStationByCode(stationCodes.get(i));
            stationTiming.setStation(s);
            stationTiming.setArrivalTime(Time.valueOf(arrivalTimes.get(i)));
            stationTiming.setDepartureTime(Time.valueOf(departureTimes.get(i)));
            stationTiming.setDistanceFromNextStation(distances.get(i));
            stationTiming.setRoute(r);
            stationTimingsList.add(stationTiming);
        }
        
        stationTimingsService.addStationTimingsList(stationTimingsList);
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
