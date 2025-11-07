package com.hotel.hotel.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hotel.hotel.repository.*;
import com.hotel.hotel.model.*;

@Controller
public class ValoracionsController {

    private ClientsRepository clientsRepository;
    private HabitacioRepository habitacioRepository;
    private UsuarisRepository usuariRepository;
    private HotelRepository hotelRepository;
    private FacturaRepository facturaRepository;
    private ValoracionsRepository ValoracionsRepository;
    private ReservaRepository reservaRepository;

    public ValoracionsController(ClientsRepository clientsRepository, HabitacioRepository habitacioRepository,
            UsuarisRepository usuariRepository, HotelRepository hotelRepository, FacturaRepository facturaRepository,
            com.hotel.hotel.repository.ValoracionsRepository valoracionsRepository,
            ReservaRepository reservaRepository) {
        this.clientsRepository = clientsRepository;
        this.habitacioRepository = habitacioRepository;
        this.usuariRepository = usuariRepository;
        this.hotelRepository = hotelRepository;
        this.facturaRepository = facturaRepository;
        ValoracionsRepository = valoracionsRepository;
        this.reservaRepository = reservaRepository;

    }

    @GetMapping("/client/valoracion/{id}")
    public String mostrarFormulariValoracio(@PathVariable Long id, Model model) {

        Optional<Reserva> reserva = reservaRepository.findById(id);

        Valoracions valoracio = new Valoracions();

        valoracio.setReserva(reservaRepository.findById(id).get());
        model.addAttribute("valoracio", valoracio);
        return "formularis/valoracions";
    }

    @PostMapping("/valoracions/guardar")
    public String guardarValoracio(@ModelAttribute Valoracions valoracio, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Usuari> usuari = usuariRepository.findByEmail(username);
        Client client = (Client) usuari.get().getPersona();

        Optional<Reserva> reserva = reservaRepository.findByIdAndClientId(valoracio.getReserva().getId(),
                client.getId());

        Reserva reserva2 = reserva.get();
        Habitacio habitacio = reserva2.getHabitacio();

        if (reserva2.getHabitacio().getEstat().equals(EstatHabitacio.OCUPADA)) { // Ramon como no tengo otra forma de
                                                                                 // que lo verifique el ADMIN por el
                                                                                 // estado de la habitacion cuando se
                                                                                 // presente el cliente de resto no

            model.addAttribute("client", client);

            valoracio.setData_creacio(LocalDate.now());
            valoracio.setEmail(username);
            ValoracionsRepository.save(valoracio);

            return "redirect:/client/reserves/llistat";
        }

        else {

            return "error";
        }

    }

    @GetMapping("/admin/valoracion")
    public String mostrarValoracionsAdmin(Model model) {

        List<Valoracions> valoracions = ValoracionsRepository.findAll();

        model.addAttribute("valoracio", valoracions);
        return "reserves/valoracions";
    }

    @PostMapping("/canviEstat/{id}")
    public String llegit(@PathVariable Long id, Model model) {

        Optional<Valoracions> valo = ValoracionsRepository.findById(id);

        Valoracions valoracion1 = valo.get();

        valoracion1.setLlegit(true);

        ValoracionsRepository.save(valoracion1);

        return "redirect:/admin/reserves/llistat";

    }

}
