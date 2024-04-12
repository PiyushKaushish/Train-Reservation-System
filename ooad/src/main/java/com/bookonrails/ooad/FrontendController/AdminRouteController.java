package com.bookonrails.ooad.FrontendController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.bookonrails.ooad.Model.Route;
import com.bookonrails.ooad.Service.RouteService;

@Controller
@RequestMapping("/admin/route")
public class AdminRouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/add")
    public String showAddRouteForm(Model model) {
        model.addAttribute("route", new Route());
        return "admin/route/add"; // This will render the add_route.html template
    }
    @PostMapping("/add")
    public String addRoute(@ModelAttribute("route") Route route, Model model) {
        String routeCode = route.getRouteCode();
        Route existingRoute = routeService.getRouteByRouteCode(routeCode);
        
        if (existingRoute != null) {
            model.addAttribute("message", "Route with code " + routeCode + " already exists.");
            return "message"; // Render an error page
        }
        
        // If the route does not exist, add it
        routeService.addRoute(route);
        
        // Redirect to the show route page
        return "redirect:/admin/route/show";
    }
    @GetMapping("/show")
    public ModelAndView showRoutes() {
        ModelAndView modelAndView = new ModelAndView("admin/route/show");
        modelAndView.addObject("routes", routeService.getAllRoutes()); // Change "route" to "routes"
        return modelAndView;
    }
    

    @PostMapping("/delete/{id}")
    public String deleteRoute(@PathVariable("id") Long routeId) {
        routeService.deleteRoute(routeId);
        return "redirect:/admin/route/show"; // Redirect to the show route page after deletion
    }
    
    @GetMapping("/getRoutesBetweenStations")
    public String getRoutesBetweenStations(@RequestParam("src") String src, @RequestParam("dest") String dest, Model model) {
        List<Route> routes = routeService.getRoutesBetweenStations(src, dest);
        model.addAttribute("routes", routes);
        return "view-routes"; // Render the view-routes.html template
    }
}
