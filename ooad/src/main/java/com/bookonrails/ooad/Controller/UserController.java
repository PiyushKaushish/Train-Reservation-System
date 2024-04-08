package com.bookonrails.ooad.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookonrails.ooad.Model.User;
import com.bookonrails.ooad.Service.UserService;

@RestController
@RequestMapping(path="/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService userService;

    // signup
    @PostMapping(path="/signup")
    public @ResponseBody User signup(@RequestBody User u){
        return userService.signUp(u);
    }

    // login
    @PostMapping(path="/login")
    public @ResponseBody User login(@RequestBody User u){
        return userService.login(u.getUsername(), u.getPassword());
    }

    @PostMapping(path="/update-profile")
    public @ResponseBody boolean updateProfile(@RequestBody User u){
        return userService.updateUser(u);
    }

    // change password
    @PostMapping(path="/change-password")
    public @ResponseBody boolean changePassword(@RequestBody User u){
        User user = userService.viewUserDetails(u.getUsername());
        if (user == null) {
            System.out.println("Invalid username");
            return false;
        }

        return userService.changePassword(user, u.getPassword());
    }



    
}
