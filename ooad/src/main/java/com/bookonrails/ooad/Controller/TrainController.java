// package com.bookonrails.ooad.Controller;

// import com.bookonrails.ooad.Model.Train;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import java.util.List;

// @Controller
// public class TrainController {

//     // Assuming you have a service class that handles train-related operations
//     private final TrainService trainService;

//     @Autowired
//     public TrainController(TrainService trainService) {
//         this.trainService = trainService;
//     }

//     @GetMapping("/search")
//     public String showSearchForm() {
//         return "search-train"; // This maps to search-train.html
//     }

//     @GetMapping("/search-results")
//     public String searchTrains(@RequestParam("date") String date,
//                                @RequestParam("day") String day,
//                                @RequestParam("fromStation") String fromStation,
//                                @RequestParam("toStation") String toStation,
//                                Model model) {

//         // Logic to search trains based on the provided parameters
//         List<Train> trains = trainService.findTrainsBySearchCriteria(date, day, fromStation, toStation);

//         if (trains.isEmpty()) {
//             model.addAttribute("message", "No trains found for the given search criteria.");
//         } else {
//             model.addAttribute("trains", trains);
//         }

//         return "search-results"; // Return a new HTML page to display results
//     }
// }

package com.bookonrails.ooad.Controller;

import com.bookonrails.ooad.Model.Train;
import com.bookonrails.ooad.Service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TrainController {

    private final TrainService trainService;

    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping("/trains")
    public String getAllTrains(Model model) {
        List<Train> trains = trainService.getAllTrains();
        model.addAttribute("trains", trains);
        return "train-list";
    }
}

