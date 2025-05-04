package com.granja.granja_crud.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.granja.granja_crud.model.Alimentacion;

public interface AlimentacionRepository extends JpaRepository<Alimentacion, Long> {
}
