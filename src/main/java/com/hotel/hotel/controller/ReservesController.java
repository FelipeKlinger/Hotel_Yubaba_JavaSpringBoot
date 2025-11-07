package com.hotel.hotel.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hotel.hotel.model.Client;
import com.hotel.hotel.model.EstatHabitacio;
import com.hotel.hotel.model.Factura;
import com.hotel.hotel.model.Habitacio;
import com.hotel.hotel.model.Hotel;
import com.hotel.hotel.model.Reserva;
import com.hotel.hotel.model.TipusReserva;
import com.hotel.hotel.model.Usuari;
import com.hotel.hotel.repository.ClientsRepository;
import com.hotel.hotel.repository.FacturaRepository;
import com.hotel.hotel.repository.HabitacioRepository;
import com.hotel.hotel.repository.HotelRepository;
import com.hotel.hotel.repository.ReservaRepository;
import com.hotel.hotel.repository.UsuarisRepository;

import jakarta.servlet.http.HttpSession;



@Controller
public class ReservesController {
    
 
private ReservaRepository reservaRepository;

    private ClientsRepository clientsRepository;
    private HabitacioRepository habitacioRepository;
    private UsuarisRepository usuariRepository;
    private HotelRepository hotelRepository;
    private FacturaRepository facturaRepository;

    public ReservesController(ReservaRepository reservaRepository, ClientsRepository clientsRepository, HabitacioRepository habitacioRepository, UsuarisRepository usuariRepository, HotelRepository hotelRepository, FacturaRepository facturaRepository) {
        this.reservaRepository = reservaRepository;
        this.clientsRepository = clientsRepository;
        this.habitacioRepository = habitacioRepository;
        this.usuariRepository = usuariRepository;
        this.hotelRepository = hotelRepository;
        this.facturaRepository = facturaRepository;
    }



 


   @GetMapping("/admin/reserves")
   public String showReserves(Model model) {
       List<Reserva> reserves = reservaRepository.findAll();
       List<Client> clients = clientsRepository.findAll();
       List<Habitacio> habitacions = habitacioRepository.findAll();
       model.addAttribute("reserves", reserves);
       model.addAttribute("clients", clients);
       model.addAttribute("habitacions", habitacions);
       return "reserves/reserves";
   }



////////////////////////////////////////Filtrar PASO 1//////////////////////////////////////////////
/// 
  
@GetMapping("/hotels/{id}/filtrar")
public String mostrarFormularioReserva(
    @PathVariable Long id, 
    @RequestParam(name = "habitacioId", required = false) Long habitacioId,
    Model model, 
    HttpSession session
) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String emailUsuario = auth.getName();
    Optional<Usuari> UsuariSesio = usuariRepository.findByEmail(emailUsuario);

    Usuari usuari = UsuariSesio.get();
    Client client = (Client) usuari.getPersona();

    Optional<Hotel> hotelopt = hotelRepository.findById(id);
    Hotel hotel = hotelopt.get();

    session.setAttribute("id_hotel", hotel.getId());
    session.setAttribute("client", client);

    Reserva reservaTemporal = new Reserva();
    reservaTemporal.setClient(client);

    // Si se recibió un id de habitación, lo precargamos
    if (habitacioId != null) {
        Optional<Habitacio> habitacioOpt = habitacioRepository.findById(habitacioId);
        habitacioOpt.ifPresent(reservaTemporal::setHabitacio);
    }

    model.addAttribute("nom", usuari.getPersona().getNom());
    model.addAttribute("cognom", usuari.getPersona().getCognom());
    model.addAttribute("hotel", hotel);
    model.addAttribute("reserva", reservaTemporal);
    model.addAttribute("client", client);

    return "reserves/filtrar";
}


