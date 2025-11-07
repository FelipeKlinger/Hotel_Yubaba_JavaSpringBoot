package com.hotel.hotel.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hotel.hotel.model.Categoria;
import com.hotel.hotel.repository.CategoriaRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/categories")
    public String showCategories(Model model) {
        List<Categoria> categories = categoriaRepository.findAll();
        model.addAttribute("categories", categories);
        return "categoria/llistat";
    }

    @GetMapping("/desar-categoria")
    public String desar() {
        Categoria c = new Categoria();
        c.setNom("Categoria nova");
        categoriaRepository.save(c);
        return "redirect:/categories";
    }

    @GetMapping("/crear-cookie")
    public String setCookie(HttpServletResponse response) {
        // Crear i afegir una cookie
        Cookie cookie = new Cookie("username", "prova");
        cookie.setMaxAge(60 * 60 * 24); // Vàlida 1 dia
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "principal";
    }

    @GetMapping("/recuperar-cookie")
    public String home(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies[0].getValue());

        return "principal";
    }

}
