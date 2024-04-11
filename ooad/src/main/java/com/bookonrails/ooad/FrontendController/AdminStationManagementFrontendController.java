package com.bookonrails.ooad.FrontendController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bookonrails.ooad.Model.Station;
import com.bookonrails.ooad.Service.StationService;


@Controller
@RequestMapping(path="/admin/station")
@CrossOrigin(origins = "*")
public class AdminStationManagementFrontendController {
    @Autowired
    private StationService stationService;

    @GetMapping("/add")
    public String addStation(Model m) {
        m.addAttribute("Station",new Station());
        return "admin/station/add";
    }

    @PostMapping("/add")
    public String addStation(@ModelAttribute("Station") Station station, Model model) {
        stationService.addStation(station);
        return "redirect:/admin/station/show";
    }

    @GetMapping("/show")
    public String showStations(Model model) {
        model.addAttribute("stations", stationService.getAllStations());
        return "admin/station/show"; 
    }

    @PostMapping("/delete")
    public String deleteStation(@RequestParam("stationCode") String stationCode) {
        stationService.deleteStation(stationCode);
        return "redirect:/admin/station/show";
    }

    @GetMapping("/update/{stationCode}")
    public String updateStationForm(@PathVariable String stationCode, Model model) {
        Station station = stationService.getStationByCode(stationCode);
        model.addAttribute("station", station);
        return "admin/station/update"; 
    }

    @PostMapping("/update/{stationCode}")
    public String updateStation(@PathVariable String stationCode, @ModelAttribute("station") Station updatedStation) {
        System.out.println(updatedStation);
        stationService.updateStation(stationCode, updatedStation);
        return "redirect:/admin/station/show";
    }
}
