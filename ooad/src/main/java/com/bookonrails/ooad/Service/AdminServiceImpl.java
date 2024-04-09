// AdminServiceImpl.java

package com.bookonrails.ooad.Service;

import com.bookonrails.ooad.Interface.UserManagement;
import com.bookonrails.ooad.Model.User;
import com.bookonrails.ooad.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements UserManagement {

    private final UserRepository userRepository;

    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        // Implement logic to add a user
        return userRepository.save(user);
    }

    // Implement other methods of UserManagement interface similarly

    @Override
    public User viewUserDetails(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> viewAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean updateUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }


    @Override
    public boolean deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return false;
    }

    @Override
    public User searchUser(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchUser'");
    }

    @Override
    public User searchUser(String username, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchUser'");
    }

    @Override
    public User login(String username, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public User signUp(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signUp'");
    }

    @Override
    public boolean changePassword(User user, String newPassword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changePassword'");
    }

    // Implement other methods as needed
}
