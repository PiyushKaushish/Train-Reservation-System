package com.bookonrails.ooad.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookonrails.ooad.Interface.UserManagement;
import com.bookonrails.ooad.Model.*;
import com.bookonrails.ooad.Repository.UserRepository;

@Service
public class UserService implements UserManagement {
    @Autowired
    UserRepository userRepository;

    public User addUser(User user) {
        try {
            User u = userRepository.save(user);
            return u;
        } catch (Exception e) {
            System.out.println("500: Internal Server Error");
            System.out.println("Error while adding user: " + e.getMessage());
            return null;
        }
    }

    public boolean updateUser(User user) {
        if (userRepository.existsById(user.getId())) {
            User u = userRepository.findById(user.getId()).get();
            if (u != null) {
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

    public boolean deleteUser(User user) {
        if (userRepository.existsById(user.getId())) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    public User searchUser(String username) {
        return userRepository.findByUsername(username);
    }

    public User searchUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User signUp(User user) {
        User u = addUser(user);
        return u;
    }

    public boolean changePassword(User user, String newPassword) {
        if (userRepository.existsById(user.getId())) {
            User u = userRepository.findById(user.getId()).get();
            if (u != null) {
                u.setPassword(newPassword);
                userRepository.save(u);
                return true;
            }
        }
        return false;
    }

    public User viewUserDetails(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> viewAllUsers() {
        return userRepository.findAll();
    }

    public boolean deleteUser(Long userId) {
        User u = userRepository.findById(userId).get();
        if (u != null) {
            userRepository.delete(u);
            return true;
        } else {
            return false;
        }
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

}
