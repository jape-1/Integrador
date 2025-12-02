package com.example.Integrador.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mascota")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMascota;
    private String nombre;
    private String especie;
    private String raza;
    private Integer edad;
    private Double peso;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_cliente",nullable = false)
    private Cliente propietario;


}
