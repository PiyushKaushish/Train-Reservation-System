package com.bookonrails.ooad.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookonrails.ooad.Model.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    
}
