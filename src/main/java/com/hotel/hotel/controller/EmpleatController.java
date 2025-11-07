package com.hotel.hotel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hotel.hotel.model.Empleat;
import com.hotel.hotel.model.Tasca;
import com.hotel.hotel.model.Usuari;
import com.hotel.hotel.repository.EmpleatsRepository;
import com.hotel.hotel.repository.TascaRepository;
import com.hotel.hotel.repository.UsuarisRepository;

@Controller
public class EmpleatController {

    private EmpleatsRepository empleatsRepository;
    private TascaRepository tascaRepository;
    private UsuarisRepository usuariRepository;

    public EmpleatController(EmpleatsRepository empleatsRepository, TascaRepository tascaRepository,
            UsuarisRepository usuariRepository) {
        this.empleatsRepository = empleatsRepository;
        this.tascaRepository = tascaRepository;
        this.usuariRepository = usuariRepository;
    }

    @GetMapping("admin/empleats")
    public String showEmpleats(Model model) {
        List<Empleat> empleats = empleatsRepository.findAll();
        List<Tasca> tasques = tascaRepository.findAll();
        model.addAttribute("empleats", empleats);
        model.addAttribute("tasques", tasques);
        return "empleats/llistat";

    }

    @GetMapping("admin/crearEmpleat")
    public String altaempleat(Model model) {
        List<Tasca> tasques = tascaRepository.findAll();
        model.addAttribute("tasques", tasques);
        model.addAttribute("empleat", new Empleat());
        return "formularis/crearEmpleat";
    }

    @PostMapping("/registrarEmpleat")
    public String registreEmpleat(Empleat empleat) {

        empleatsRepository.save(empleat);

        return "redirect:/admin/empleats";
    }

    @PostMapping("/empleats/eliminar/{id}")
    public String elminarEmpoleat(@PathVariable Long id, Model model) {
        Optional<Empleat> empleat = empleatsRepository.findById(id);
        if (empleat.isPresent()) {
            empleatsRepository.delete(empleat.get());
            return "redirect:/admin/empleats";
        }
        return "error";
    }

    @GetMapping("/empleats/modificar/{id}")
    public String mostrarModificarEmpleat(@PathVariable Long id, Model model) {
        Optional<Empleat> empleat = empleatsRepository.findById(id);
        if (empleat.isPresent()) {
            model.addAttribute("empleat", empleat.get());
            return "formularis/modificarEmpleat";
        }
        return "error";
    }

    @PostMapping("/ModificarEmpleat")
    public String ModificarEmpleat(@ModelAttribute Empleat empleat) {

        Optional<Empleat> empleatActual = empleatsRepository.findById(empleat.getId());

        if (empleatActual.isPresent()) {

            Empleat actual = empleatActual.get();

            actual.setAdreça(empleat.getAdreça());
            actual.setTelefon(empleat.getTelefon());
            actual.setEmail(empleat.getEmail());
            actual.setSalariBrut(empleat.getSalariBrut());
            actual.setNom(empleat.getNom());
            actual.setCognom(empleat.getCognom());
            actual.setLlocFeina(empleat.getLlocFeina());
            actual.setEstat(empleat.getEstat());

            empleatsRepository.save(actual);
        }

        return "redirect:admin/empleats";
    }

    @PostMapping("/empleats/AltaAdministrador/{id}")
    public String AdminEmpleat(@PathVariable Long id) {

        Optional<Empleat> empleatActual = empleatsRepository.findById(id);
        Empleat empleat = empleatActual.get();

        Usuari usuari = new Usuari();

        usuari.setEmail(empleat.getEmail());
        usuari.setActiu(true);
        usuari.setRol("ROLE_ADMIN");
        usuari.setPassword("secret");
        usuari.setUsername(empleat.getNom());

        usuari = usuariRepository.save(usuari);
        empleat.setUsuari(usuari);
        empleatsRepository.save(empleat);

        return "redirect:/admin/empleats";
    }

}
