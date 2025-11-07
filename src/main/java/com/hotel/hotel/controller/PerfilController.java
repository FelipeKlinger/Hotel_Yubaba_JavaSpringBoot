package com.hotel.hotel.controller;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; // Import correcto

import com.hotel.hotel.model.Usuari;
import com.hotel.hotel.repository.UsuarisRepository;

@Controller
public class PerfilController {

    private UsuarisRepository usuariRepository = null;

    public PerfilController(UsuarisRepository usuariRepository) {
        this.usuariRepository = usuariRepository;
    }


    @GetMapping("/client/home")
    
    public String perfil(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Obtenim el nom d'usuari (email en el nostre cas!)
        String username = authentication.getName();
        

        Optional<Usuari> usuari = usuariRepository.findByEmail(username);

        if (usuari.isPresent()) {
            model.addAttribute("nom", usuari.get().getPersona().getNom());
            model.addAttribute("cognom", usuari.get().getPersona().getCognom());
        } 

        return "client/home";
    }

    @GetMapping("/LamevaCompete")
public String redirectByRole() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
    if (authentication.getAuthorities().stream()
            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
        return "redirect:/admin/dashboard";
    } else if (authentication.getAuthorities().stream()
            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_CLIENT"))) {
        return "redirect:/client/home";
    } else {
        return "redirect:/login"; 
    }
}
}