package com.bookonrails.ooad.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookonrails.ooad.Interface.UserManagement;
import com.bookonrails.ooad.Model.*;
import com.bookonrails.ooad.Repository.UserRepository;

public class UserService implements UserManagement{
    @Autowired
    UserRepository userRepository;

    public boolean addUser(User user){
        return false;
    }

    public boolean updateUser(User user){
        return false;
    }

    public boolean deleteUser(User user){
        return false;
    }

    public User searchUser(String username){
        return new User();
    }

    public User searchUser(String username, String password){
        return new User();
    }

    public User login(String username, String password){
        return new User();
    }

    public User signUp(User user){
        return new User();
    }

    public boolean updateProfile(User user){
        return false;
    }

    public boolean changePassword(User user, String newPassword){
        return false;
    }

    public void logout(){
    }

    public User viewUserDetails(String username){
        return new User();
    }

    public List<User> viewAllUsers(){
        return null;
    }






    
}
