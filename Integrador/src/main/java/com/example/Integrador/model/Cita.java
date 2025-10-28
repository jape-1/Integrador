package com.example.Integrador.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "cita")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCita;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime hora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Estado estado;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    // Relación con Mascota
    @ManyToOne
    @JoinColumn(name = "id_mascota", nullable = false)
    private Mascota mascota;

    // Relación con Veterinario (Usuario)
    @ManyToOne
    @JoinColumn(name = "id_veterinario", nullable = false)
    private User veterinario;

    // Relación con Servicio
    @ManyToOne
    @JoinColumn(name = "id_servicio", nullable = false)
    private Servicio servicio;

    public enum Estado {
        PENDIENTE,
        COMPLETADA,
        CANCELADA
    }

}