package com.hotel.hotel.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotel.hotel.model.Client;
import com.hotel.hotel.model.EstatHabitacio;
import com.hotel.hotel.model.Factura;
import com.hotel.hotel.model.Habitacio;
import com.hotel.hotel.model.Hotel;
import com.hotel.hotel.model.Reserva;
import com.hotel.hotel.model.TipusHabitacio;
import com.hotel.hotel.model.Valoracions;
import com.hotel.hotel.repository.ClientsRepository;
import com.hotel.hotel.repository.EmpleatsRepository;
import com.hotel.hotel.repository.FacturaRepository;
import com.hotel.hotel.repository.HabitacioRepository;
import com.hotel.hotel.repository.HotelRepository;
import com.hotel.hotel.repository.ReservaRepository;
import com.hotel.hotel.repository.TascaRepository;
import com.hotel.hotel.repository.ValoracionsRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

private  ReservaRepository reservaRepository;
private FacturaRepository facturaRepository;
private HotelRepository hotelRepository;
private HabitacioRepository habitacioRepository;
private EmpleatsRepository empleatsRepository;
private TascaRepository tascaRepository;
private ClientsRepository clientsRepository;
private ValoracionsRepository ValoracionsRepository;



public AdminController(ReservaRepository reservaRepository, FacturaRepository facturaRepository, HotelRepository hotelRepository, HabitacioRepository habitacioRepository, EmpleatsRepository empleatsRepository, TascaRepository tascaRepository, ClientsRepository clientsRepository, ValoracionsRepository valoracionsRepository) {

this.reservaRepository = reservaRepository;
this.facturaRepository = facturaRepository;
this.hotelRepository = hotelRepository;
this.habitacioRepository = habitacioRepository;
this.empleatsRepository = empleatsRepository;
this.tascaRepository = tascaRepository;
this.clientsRepository = clientsRepository;
this.ValoracionsRepository = valoracionsRepository;
}



@GetMapping ("/dashboard")
public String dashboard() {

return "admin/dashboard";
}

@GetMapping("/hotels")
public String showCategories(Model model) {
    List<Hotel> hotels = hotelRepository.findAll();
    model.addAttribute("hotels", hotels);

    return "admin/hotels/llistat"; 
}


@GetMapping("/reserves/llistat")
public String mostrarLlistatReservesAdmin(Model model) {

List<Reserva> reserves = reservaRepository.findAll();
model.addAttribute("reserves", reserves);
  
    
    return "admin/reserves";
}

@PostMapping("/reserves/eliminar/{id}")
public String eliminarReserva(@PathVariable Long id, Model Model) {


        Optional<Reserva> reserva = reservaRepository.findById(id);
        
        if (reserva.isPresent()) {
            reserva.get().getHabitacio().setEstat(EstatHabitacio.DISPONIBLE);
            reservaRepository.delete(reserva.get());
            return "redirect:/admin/reserves/llistat";
        }
    return "error";
}

@GetMapping("/reserves/factura/{id}")
public String mostrarFactura(@PathVariable Long id, Model model, HttpSession session) { 
        
    
        Optional<Reserva> reserva = reservaRepository.findById(id);

        if (reserva.isPresent()) {
            
            Optional<Factura> factura = facturaRepository.findByReservaId(reserva.get().getId());

            Reserva r = reserva.get();
            Habitacio habitacio = r.getHabitacio();
            Hotel hotel = habitacio.getHotel();
            Client client = r.getClient();

            model.addAttribute("hotel", hotel);
            model.addAttribute("nom", client.getNom());
            model.addAttribute("cognom", client.getCognom());


            if (factura.isPresent()) {
                model.addAttribute("factura", factura.get());
            } 
        } 
    return "reserves/factura";
}

@PostMapping("/reserves/confirmar/{id}")
public String ConfirmarReserva(@PathVariable Long id, Model model) {

    
    Optional<Reserva> reserva = reservaRepository.findById(id);

    Reserva reservaConfirmada = reserva.get();
    
    reservaConfirmada.getHabitacio().setEstat(EstatHabitacio.OCUPADA);
    
        reservaRepository.save(reservaConfirmada);
     
    return "redirect:/admin/reserves/llistat";

}

@GetMapping("/habitacions")
public String mostrarHabitaciones(Model model) {
   
List<Habitacio> habitacions = habitacioRepository.findAll();
List<Hotel> hotels = hotelRepository.findAll();

model.addAttribute("habitacions", habitacions);
model.addAttribute("hotels", hotels);
    return "admin/habitacions"; 

}

@PostMapping("/habitaciones/filtrar")
public String filtrarHabitaciones(@RequestParam(required = false) Long hotelId,
                                  @RequestParam(required = false) TipusHabitacio tipus,
                                  Model model) {

    List<Habitacio> habitacions;

    if (hotelId != null && tipus != null) {
        habitacions = habitacioRepository.findByHotelIdAndTipus(hotelId, tipus);
    } else if (hotelId != null) {
        habitacions = habitacioRepository.findByHotelId(hotelId);
    } else if (tipus != null) {
        habitacions = habitacioRepository.findByTipus(tipus);
    } else {
        habitacions = habitacioRepository.findAll();
    }

    model.addAttribute("habitacions", habitacions);
    model.addAttribute("hotels", hotelRepository.findAll());

    return "admin/habitacions";
}





}