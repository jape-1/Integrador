package com.example.Integrador.controller;

import com.example.Integrador.model.Cita;
import com.example.Integrador.model.Factura;
import com.example.Integrador.service.CitaService;
import com.example.Integrador.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private CitaService citaService;

    @GetMapping
    public List<Factura> listarFacturas() {
        return facturaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> obtenerFactura(@PathVariable Long id) {
        Optional<Factura> factura = facturaService.buscarPorId(id);
        return factura.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Factura> crearFactura(@RequestBody Factura factura) {
        // aseguramos que la cita exista
        Optional<Cita> cita = citaService.buscarPorId(factura.getCita().getIdCita());
        if (cita.isPresent()) {
            return ResponseEntity.ok(facturaService.guardar(factura));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFactura(@PathVariable Long id) {
        if (facturaService.buscarPorId(id).isPresent()) {
            facturaService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
