package com.granja.granja_crud.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.granja.granja_crud.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
