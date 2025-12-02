package com.example.Integrador.controller;

import com.example.Integrador.model.Servicio;
import com.example.Integrador.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:5501")
@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public List<Servicio> listarServicios() {
        return servicioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> obtenerServicio(@PathVariable Long id) {
        Optional<Servicio> servicio = servicioService.buscarPorId(id);
        return servicio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Servicio crearServicio(@RequestBody Servicio servicio) {
        return servicioService.guardar(servicio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicio> actualizarServicio(@PathVariable Long id, @RequestBody Servicio servicio) {
        return servicioService.buscarPorId(id)
                .map(s -> {
                    servicio.setIdServicio(id);
                    return ResponseEntity.ok(servicioService.guardar(servicio));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Long id) {
        if (servicioService.buscarPorId(id).isPresent()) {
            servicioService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
