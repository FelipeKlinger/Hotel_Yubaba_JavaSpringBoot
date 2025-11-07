package com.hotel.hotel.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.hotel.model.Hotel;

@SuppressWarnings("unused")
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{
    

    //Optional<Hotel> findByNomById(int idhotel);
}
