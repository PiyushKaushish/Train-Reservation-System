package com.bookonrails.ooad.FrontendController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bookonrails.ooad.Model.*;
import com.bookonrails.ooad.Service.*;

import java.sql.Time;
import java.time.LocalTime;
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

    @Autowired
    private TrainService trainService;

    @GetMapping("/add")
    public String addStationTimingForm(Model model) {
        model.addAttribute("stations", stationService.getAllStations());
        return "admin/station-timings/add";
    }

    @PostMapping("/add")
    public String addStationTiming(@RequestParam("trainNumber") String trainNumber,
            @RequestParam("stationCode[]") List<String> stationCodes,
            @RequestParam("arrivalTimes[]") List<String> arrivalTimes,
            @RequestParam("departureTimes[]") List<String> departureTimes,
            @RequestParam("distance[]") List<Double> distances) {
        Route r = routeService.getRouteByTrainNo(trainNumber);
        List<StationTimings> stationTimingsList = new ArrayList<>();
        for (int i = 0; i < stationCodes.size(); i++) {
            StationTimings stationTiming = new StationTimings();
            Station s = stationService.getStationByCode(stationCodes.get(i));
            if (s == null) {
                return "redirect:/admin/station-timings/add?error=stationNotFound";
            }
            stationTiming.setStation(s);
            LocalTime localTime = LocalTime.parse(arrivalTimes.get(i));
            Time arrivalTime = Time.valueOf(localTime);
            stationTiming.setArrivalTime(arrivalTime);
            localTime = LocalTime.parse(departureTimes.get(i));
            Time departureTime = Time.valueOf(localTime);
            stationTiming.setDepartureTime(departureTime);
            stationTiming.setDistanceFromNextStation(distances.get(i));
            stationTiming.setRoute(r);
            stationTimingsList.add(stationTiming);
        }

        stationTimingsService.addStationTimingsList(stationTimingsList);
        return "redirect:/admin/station-timings/show";
    }

    @PostMapping("/single-add/{id}")
    public String addSingleStationTimingForm(@PathVariable Long id,  @RequestParam("stationCode") String stationCode, @RequestParam("arrivalTime") String arrivalTime, @RequestParam("departureTime") String departureTime, @RequestParam("distance") Double distance) {
        Route route = routeService.getRouteById(id);
        StationTimings stationTiming = new StationTimings();
        stationTiming.setRoute(route);
        Station s = stationService.getStationByCode(stationCode);
        stationTiming.setStation(s);
        LocalTime localTime = LocalTime.parse(arrivalTime);
        Time arrivalTime1 = Time.valueOf(localTime);
        stationTiming.setArrivalTime(arrivalTime1);
        localTime = LocalTime.parse(departureTime);
        Time departureTime1 = Time.valueOf(localTime);
        stationTiming.setDepartureTime(departureTime1);
        stationTiming.setDistanceFromNextStation(distance);
        stationTimingsService.addStationTimings(stationTiming);
        return "redirect: /admin/station-timings/show";
    }

    @GetMapping("/single-add/{id}")
    public String showSingleStationTimingForm(@PathVariable Long id, Model model) {
        Route route = routeService.getRouteById(id);
        model.addAttribute("route", route);
        model.addAttribute("stations", stationService.getAllStations());
        return "admin/station-timings/add-single";
    }

    @GetMapping("/show")
    public String showStationTimings(Model model) {
        model.addAttribute("trains", trainService.getAllTrains());
        return "admin/station-timings/show";
    }

    @GetMapping("/show/{route_id}")
    public String showStationTimingsByRoute(@PathVariable Long route_id, Model model) {
        Route route = routeService.getRouteById(route_id);
        List<StationTimings> stationTimings = stationTimingsService.getStationTimingsByRoute(route);
        model.addAttribute("TrainNo", route.getTrain().getTrainNo());
        model.addAttribute("TrainName", route.getTrain().getTrainName());
        model.addAttribute("route", route);
        model.addAttribute("stationTimings", stationTimings);
        return "admin/station-timings/show-details";
    }

    @GetMapping("/update/{id}")
    public String updateStationTimingForm(@PathVariable Long id, Model model) {
        StationTimings stationTiming = stationTimingsService.getStationTimingsById(id);
        model.addAttribute("stationTiming", stationTiming);
        model.addAttribute("stations", stationService.getAllStations());
        return "admin/station-timings/update";
    }

    @PostMapping("/update/{id}")
    public String updateStationTiming(@PathVariable Long id, @RequestParam("stationCode") String stationCode, @RequestParam("arrivalTime") String arrivalTime, @RequestParam("departureTime") String departureTime, @RequestParam("distance") Double distance) {
        StationTimings updatedStationTiming = new StationTimings();
        StationTimings st= stationTimingsService.getStationTimingsById(id);
        updatedStationTiming.setRoute(st.getRoute());
        Station s = stationService.getStationByCode(stationCode);
        updatedStationTiming.setStation(s);
        LocalTime localTime = LocalTime.parse(arrivalTime);
        Time arrivalTime1 = Time.valueOf(localTime);
        updatedStationTiming.setArrivalTime(arrivalTime1);
        localTime = LocalTime.parse(departureTime);
        Time departureTime1 = Time.valueOf(localTime);
        updatedStationTiming.setDepartureTime(departureTime1);
        updatedStationTiming.setDistanceFromNextStation(distance);
        stationTimingsService.updateStationTimings(id, updatedStationTiming);
        return "redirect:/admin/station-timings/show";
    }

    @PostMapping("/delete")
    public String deleteStationTiming(@RequestParam("timingId") Long id) {
        stationTimingsService.deleteStationTimings(id);
        return "redirect:/admin/station-timings/show";
    }

}
