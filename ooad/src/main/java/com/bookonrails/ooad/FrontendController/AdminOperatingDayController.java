package com.bookonrails.ooad.FrontendController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bookonrails.ooad.Model.OperatingDay;
import com.bookonrails.ooad.Service.OperatingDayService;

@Controller
@RequestMapping("/admin/operating-day")
@CrossOrigin(origins = "*")
public class AdminOperatingDayController {
    @Autowired
    private OperatingDayService operatingDayService;

    // Add
    @GetMapping(path="/add")
    public String addOperatingDay() {
        return  "admin/operating-day/add";
    }

    @PostMapping(path= "/add")
    public String handleAdd(@ModelAttribute OperatingDay operatingDay) {
        operatingDayService.addOperatingDay(operatingDay);
        return "redirect: /admin/operating-day/show";
    }

    // Show
    @GetMapping(path="/show")
    public String showOperatingDays(Model m) {
        m.addAttribute("operatingDays", operatingDayService.getAllOperatingDays());
        return "admin/operating-day/show";
    }

    // Delete
    @PostMapping(path="/delete/{id}")
    public String deleteOperatingDay(@PathVariable("id") Long operatingDayId) {
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
