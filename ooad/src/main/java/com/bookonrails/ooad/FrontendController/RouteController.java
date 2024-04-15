package com.bookonrails.ooad.FrontendController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookonrails.ooad.Model.Route;
import com.bookonrails.ooad.Service.RouteService;

@Controller
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/routes")
    public String showRoutes(Model model, @CookieValue(value = "trainNo", defaultValue = "") String trainNo) {
        // Fetch route for the specified train number
        Route route = routeService.getRouteByTrainNo(trainNo);
        
        // Add the route to the model
        model.addAttribute("route", route);
        
        return "showRoutes"; // Assuming the name of the template is showRoutes.html
    }
}
