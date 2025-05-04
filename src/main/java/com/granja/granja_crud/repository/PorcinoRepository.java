package com.granja.granja_crud.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.granja.granja_crud.model.Porcino;
import com.granja.granja_crud.model.Raza;

import java.util.List;

public interface PorcinoRepository extends JpaRepository<Porcino, Long> {

    
    List<Porcino> findByRaza(Raza raza);

    
    @Query("SELECT p FROM Porcino p WHERE p.cliente.cedula = :cedula")
    List<Porcino> findByCliente(@Param("cedula") String cedula);

    
    @Query("SELECT p FROM Porcino p WHERE p.alimentacion.id = :idAlimentacion")
    List<Porcino> findByAlimentacion(@Param("idAlimentacion") Long idAlimentacion);
}
