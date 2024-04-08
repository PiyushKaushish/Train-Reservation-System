package com.bookonrails.ooad.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookonrails.ooad.Model.User;


public interface UserRepository extends JpaRepository<User, Long>{
    public User findByUsername(String username);
    public User findByEmail(String email);
    public User findByUsernameAndPassword(String username, String password);
}
