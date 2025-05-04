package com.granja.granja_crud.controller;

import com.granja.granja_crud.model.Cliente;
import com.granja.granja_crud.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    
    //Vistas con Thymeleaf       
    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        return "clientes"; 
    }

    
    @GetMapping("/nuevo")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "form_cliente"; 
    }

   
    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{cedula}")
    public String mostrarFormularioEdicion(@PathVariable String cedula, Model model) {
        Optional<Cliente> cliente = clienteRepository.findById(cedula);
        if (cliente.isPresent()) {
            model.addAttribute("cliente", cliente.get());
            return "form_cliente";
        }
        return "redirect:/clientes";
    }

        @GetMapping("/eliminar/{cedula}")
    public String eliminarCliente(@PathVariable String cedula) {
        clienteRepository.deleteById(cedula);
        return "redirect:/clientes";
    }

    
    // API REST 
    @ResponseBody
    @GetMapping("/api")
    public List<Cliente> listarClientesApi() {
        return clienteRepository.findAll();
    }
    
    @ResponseBody
    @GetMapping("/api/{cedula}")
    public Optional<Cliente> obtenerClienteApi(@PathVariable String cedula) {
        return clienteRepository.findById(cedula);
    }
    
    @ResponseBody
    @PostMapping("/api")
    public Cliente crearClienteApi(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    @ResponseBody
    @PutMapping("/api/{cedula}")
    public Cliente actualizarClienteApi(@PathVariable String cedula, @RequestBody Cliente clienteActualizado) {
        return clienteRepository.findById(cedula).map(cliente -> {
            cliente.setNombres(clienteActualizado.getNombres());
            cliente.setApellidos(clienteActualizado.getApellidos());
            cliente.setDireccion(clienteActualizado.getDireccion());
            cliente.setTelefono(clienteActualizado.getTelefono());
            return clienteRepository.save(cliente);
        }).orElseGet(() -> {
            clienteActualizado.setCedula(cedula);
            return clienteRepository.save(clienteActualizado);
        });
    }

    @ResponseBody
    @DeleteMapping("/api/{cedula}")
    public void eliminarClienteApi(@PathVariable String cedula) {
        clienteRepository.deleteById(cedula);
    }
}
