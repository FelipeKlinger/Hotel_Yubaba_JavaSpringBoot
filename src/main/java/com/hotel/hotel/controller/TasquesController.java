package com.hotel.hotel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotel.hotel.model.Empleat;
import com.hotel.hotel.model.EstatTasca;
import com.hotel.hotel.model.Tasca;
import com.hotel.hotel.repository.EmpleatsRepository;
import com.hotel.hotel.repository.TascaRepository;

@Controller
public class TasquesController {
    
    private EmpleatsRepository empleatsRepository;
    private TascaRepository tascaRepository;


    public TasquesController(EmpleatsRepository empleatsRepository, TascaRepository tascaRepository) {
        this.empleatsRepository = empleatsRepository;
        this.tascaRepository = tascaRepository;
    }

    @GetMapping("admin/tasques")
    public String showTasques(Model model) {
    
    List<Empleat> empleats = empleatsRepository.findAll();
    List<Tasca> tasques = tascaRepository.findAll();
    model.addAttribute("empleats", empleats);  
    model.addAttribute("tasques", tasques);
    return "empleats/tasques";


}

    @GetMapping("admin/tasques/{id}")
    public String showTasquesPerId(Model model, @PathVariable Long id) {
    
    List<Empleat> empleats = empleatsRepository.findAll();
    Optional <Tasca> tasques = tascaRepository.findById(id);

    model.addAttribute("empleats", empleats);  
    model.addAttribute("tasques", tasques);
    return "empleats/tasques";


}

@PostMapping("/assignar")
public String assignarTasca(@RequestParam Long tascaId, @RequestParam Long empleatId) {

    Tasca tasca = tascaRepository.findById(tascaId).orElse(null);
    Empleat empleat = empleatsRepository.findById(empleatId).orElse(null);

        empleat.getTasques().add(tasca);
        tasca.getEmpleats().add(empleat);
        empleatsRepository.save(empleat);
        tascaRepository.save(tasca);
    

    return "redirect:/admin/tasques";
}


   @PostMapping("tasques/canviarEstat")
public String assignTasques(@RequestParam("tascaId") Long tascaId,
                            @RequestParam("estat") EstatTasca estat) {

    Optional<Tasca> tasca = tascaRepository.findById(tascaId);
    
    Tasca tascaNueva = tasca.get();
    
    if (tascaNueva != null) {
        tascaNueva.setEstat(estat);
        tascaRepository.save(tascaNueva);
    }

    return "redirect:/admin/tasques";
}

@GetMapping("admin/crearTasques")
public String altaTasques(Model model) {
    model.addAttribute("tasca", new Tasca());
    return "formularis/CrearTasques";
}


@PostMapping("crearTasques")
public String altaTasques(Tasca tasca) {
    
    tascaRepository.save(tasca);
    return "redirect:/admin/tasques";

}


@PostMapping("/tasques/eliminar")
public String eliminarTasca(@RequestParam("tascaId") Long tascaId) {
    Optional<Tasca> tasca = tascaRepository.findById(tascaId);
    if (tasca.isPresent()) {
        tascaRepository.delete(tasca.get());
        return "redirect:/admin/tasques";
    }
    return "error";
}





    

}
