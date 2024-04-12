package com.bookonrails.ooad.FrontendController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.bookonrails.ooad.Model.Train;
import com.bookonrails.ooad.Service.RouteService;
import com.bookonrails.ooad.Service.TrainService;

@Controller
@RequestMapping("/admin/train")
@CrossOrigin(origins = "*")
public class AdminTrainController {

    @Autowired
    private TrainService trainService;

    @Autowired
    private RouteService routeService;

    @GetMapping("/add")
    public String addTrain(Model m) {
        m.addAttribute("train", new Train());
        m.addAttribute("routes", routeService.getAllRoutes());
        return "admin/train/add";
    }

    @PostMapping("/add")
    public String addTrain(@ModelAttribute("train") Train train, Model model) {
        trainService.saveTrain(train);
        return "redirect:/admin/train/show";
    }

    @GetMapping("/show")
    public String showTrains(Model model) {
        model.addAttribute("trains", trainService.getAllTrains());
        return "admin/train/show"; 
    }

    @PostMapping("/delete/{trainNo}")
    public String deleteTrain(@PathVariable("trainNo") String trainNo) {
        trainService.deleteTrain(trainNo);
        return "redirect:/admin/train/show";
    }

    @GetMapping("/update/{trainNo}")
    public String updateTrainForm(@PathVariable String trainNo, Model model) {
        Train train = trainService.getTrainByTrainNo(trainNo);
        if (train == null) {
            return "redirect:/admin/train/show?error=Train%20not%20found%20with%20trainNo: " + trainNo;
        }
        model.addAttribute("train", train);
        return "admin/train/update"; 
    }

    @PostMapping("/update/{trainNo}")
    public String updateTrain(@PathVariable String trainNo, @ModelAttribute("train") Train updatedTrain) {
        trainService.updateTrain(trainNo, updatedTrain);
        return "redirect:/admin/train/show";
    }
}
