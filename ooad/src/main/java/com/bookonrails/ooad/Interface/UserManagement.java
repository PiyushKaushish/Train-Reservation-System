package com.bookonrails.ooad.Interface;

import java.util.List;

import com.bookonrails.ooad.Model.User;

public interface UserManagement {
    public User addUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(Long userId);
    public User searchUser(String username);
    public User searchUser(String username, String password);
    public User login(String username, String password);
    public User signUp(User user);
    public boolean changePassword(User user, String newPassword);
    public User viewUserDetails(String username);
    public List<User> viewAllUsers();
    public User getUserByUsername(String username);
    
}
