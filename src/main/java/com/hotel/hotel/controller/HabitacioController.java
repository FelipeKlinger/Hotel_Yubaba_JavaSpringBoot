package com.hotel.hotel.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hotel.hotel.model.Habitacio;
import com.hotel.hotel.repository.HabitacioRepository;



@Controller
public class HabitacioController {


   private final HabitacioRepository habitacioRepository;


   public HabitacioController(HabitacioRepository habitacioRepository) {
       this.habitacioRepository = habitacioRepository;
   }


   @GetMapping("/habitacions")
   public String mostarhabitacions(Model model) {
       List<Habitacio> habitacions = habitacioRepository.findAll();
       model.addAttribute("habitacions", habitacions);
       return "habitacions/llistat";
   }


   @GetMapping("/habitacions/{idhotel}")
   public String mostarhabitacions2(@PathVariable int idhotel, Model model) {
       List<Habitacio> habitacions = habitacioRepository.findByHotelId(idhotel);
       model.addAttribute("habitacions", habitacions);
       return "habitacions/llistat";
   }




}
