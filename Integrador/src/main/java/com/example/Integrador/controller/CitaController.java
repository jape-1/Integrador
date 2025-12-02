package com.example.Integrador.controller;
import com.example.Integrador.model.Cita;
import com.example.Integrador.service.CitaService;
import com.example.Integrador.service.ClienteService;
import com.example.Integrador.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:5501")
@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private ClienteService usuarioService;

    @Autowired
    private MascotaService mascotaService;

    @GetMapping
    public List<Cita> listarCitas() {
        return citaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerCita(@PathVariable Long id) {
        Optional<Cita> cita = citaService.buscarPorId(id);
        return cita.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/fecha/{fecha}")
    public List<Cita> listarCitasPorFecha(@PathVariable String fecha) {
        return citaService.listarPorFecha(LocalDate.parse(fecha));
    }

    @PostMapping
    public Cita crearCita(@RequestBody Cita cita) {
        return citaService.guardar(cita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizarCita(@PathVariable Long id, @RequestBody Cita cita) {
        return citaService.buscarPorId(id)
                .map(c -> {
                    cita.setIdCita(id);
                    return ResponseEntity.ok(citaService.guardar(cita));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCita(@PathVariable Long id) {
        if (citaService.buscarPorId(id).isPresent()) {
            citaService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
