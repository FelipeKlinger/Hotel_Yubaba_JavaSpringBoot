package com.hotel.hotel.repository;


import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.hotel.hotel.model.*;


@Repository
public interface ClientsRepository extends JpaRepository<Client, Long>  {

    Optional<Client> findById(Long id);
    Client findByUsuari(Optional<Usuari> usuari);
  


  
}

