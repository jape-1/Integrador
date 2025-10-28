package com.example.Integrador.repository;

import com.example.Integrador.model.Cita;
import com.example.Integrador.model.Mascota;
import com.example.Integrador.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByFecha(LocalDate fecha);
    List<Cita> findByVeterinario(User veterinario);
    List<Cita> findByMascota(Mascota mascota);
}
