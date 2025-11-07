package com.hotel.hotel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hotel.hotel.model.EstatHabitacio;
import com.hotel.hotel.model.Habitacio;
import com.hotel.hotel.model.Hotel;
import com.hotel.hotel.model.TipusHabitacio;

@Repository
public interface HabitacioRepository extends JpaRepository<Habitacio, Long> {

    Optional<Habitacio> findById(Long id);

    Optional<Habitacio> findByHotel(Hotel hotel);

    List<Habitacio> findByHotelId(Long idhotel);

    List<Habitacio> findByEstat(EstatHabitacio estat);

    List<Habitacio> findByHotelIdAndEstat(Long hotelId, EstatHabitacio estat);

    List<Habitacio> findByTipus(TipusHabitacio tipus);

    List<Habitacio> findByHotelIdAndTipus(Long hotelId, TipusHabitacio tipus);

    @Query("SELECT h.hotel.id, COUNT(h) FROM Habitacio h GROUP BY h.hotel.id")

    List<Habitacio> findByHotelId(int idhotel);
}
