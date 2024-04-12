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
import com.bookonrails.ooad.Service.StationService;

import com.bookonrails.ooad.Model.ClassType;

@Controller
@RequestMapping(path="/train")
@CrossOrigin(origins = "*")
public class TrainFrontendController {
    @Autowired
    private TrainService trainService;
    @Autowired
    private StationService stationService;

    @GetMapping(path="/search")
    public String searchTrain(Model model) {
        List<Station> stations = stationService.getAllStations();
        model.addAttribute("stations", stations);
        return "train/search_train";
    }

    @PostMapping(path="/view")
    public String searchTrain(@RequestParam("date") String date, @RequestParam("fromStation") String source, @RequestParam("toStation") String destination,@RequestParam("classType") String classType, Model model) {
        // Date d= Date.valueOf(date);
        
        System.out.println(date+ " "+ source + " "+ destination+" "+ classType);
        List<Train> t= trainService.searchTrainBySrcAndDest(source, destination);
        System.out.println(t.size());
        // System.out.println(t.get(0).getTrainName());
        // System.out.println(t.get(1).getTrainName());
        model.addAttribute("trains",t );
        // fix input format and then send the trains
        return "train/search_results";
    }

    
    


    
}


