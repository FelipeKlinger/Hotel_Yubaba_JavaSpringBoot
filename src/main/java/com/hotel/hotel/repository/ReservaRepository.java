package com.hotel.hotel.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hotel.hotel.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    Optional<Reserva> findById(Long id);

    List<Reserva> findByClientId(Long clientId);

    Optional<Reserva> findByIdAndClientId(Long id, Long clientId);

    @Query("SELECT r FROM Reserva r WHERE r.habitacio.hotel.id = :hotelId AND "
            + "(:dataInici <= r.data_fi AND :dataFi >= r.data_inici)")
    List<Reserva> findReservasSolapadas(@Param("hotelId") Long hotelId, @Param("dataInici") Date dataInici,
            @Param("dataFi") Date dataFi);

}
