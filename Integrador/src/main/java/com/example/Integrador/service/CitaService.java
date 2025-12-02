package com.example.Integrador.service;

import com.example.Integrador.model.Cita;
import com.example.Integrador.model.Cliente;
import com.example.Integrador.model.Mascota;
import com.example.Integrador.model.User;
import com.example.Integrador.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> listarTodas() {
        return citaRepository.findAll();
    }

    public Optional<Cita> buscarPorId(Long id) {
        return citaRepository.findById(id);
    }

    public List<Cita> listarPorFecha(LocalDate fecha) {
        return citaRepository.findByFecha(fecha);
    }

    public List<Cita> listarPorVeterinario(Cliente cliente) {
        return citaRepository.findByCliente(cliente);
    }

    public List<Cita> listarPorMascota(Mascota mascota) {
        return citaRepository.findByMascota(mascota);
    }

    public Cita guardar(Cita cita) {
        return citaRepository.save(cita);
    }

    public void eliminar(Long id) {
        citaRepository.deleteById(id);
    }
}
