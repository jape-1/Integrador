package com.example.Integrador.repository;

import com.example.Integrador.model.Cliente;
import com.example.Integrador.model.Mascota;
import com.example.Integrador.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota,Long> {

    List<Mascota> findByPropietario(Cliente propietario);

}
