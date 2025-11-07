package com.hotel.hotel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.hotel.model.*;


public interface UsuarisRepository extends JpaRepository<Usuari, Long>{

    Optional<Usuari> findByEmail(String email);
    Optional<Usuari> findByUsername(String username);

    

    
    
}
