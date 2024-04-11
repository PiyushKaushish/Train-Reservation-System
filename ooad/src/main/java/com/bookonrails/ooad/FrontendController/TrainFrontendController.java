package com.bookonrails.ooad.FrontendController;



import java.sql.Date;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// import com.bookonrails.ooad.Model.ClassType;

@Controller
@RequestMapping(path="/train")
@CrossOrigin(origins = "*")
public class TrainFrontendController {
    @GetMapping(path="/search")
    public String searchTrain() {
        return "train/search_train";
    }

    @PostMapping(path="/search")
    public String searchTrain(@RequestParam("date") String date, @RequestParam("fromStation") String source, @RequestParam("toStation") String destination,@RequestParam("classType") String classType, Model model) {
        Date d= Date.valueOf(date);
        System.out.println(date+ " "+ source + " "+ destination+" "+ classType);
        System.out.println(d);
        // fix input format and then send the trains
        return "train/search_train";
    }



    
}
