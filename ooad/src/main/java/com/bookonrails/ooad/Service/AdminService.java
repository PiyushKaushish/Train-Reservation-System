package com.bookonrails.ooad.Service;

import com.bookonrails.ooad.Model.Admin;
import com.bookonrails.ooad.Repository.AdminRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin authenticate(Admin a) {
        // Implement authentication logic by checking credentials in the database
        Admin admin = adminRepository.findByUsernameAndPassword(a.getUsername(), a.getPassword());
        return admin;
    }

    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    public Admin getAdmin(Long id) {
        return adminRepository.findById(id).get();
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }


}
