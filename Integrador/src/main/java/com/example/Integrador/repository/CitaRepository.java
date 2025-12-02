package com.example.Integrador.repository;

import com.example.Integrador.model.Cita;
import com.example.Integrador.model.Cliente;
import com.example.Integrador.model.Mascota;
import com.example.Integrador.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByFecha(LocalDate fecha);
    List<Cita> findByCliente(Cliente cliente);
    List<Cita> findByMascota(Mascota mascota);
}
