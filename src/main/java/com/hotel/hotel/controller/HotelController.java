package com.hotel.hotel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hotel.hotel.model.Habitacio;
import com.hotel.hotel.model.Hotel;
import com.hotel.hotel.repository.HotelRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class HotelController {

    private final HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @GetMapping("/")
    public String index() {
        return "principal";
    }

    @GetMapping("/hotels")
    public String showCategories(Model model) {
        List<Hotel> hotels = hotelRepository.findAll();
        model.addAttribute("hotels", hotels);
        return "hotels/llistat";
    }

    @GetMapping("/hotels/{id}")
    public String infohotel(@PathVariable Long id, Model model, HttpSession session) {

        Optional<Hotel> opt = this.hotelRepository.findById(id);

        if (opt.isPresent()) {
            session.setAttribute("id_hotel", id);
            Hotel h = opt.get();
            model.addAttribute("hotel", h);
            List<Habitacio> habitacions = h.getHabitacions();
            model.addAttribute("habitacions", habitacions);
            return "habitacions/llistat";

        }

        return "error";
    }

}
