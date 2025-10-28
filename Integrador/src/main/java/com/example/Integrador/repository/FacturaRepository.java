package com.example.Integrador.repository;

import com.example.Integrador.model.Cita;
import com.example.Integrador.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacturaRepository extends JpaRepository<Factura,Long> {
    Optional<Factura> findByCita(Cita cita);
}
