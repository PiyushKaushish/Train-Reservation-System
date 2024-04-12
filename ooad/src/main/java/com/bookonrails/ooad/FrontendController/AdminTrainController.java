package com.bookonrails.ooad.FrontendController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bookonrails.ooad.Service.TrainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
    public String handleAdd() {

        return "redirect: /admin/train/show";
    }
    


    
    
}
