package com.granja.granja_crud.controller;

import com.granja.granja_crud.model.Alimentacion;
import com.granja.granja_crud.repository.AlimentacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/alimentacion")
public class AlimentacionController {

    @Autowired
    private AlimentacionRepository alimentacionRepository;

   //Vista Thymeleaf
    @GetMapping
    public String listarAlimentacion(Model model) {
        model.addAttribute("alimentaciones", alimentacionRepository.findAll());
        return "alimentacion";
    }

      @GetMapping("/nuevo")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("alimentacion", new Alimentacion());
        return "form_alimentacion"; 
    }
    
    @PostMapping("/guardar")
    public String guardarAlimentacion(@ModelAttribute Alimentacion alimentacion) {
        alimentacionRepository.save(alimentacion);
        return "redirect:/alimentacion";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Optional<Alimentacion> alimentacion = alimentacionRepository.findById(id);
        if (alimentacion.isPresent()) {
            model.addAttribute("alimentacion", alimentacion.get());
            return "form_alimentacion"; 
        }
        model.addAttribute("error", "No se encontró la alimentación con ID " + id);
        return "redirect:/alimentacion";
    }

   
    @PostMapping("/eliminar/{id}")
    public String eliminarAlimentacion(@PathVariable Long id) {
        if (alimentacionRepository.existsById(id)) {
            alimentacionRepository.deleteById(id);
        }
        return "redirect:/alimentacion";
    }

   
    @GetMapping("/api")
    @ResponseBody
    public List<Alimentacion> listarAlimentacionApi() {
        return alimentacionRepository.findAll();
    }

    
    @GetMapping("/api/{id}")
    @ResponseBody
    public Optional<Alimentacion> obtenerAlimentacionApi(@PathVariable Long id) {
        return alimentacionRepository.findById(id);
    }

    
    @PostMapping("/api")
    @ResponseBody
    public Alimentacion crearAlimentacionApi(@RequestBody Alimentacion alimentacion) {
        return alimentacionRepository.save(alimentacion);
    }

    
    @PutMapping("/api/{id}")
    @ResponseBody
    public Alimentacion actualizarAlimentacionApi(@PathVariable Long id, @RequestBody Alimentacion alimentacionActualizada) {
        return alimentacionRepository.findById(id).map(alimentacion -> {
            alimentacion.setDescripcion(alimentacionActualizada.getDescripcion());
            alimentacion.setDosis(alimentacionActualizada.getDosis());
            return alimentacionRepository.save(alimentacion);
        }).orElseGet(() -> {
            alimentacionActualizada.setId(id);
            return alimentacionRepository.save(alimentacionActualizada);
        });
    }

    
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void eliminarAlimentacionApi(@PathVariable Long id) {
        alimentacionRepository.deleteById(id);
    }
}
