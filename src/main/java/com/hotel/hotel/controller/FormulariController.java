package com.hotel.hotel.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotel.hotel.model.Client;
import com.hotel.hotel.model.Usuari;
import com.hotel.hotel.repository.ClientsRepository;
import com.hotel.hotel.repository.EmpleatsRepository;
import com.hotel.hotel.repository.TascaRepository;
import com.hotel.hotel.repository.UsuarisRepository;



@Controller
public class FormulariController {

    private EmpleatsRepository empleatsRepository;
    private ClientsRepository clientRepository;
    private TascaRepository tascaRepository;
    private UsuarisRepository UsuarisRepository;
    

    public FormulariController(EmpleatsRepository empleatsRepository, ClientsRepository clientRepository  ,TascaRepository tascaRepository, UsuarisRepository UsuarisRepository) {
        this.empleatsRepository = empleatsRepository;
        this.tascaRepository = tascaRepository;
        this.clientRepository = clientRepository;
        this.UsuarisRepository = UsuarisRepository;

    }






    @GetMapping ("/altaclient")
    public String altaclient(Model model) {
        List<Usuari> usuaris = UsuarisRepository.findAll(); 
        model.addAttribute("Client", new Client());
        model.addAttribute("usuaris", usuaris);
        return "formularis/altaclient";
    }

    //Cliente
    
    @PostMapping("/registrarClient")
    public String registreClient(Client client, @RequestParam("password") String password) {
        Usuari usuari = new Usuari();

        usuari.setEmail(client.getEmail());
        usuari.setActiu(true);
        usuari.setRol("ROLE_CLIENT");
        usuari.setPassword(password);
        usuari.setUsername(client.getNom());    

        usuari = UsuarisRepository.save(usuari);

        client.setUsuari(usuari);

        clientRepository.save(client);


        return "redirect:/login"; 
    }


    

    @GetMapping ("/clients/llistat")
    public String llistatClients(Model model) {
        model.addAttribute("clients", clientRepository.findAll());
        return "clients/llistat";
    }

    //Cliente






}
