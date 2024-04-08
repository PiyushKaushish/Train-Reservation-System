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
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateUser(User user){
        if(userRepository.existsById(user.getId())){
            User u= userRepository.findById(user.getId()).get();
            if(u!=null){
                u.setEmail(user.getEmail());
                u.setFirstName(user.getFirstName());
                u.setLastName(user.getLastName());
                u.setPhoneNumber(user.getPhoneNumber());
                u.setAddress(user.getAddress());
                userRepository.save(u);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(User user){
        if(userRepository.existsById(user.getId())){
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    public User searchUser(String username){
        return userRepository.findByUsername(username);
    }

    public User searchUser(String username, String password){
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User login(String username, String password){
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User signUp(User user){
        User u = userRepository.save(user);
        return u;
    }


    public boolean changePassword(User user, String newPassword){
        if(userRepository.existsById(user.getId())){
            User u= userRepository.findById(user.getId()).get();
            if (u!=null){
                u.setPassword(newPassword);
                userRepository.save(u);
                return true;
            }
        }
        return false;
    }

    public void logout(){
    }

    public User viewUserDetails(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> viewAllUsers(){
        return userRepository.findAll();
    }

    
}
