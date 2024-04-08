package com.bookonrails.ooad.Interface;

import com.bookonrails.ooad.Model.User;

public interface UserManagement {
    public boolean addUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(User user);
    public boolean searchUser(User user);
    public boolean searchUser(String email);
    public boolean searchUser(String username, String password);
    public boolean login(String username, String password);
    public boolean signUp(User user);
    public boolean updateProfile(User user);
    public boolean changePassword(User user);
    public void logout();
}
