package com.bookonrails.ooad.Controller;

import com.bookonrails.ooad.Model.Station;
import com.bookonrails.ooad.Service.StationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/station")
@CrossOrigin(origins = "*")
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping("/getAll")
    public List<Station> getStations() {
        return stationService.getAllStations();
    }

}


