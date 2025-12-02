package com.example.Integrador.controller;

import com.example.Integrador.model.Cliente;
import com.example.Integrador.model.Mascota;
import com.example.Integrador.model.User;
import com.example.Integrador.service.ClienteService;
import com.example.Integrador.service.MascotaService;
import com.example.Integrador.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:5501")
@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private ClienteService usuarioService;

    @GetMapping
    public List<Mascota> listarMascotas() {
        return mascotaService.listarTodas();
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Mascota>> listarMascotasPorCliente(@PathVariable Long idCliente) {
        Optional<Cliente> cliente = usuarioService.buscarPorId(idCliente);
        return cliente.map(c -> ResponseEntity.ok(mascotaService.listarPorPropietario(c)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mascota crearMascota(@RequestBody Mascota mascota) {
        return mascotaService.guardar(mascota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascota> actualizarMascota(@PathVariable Long id, @RequestBody Mascota mascota) {
        return mascotaService.buscarPorId(id)
                .map(m -> {
                    mascota.setIdMascota(id);
                    return ResponseEntity.ok(mascotaService.guardar(mascota));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        if (mascotaService.buscarPorId(id).isPresent()) {
            mascotaService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