@PostMapping("/FinalitzarReserva")
public String reservarhabitacio(Model model, @ModelAttribute Reserva reserva, BindingResult result, HttpSession session, @RequestParam("metode_pagament") String metode_pagament) {
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String emailUsuario = auth.getName();
    Optional<Usuari> optionalUsuari = usuariRepository.findByEmail(emailUsuario);

    reserva.setData_reserva(Date.valueOf(LocalDate.now()));

    Optional<Habitacio> habitacioOpt = habitacioRepository.findById(reserva.getHabitacio().getId());


    Habitacio habitacio = habitacioOpt.get();

    Client client = (Client) session.getAttribute("client");
    reserva.setClient(client);
    reserva.setHabitacio(habitacio);

    habitacio.setEstat(EstatHabitacio.RESERVADA);

    long diesEstada = ChronoUnit.DAYS.between(reserva.getData_inici().toLocalDate(), reserva.getData_fi().toLocalDate());

    if (diesEstada<=0) {
        model.addAttribute("error de reserva", "La data d'inici ha de ser anterior a la data de fi");
        return "error";
    }

    double preuhabitaMP = habitacio.getPreuNitMP();
    double preuhabitaAD = habitacio.getPreuNitAD();

    if (reserva.getTipus_reserva() == TipusReserva.MP) {
        double preuMP = diesEstada * preuhabitaMP ;
        double iva = preuMP * (reserva.getTipus_iva() / 100);
        double total  = preuMP + iva;
        reserva.setPreu_total_reserva(total);
    }

    if (reserva.getTipus_reserva() == TipusReserva.AD) {
        double preuAD = diesEstada * preuhabitaAD ;
        double iva = preuAD * (reserva.getTipus_iva() / 100);
        double total  = preuAD + iva;
        reserva.setPreu_total_reserva(total);
    }

    Factura factura = new Factura();

    factura.setData_emisio(reserva.getData_reserva().toLocalDate());
    factura.setMetode_pagament(metode_pagament);

    double iva = reserva.getTipus_iva();
    double total = reserva.getPreu_total_reserva();
    double baseImposable = total / (1 + iva / 100.0);

    factura.setIva(iva);
    factura.setBase_imposable(baseImposable);
    factura.setTotal(total);

    reserva.setFactura(factura);
    factura.setReserva(reserva); 

    
    reservaRepository.save(reserva);

    
    model.addAttribute("reserva", reserva);




    return "redirect:client/reserves/llistat";
}

@PostMapping("/hotels/{id}/reserva")
public String HabitacionsFiltradesperdata(@PathVariable Long id, @ModelAttribute("reserva") Reserva reservaTemporal, Model model, HttpSession session) {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String emailUsuario = auth.getName();
    Optional<Usuari> optionalUsuari = usuariRepository.findByEmail(emailUsuario);

  

    Usuari usuari = optionalUsuari.get();
    Client client = (Client) usuari.getPersona();


    reservaTemporal.setClient(client); 

    session.setAttribute("data_inici", reservaTemporal.getData_inici());
    session.setAttribute("data_fi", reservaTemporal.getData_fi());

    List<Reserva> reservasEntredatas = reservaRepository.findReservasSolapadas(id, reservaTemporal.getData_inici(), reservaTemporal.getData_fi());

       List<Long> habitacionesOcupadasIds = new ArrayList<>();
       for (Reserva r : reservasEntredatas) {
           if (r.getHabitacio() != null) {
               habitacionesOcupadasIds.add(r.getHabitacio().getId());
           }
       }

         List<Habitacio> todasHabitaciones = habitacioRepository.findByHotelId(id);


       List<Habitacio> habitacionesDisponibles = new ArrayList<>();

       for (Habitacio h : todasHabitaciones) {
           if (!habitacionesOcupadasIds.contains(h.getId())) {
               habitacionesDisponibles.add(h);
           }
   }

    model.addAttribute("habitacionsDisponibles", habitacionesDisponibles);
    model.addAttribute("client", client);
    model.addAttribute("hotel", hotelRepository.findById(id));
    model.addAttribute("reserva", reservaTemporal);
    model.addAttribute("nom", usuari.getPersona().getNom());
    model.addAttribute("cognom", usuari.getPersona().getCognom());

    return "reserves/infohabitacio";
}


//////////////////////////////////////////////////////////////////////////////

@GetMapping("/client/reserves/llistat")
public String mostrarLlistatReserves(Model model, HttpSession session) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();

    Optional<Usuari> usuari = usuariRepository.findByEmail(username);
    
    Client client = (Client) usuari.get().getPersona();

    if (usuari.isPresent()) {
        model.addAttribute("nom", usuari.get().getPersona().getNom());
        model.addAttribute("cognom", usuari.get().getPersona().getCognom());

        List<Reserva> reservesDelClient = reservaRepository.findByClientId(client.getId()); 
                model.addAttribute("reserves", reservesDelClient);

    }
    
    return "reserves/llistat";
}

//////////////////////////////////ELIMINAR RESERVA////////////////////////////////////////////

@PostMapping("/reserves/eliminar/{id}")
public String eliminarReserva(@PathVariable Long id, Model model,  RedirectAttributes redirectAttributes) {

    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    Optional<Usuari> usuari = usuariRepository.findByEmail(username);

    if (usuari.isPresent()) {
        Client client = (Client) usuari.get().getPersona();
        Optional<Reserva> reservaOpt = reservaRepository.findByIdAndClientId(id, client.getId());

        if (reservaOpt.isPresent()) {
            Reserva reserva = reservaOpt.get();
            LocalDate dataActual = LocalDate.now();
            long diesFaltants = ChronoUnit.DAYS.between(dataActual, reserva.getData_inici().toLocalDate());

            if (diesFaltants <= 0) {
                redirectAttributes.addFlashAttribute("errorReserva", "La reserva no se puede eliminar: queda 1 día o menos.");
                return "redirect:/client/reserves/llistat";
            }

            reserva.getHabitacio().setEstat(EstatHabitacio.DISPONIBLE);
            redirectAttributes.addFlashAttribute("successReserva", "Reserva eliminada correctamente.");
            reservaRepository.delete(reserva);
            
            return "redirect:/client/reserves/llistat";
        }
    }

    return "error";
}


