package com.bookonrails.ooad.Service;

import com.bookonrails.ooad.Model.Admin;
import com.bookonrails.ooad.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthenticationServiceImpl implements AdminAuthenticationService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public boolean authenticate(String username, String password) {
        // Implement authentication logic by checking credentials in the database
        Admin admin = adminRepository.findByUsername(username);
        return admin != null && admin.getPassword().equals(password);
    }
}