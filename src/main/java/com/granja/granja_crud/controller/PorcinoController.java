package com.granja.granja_crud.controller;

import com.granja.granja_crud.model.Porcino;
import com.granja.granja_crud.repository.PorcinoRepository;
import com.granja.granja_crud.repository.ClienteRepository;
import com.granja.granja_crud.repository.AlimentacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/porcinos") 
public class PorcinoController {

    @Autowired
    private PorcinoRepository porcinoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AlimentacionRepository alimentacionRepository;

    
    @GetMapping
    public String listarPorcinos(Model model) {
        model.addAttribute("porcinos", porcinoRepository.findAll());  
        return "porcinos";  
    }

    
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("porcino", new Porcino());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("alimentaciones", alimentacionRepository.findAll());
        return "form_porcinos";  
    }

    
    @PostMapping("/guardar")
    public String guardarPorcino(@ModelAttribute Porcino porcino) {
        porcinoRepository.save(porcino);
        return "redirect:/porcinos";
    }

   
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Optional<Porcino> porcino = porcinoRepository.findById(id);
        if (porcino.isPresent()) {
            model.addAttribute("porcino", porcino.get());
            model.addAttribute("clientes", clienteRepository.findAll());
            model.addAttribute("alimentaciones", alimentacionRepository.findAll());
            return "form_porcinos";  
        }
        return "redirect:/porcinos";
    }

    
    @GetMapping("/eliminar/{id}")
    public String eliminarPorcino(@PathVariable Long id) {
        porcinoRepository.deleteById(id);
        return "redirect:/porcinos";
    }

    
    @ResponseBody
    @GetMapping("/api")
    public List<Porcino> obtenerPorcinosApi() {
        return porcinoRepository.findAll();
    }

    
    @ResponseBody
    @GetMapping("/api/{id}")
    public Optional<Porcino> obtenerPorcinoApi(@PathVariable Long id) {
        return porcinoRepository.findById(id);
    }

    
    @ResponseBody
    @PostMapping("/api")
    public Porcino crearPorcinoApi(@RequestBody Porcino porcino) {
        return porcinoRepository.save(porcino);
    }

    
    @ResponseBody
    @PutMapping("/api/{id}")
    public Porcino actualizarPorcinoApi(@PathVariable Long id, @RequestBody Porcino porcinoActualizado) {
        return porcinoRepository.findById(id).map(porcino -> {
            porcino.setRaza(porcinoActualizado.getRaza());
            porcino.setEdad(porcinoActualizado.getEdad());
            porcino.setPeso(porcinoActualizado.getPeso());
            porcino.setCliente(porcinoActualizado.getCliente());
            porcino.setAlimentacion(porcinoActualizado.getAlimentacion());
            return porcinoRepository.save(porcino);
        }).orElseGet(() -> {
            porcinoActualizado.setId(id);
            return porcinoRepository.save(porcinoActualizado);
        });
    }

    
    @ResponseBody
    @DeleteMapping("/api/{id}")
    public void eliminarPorcinoApi(@PathVariable Long id) {
        porcinoRepository.deleteById(id);
    }

    
    @GetMapping("/reporte")
    public String reportePorcinosClientes(Model model) {
        model.addAttribute("porcinos", porcinoRepository.findAll());
        model.addAttribute("clientes", clienteRepository.findAll());
        return "reporte_porcinos_clientes";  
    }

   
    @ResponseBody
    @GetMapping("/api/reporte")
    public List<Porcino> reportePorcinosClientesApi() {
        return porcinoRepository.findAll();
    }
}
