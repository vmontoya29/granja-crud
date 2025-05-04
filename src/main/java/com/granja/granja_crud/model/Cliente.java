package com.granja.granja_crud.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CLIENTE")
@Data
public class Cliente {
    
    @Id
    @Column(name = "CEDULA", length = 20)
    private String cedula;

    @Column(name = "NOMBRES", length = 100, nullable = false)
    private String nombres;

    @Column(name = "APELLIDOS", length = 100, nullable = false)
    private String apellidos;

    @Column(name = "DIRECCION", length = 255)
    private String direccion;

    @Column(name = "TELEFONO", length = 20)
    private String telefono;
}
