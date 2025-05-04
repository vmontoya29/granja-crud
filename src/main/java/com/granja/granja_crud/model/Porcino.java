package com.granja.granja_crud.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PORCINO")
@Data
public class Porcino {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "RAZA", nullable = false)
    private Raza raza; 

    @Column(name = "EDAD", nullable = false)
    private int edad;

    @Column(name = "PESO", precision = 10,  nullable = false)
    private double peso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENTE_ID", referencedColumnName = "CEDULA", nullable = false)
    private Cliente cliente;


    @ManyToOne(fetch = FetchType.EAGER)  
    @JoinColumn(name = "ALIMENTACION_ID", referencedColumnName = "ID", nullable = false)
    private Alimentacion alimentacion;

}
