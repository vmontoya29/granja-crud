package com.granja.granja_crud.model;



import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ALIMENTACION")
@Data
public class Alimentacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRIPCION", length = 255, nullable = false)
    private String descripcion;

    @Column(name = "DOSIS", length = 100, nullable = false)
    private String dosis;
}
