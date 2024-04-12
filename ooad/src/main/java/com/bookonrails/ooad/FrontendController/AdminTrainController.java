package com.bookonrails.ooad.FrontendController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bookonrails.ooad.Model.Train;
import com.bookonrails.ooad.Service.TrainService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/admin/train")
@CrossOrigin(origins = "*")
public class AdminTrainController {
    @Autowired
    private TrainService trainService;

    @GetMapping(path="/add")
    public String addTrain() {
        return  "admin/train/add";
    }

    @PostMapping(path= "/add")
    public String handleAdd(@ModelAttribute("train") Train train) {
        trainService.saveTrain(train);
        return "redirect: /admin/train/show";
    }

    @GetMapping(path="/show")
    public String showTrains(Model m) {
        m.addAttribute("trains", trainService.getAllTrains());
        return "admin/train/show";
    }

    @PostMapping(path="/delete/{trainNo}")
    public String deleteTrain(@PathVariable("trainNo") String trainNo) {
        trainService.deleteTrain(trainNo);
        return "redirect:/admin/train/show";
    }

    @GetMapping(path="/update/{trainNo}")
    public String updateTrain(@PathVariable("trainNo") String trainNo, Model m) {
        Train train = trainService.getTrainByTrainNo(trainNo);
        if (train == null) {
            return "redirect:/admin/train/show?error=Train%20not%20found%20with%20trainNo: " + trainNo;
        }

        m.addAttribute("train", train);
        return "admin/train/update";
    }

    @PostMapping(path="/update/{trainNo}")
    public String handleUpdate(@PathVariable("trainNo") String trainNo, @ModelAttribute("train") Train updatedTrain) {
        trainService.updateTrain(trainNo, updatedTrain);
        return "redirect:/admin/train/show";
    }
    


    
    
}
