package com.hotel.hotel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotel.hotel.model.Client;
import com.hotel.hotel.model.Hotel;
import com.hotel.hotel.model.Usuari;
import com.hotel.hotel.repository.ClientsRepository;
import com.hotel.hotel.repository.HotelRepository;
import com.hotel.hotel.repository.UsuarisRepository;

@Controller
public class ClientController {

    private HotelRepository hotelRepository;
    private ClientsRepository clientRepository;
    private UsuarisRepository usuariRepository;

    public ClientController(HotelRepository hotelRepository, ClientsRepository clientRepository,
            UsuarisRepository usuariRepository) {
        this.clientRepository = clientRepository;
        this.hotelRepository = hotelRepository;
        this.usuariRepository = usuariRepository;
    }

    @GetMapping("/client/hotels")
    public String showCategories(Model model) {
        List<Hotel> hotels = hotelRepository.findAll();
        model.addAttribute("hotels", hotels);
        return "hotels/llistat";
    }

    @GetMapping("client/MostrarClient")
    public String mostrarClient(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Usuari> usuari = usuariRepository.findByEmail(username);
        Client client = (Client) usuari.get().getPersona();

        model.addAttribute("client", client);

        return "client/MostrarClient";
    }

    @PostMapping("/ModificarClient")
    public String modificarclient(@ModelAttribute Client client, @RequestParam("password") String password) {

        Optional<Client> clientActual = clientRepository.findById(client.getId());

        if (clientActual.isPresent()) {
            Client clientActualitzat = clientActual.get();

            clientActualitzat.setNom(client.getNom());
            clientActualitzat.setCognom(client.getCognom());
            clientActualitzat.setEmail(client.getEmail());
            clientActualitzat.setTelefon(client.getTelefon());

            Usuari clientUsuari = clientActualitzat.getUsuari();

            if (password != null && !password.isBlank()) {
                clientUsuari.setPassword(password);
            }

            clientUsuari.setUsername(client.getNom());
            clientUsuari.setEmail(client.getEmail());

            usuariRepository.save(clientUsuari);
            clientRepository.save(clientActualitzat);

            return "redirect:/login";
        }

        return "error";
    }

}
