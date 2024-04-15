package com.bookonrails.ooad.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookonrails.ooad.Model.*;
import com.bookonrails.ooad.Service.OperatingDayService;

@RestController
@RequestMapping(path="/operating-day")
@CrossOrigin(origins = "*")
public class OperatingDayController {
    @Autowired
    private OperatingDayService operatingDayService;

    @GetMapping("/getAll")
    public List<OperatingDay> getOperatingDays() {
        return operatingDayService.getAllOperatingDays();
    }

    // Map DayOfWeek enum to String
    private String mapDayOfWeekToString(DayOfWeek dayOfWeek) {
        switch(dayOfWeek) {
            case DayOfWeek.Monday:
                return "Monday";
            case DayOfWeek.Tuesday:
                return "Tuesday";
            case DayOfWeek.Wednesday:
                return "Wednesday";
            case DayOfWeek.Thursday:
                return "Thursday";
            case DayOfWeek.Friday:
                return "Friday";
            case DayOfWeek.Saturday:
                return "Saturday";
            case DayOfWeek.Sunday:
                return "Sunday";
            default:
                return "Invalid";
        }
    }

    @GetMapping("/get/{trainNo}")
    public List<String> getOperatingDaysByTrainNo(@PathVariable String trainNo) {
        if(trainNo == null || trainNo.isEmpty())
            throw new RuntimeException("Train number is required");
        // check if train exists
        List<OperatingDay> days = operatingDayService.getOperatingDaysByTrainNo(trainNo);
        if(days == null || days.isEmpty())
            throw new RuntimeException("Train not found with number: " + trainNo);
        List<String> d= new ArrayList<>();
        for(OperatingDay day : days) {
            d.add(mapDayOfWeekToString(day.getDayOfWeek()));
        }        
        return d;
    }

    
}
