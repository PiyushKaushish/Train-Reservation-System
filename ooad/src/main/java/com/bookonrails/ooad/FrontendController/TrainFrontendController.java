package com.bookonrails.ooad.FrontendController;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bookonrails.ooad.Model.Train;
import com.bookonrails.ooad.Model.Station;
import com.bookonrails.ooad.Service.TrainService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import com.bookonrails.ooad.Service.StationService;

import com.bookonrails.ooad.Model.ClassType;
import com.bookonrails.ooad.Model.Route;
import com.bookonrails.ooad.Model.SeatAvailability;

@Controller
@RequestMapping(path = "/train")
@CrossOrigin(origins = "*")
public class TrainFrontendController {
    @Autowired
    private TrainService trainService;
    @Autowired
    private StationService stationService;

    @GetMapping(path = "/search")
    public String searchTrain(Model model) {
        List<Station> stations = stationService.getAllStations();
        model.addAttribute("stations", stations);
        return "train/search_train";
    }

    @GetMapping("/view-train-route")
    public String viewTrainRoute(Model model){
        model.addAttribute("train",trainService.getAllTrains());
        return "train/show_routes";
    }

    @PostMapping("/view-train-route")
    public String showTrainRoute(@RequestParam("trainNo") String trainNo, Model m){
        // get train 
        Train t= trainService.getTrainByTrainNo(trainNo);
        Route r= t.getRoute();
        m.addAttribute("train",t);

        m.addAttribute("stationTimings",r.getStationTimings());
        return "train/schedule";
    }

    @PostMapping(path = "/search")
    public String searchTrainForm(@RequestParam("date") String date, @RequestParam("fromStation") String source,
            @RequestParam("toStation") String destination, @RequestParam("classType") String classType, Model model,HttpServletResponse response) {
        Date d = Date.valueOf(date);
        System.out.println(classType);

        ClassType classes;
        if ("AC2Tier".equals(classType)) {
            classes = ClassType.AC2Tier;
        } else if ("AC3Tier".equals(classType)) {
            classes = ClassType.AC3Tier;
        } else if ("Sleeper".equals(classType)) {
            classes = ClassType.Sleeper;
        } else {
            model.addAttribute("message", "Class does not exist");
            return "message";
        }

        Cookie classTypeCookie = new Cookie("classType", classType);
        classTypeCookie.setMaxAge(7*24*60*60);
        classTypeCookie.setPath("/");
        response.addCookie(classTypeCookie);

        Cookie dateCookie = new Cookie("date", date);
        dateCookie.setMaxAge(7*24*60*60);
        dateCookie.setPath("/");
        response.addCookie(dateCookie);

        Cookie srcStationCookie = new Cookie("srcStation", source);
        srcStationCookie.setMaxAge(7*24*60*60);
        srcStationCookie.setPath("/");
        response.addCookie(srcStationCookie);

        Cookie destStationCookie = new Cookie("destStation", destination);
        destStationCookie.setMaxAge(7*24*60*60);
        destStationCookie.setPath("/");
        response.addCookie(destStationCookie);

        System.out.println(date + " " + source + " " + destination + " " + classType);
        List<SeatAvailability> seatA = trainService.searchTrain(source, destination, classes, d);
        System.out.println(seatA.size());
        // System.out.println(t.get(0).getTrainName());
        // System.out.println(t.get(1).getTrainName());
        model.addAttribute("seatAvailability", seatA);
        // fix input format and then send the trains
        return "train/search_results";
    }

    @GetMapping("/book")
    public String bookTrain(@RequestParam("trainNo") String trainNo,@RequestParam("seatAvailabilityId") String seatAvailabilityId ,Model model, HttpServletResponse response) {
        // Set the train number in a cookie
        Cookie trainNoCookie = new Cookie("trainNo", trainNo);
        trainNoCookie.setMaxAge(7 * 24 * 60 * 60); // Cookie expires in 7 days
        trainNoCookie.setPath("/"); // Set cookie path to root
        response.addCookie(trainNoCookie);

        Cookie seatAvailabilityIdCookie = new Cookie("seatAvailabilityId", seatAvailabilityId);
        seatAvailabilityIdCookie.setMaxAge(7*24*60*60);
        seatAvailabilityIdCookie.setPath("/");
        response.addCookie(seatAvailabilityIdCookie);

        // Add the train number to the model
        model.addAttribute("trainNo", trainNo);
        model.addAttribute("seatAvailabilityId", seatAvailabilityId);

        // Return the name of the HTML page to display train number details
        return "train/train_reserve";
    }

}
