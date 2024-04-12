package com.bookonrails.ooad.FrontendController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookonrails.ooad.Model.DayOfWeek;
import com.bookonrails.ooad.Model.OperatingDay;
import com.bookonrails.ooad.Model.Train;
import com.bookonrails.ooad.Service.OperatingDayService;
import com.bookonrails.ooad.Service.TrainService;

@Controller
@RequestMapping("/admin/operating-day")
@CrossOrigin(origins = "*")
public class AdminOperatingDayController {
    @Autowired
    private OperatingDayService operatingDayService;

    @Autowired
    private TrainService trainService;

    // Add
    @GetMapping(path="/add")
    public String addOperatingDay(Model model) {
        model.addAttribute("operatingDay", new OperatingDay());
        model.addAttribute("dow", DayOfWeek.values());

        return  "admin/operating-day/add";
    }

    @PostMapping(path= "/add")
    public String handleAdd(@RequestParam("dow[]") List<DayOfWeek> dayOfWeek, @RequestParam("trainNo") String trainNo,Model model) {
        Train t= trainService.getTrainByTrainNo(trainNo);
        if(t==null){
            model.addAttribute("message", "Train not found with number: " + trainNo);
            return "message";
        }
        for (DayOfWeek dow : dayOfWeek) {
            OperatingDay operatingDay = new OperatingDay();
            operatingDay.setDayOfWeek(dow);
            operatingDay.setTrain(t);
            operatingDayService.addOperatingDay(operatingDay);
        }
        return "redirect:/admin/operating-day/show";
    }

    // Show
    @GetMapping(path="/show")
    public String showOperatingDays(Model m) {
        m.addAttribute("operatingDays", operatingDayService.getAllOperatingDays());
        return "admin/operating-day/show";
    }

    // Delete
    @PostMapping(path="/delete")
    public String deleteOperatingDay(@RequestParam("opId") Long operatingDayId) {
        operatingDayService.deleteOperatingDay(operatingDayId);
        return "redirect:/admin/operating-day/show";
    }

    // Update
    @GetMapping(path="/update/{id}")
    public String updateOperatingDay(@PathVariable("id") Long operatingDayId, Model m) {
        OperatingDay operatingDay = operatingDayService.getOperatingDayById(operatingDayId);
        if (operatingDay == null) {
            return "redirect:/admin/operating-day/show?error=OperatingDay%20not%20found%20with%20id: " + operatingDayId;
        }

        m.addAttribute("operatingDay", operatingDay);
        return "admin/operating-day/update";
    }

    @PostMapping(path="/update/{id}")
    public String handleUpdate(@PathVariable("id") Long operatingDayId, @ModelAttribute("operatingDay") OperatingDay updatedOperatingDay) {
        operatingDayService.updateOperatingDay(operatingDayId, updatedOperatingDay);
        return "redirect:/admin/operating-day/show";
    }
    
}
