package com.bookonrails.ooad.Repository;

import com.bookonrails.ooad.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);

    Admin findByUsernameAndPassword(String username, String password);
}