@GetMapping("/reserves/factura/{id}")
public String mostrarFactura(@PathVariable Long id, Model model, HttpSession session) { 
        
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();

    Optional<Usuari> usuari = usuariRepository.findByEmail(username);

    if (usuari.isPresent()) {
        model.addAttribute("nom", usuari.get().getPersona().getNom());
        model.addAttribute("cognom", usuari.get().getPersona().getCognom());
        Optional<Reserva> reserva = reservaRepository.findById(id);

        if (reserva.isPresent()) {
            
            Optional<Factura> factura = facturaRepository.findByReservaId(reserva.get().getId());

            Reserva r = reserva.get();
            Habitacio habitacio = r.getHabitacio();
            Hotel hotel = habitacio.getHotel();
            model.addAttribute("hotel", hotel); 

            if (factura.isPresent()) {
                model.addAttribute("factura", factura.get());
            } 
        } 
        return "reserves/factura";
    }

    return "redirect:/login";
}

@GetMapping("/admin/reserves/modificar/{id}")
public String mostrarFormularioModificarReserva(@PathVariable Long id, Model model) {
    Optional<Reserva> reservaOptional = reservaRepository.findById(id);
    if (reservaOptional.isPresent()) {
        Reserva reserva = reservaOptional.get();
        model.addAttribute("reserva", reserva);
        model.addAttribute("tipusReserves", TipusReserva.values()); 
        return "admin/modificar"; // Nombre de la vista (formulario)
    } else {
        model.addAttribute("error", "No se encontró la reserva con ID: " + id);
        return "error"; 
    }
}

@PostMapping("/admin/reserves/guardarModificacio")
public String guardarModificacionReserva(
        @ModelAttribute("reserva") Reserva reservaModificada,
        BindingResult result,
        Model model,
        RedirectAttributes redirectAttributes 
) {
    if (result.hasErrors()) {
        model.addAttribute("tipusReserves", TipusReserva.values());
        return "reserves/modificar";
    }

    Optional<Reserva> reservaOriginalOptional = reservaRepository.findById(reservaModificada.getId());

    if (reservaOriginalOptional.isPresent()) {
        Reserva reservaOriginal = reservaOriginalOptional.get();
        Habitacio habitacioOriginal = reservaOriginal.getHabitacio(); 

    
        if (reservaModificada.getData_inici().after(reservaModificada.getData_fi())) {
            model.addAttribute("error", "La fecha de inicio debe ser anterior a la fecha de fin.");
            model.addAttribute("tipusReserves", TipusReserva.values());
            return "reserves/modificar";
        }

    
        List<Reserva> reservasSolapadas = reservaRepository.findReservasSolapadas( habitacioOriginal.getHotel().getId(), reservaModificada.getData_inici(),reservaModificada.getData_fi());

      
        boolean habitacionDisponible = reservasSolapadas.stream().noneMatch(reserva -> !reserva.getId().equals(reservaModificada.getId()) && reserva.getHabitacio().getId().equals(habitacioOriginal.getId()));

        if (!habitacionDisponible) {
            model.addAttribute("error", "La habitación no está disponible para las fechas seleccionadas.");
            model.addAttribute("tipusReserves", TipusReserva.values());
            return "reserves/modificar";
        }
       
        reservaOriginal.setData_inici(reservaModificada.getData_inici());
        reservaOriginal.setData_fi(reservaModificada.getData_fi());
        reservaOriginal.setTipus_reserva(reservaModificada.getTipus_reserva());
        reservaOriginal.setTipus_iva(reservaModificada.getTipus_iva());

      
        long diesEstada = ChronoUnit.DAYS.between(reservaOriginal.getData_inici().toLocalDate(), reservaOriginal.getData_fi().toLocalDate());

        if (reservaOriginal.getTipus_reserva() == TipusReserva.MP) {
            double preuMP = diesEstada * habitacioOriginal.getPreuNitMP();
            double iva = preuMP * (reservaOriginal.getTipus_iva() / 100.0);
            reservaOriginal.setPreu_total_reserva(preuMP + iva);
        } else if (reservaOriginal.getTipus_reserva() == TipusReserva.AD) {
            double preuAD = diesEstada * habitacioOriginal.getPreuNitAD();
            double iva = preuAD * (reservaOriginal.getTipus_iva() / 100.0);
            reservaOriginal.setPreu_total_reserva(preuAD + iva);
        }

       reservaRepository.save(reservaOriginal);
        redirectAttributes.addFlashAttribute("missatge", "Reserva modificada amb èxit."); 

        return "redirect:/admin/reserves/llistat";
    } else {
        redirectAttributes.addFlashAttribute("error", "No s'ha trobat la reserva amb ID: " + reservaModificada.getId()); 
        return "redirect:/admin/reserves/llistat"; 
    }
}





   







  
}

