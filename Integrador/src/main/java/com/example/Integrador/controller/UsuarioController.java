package com.example.Integrador.controller;

import com.example.Integrador.model.User;
import com.example.Integrador.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // 🔹 Listar todos los usuarios
    @GetMapping
    public List<User> listarUsuarios() {
        return usuarioService.listarTodos();
    }

    // 🔹 Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> obtenerUsuario(@PathVariable Integer id) {
        Optional<User> usuario = usuarioService.buscarPorId(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Obtener un usuario por username
    @GetMapping("/buscar/{username}")
    public ResponseEntity<User> obtenerPorUsername(@PathVariable String username) {
        Optional<User> usuario = usuarioService.buscarPorUsername(username);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<User> crearUsuario(@RequestBody User usuario) {
        if (usuarioService.existePorUsername(usuario.getUsername())) {
            return ResponseEntity.badRequest().build(); // username ya existe
        }
        User nuevoUsuario = usuarioService.guardar(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    // 🔹 Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<User> actualizarUsuario(@PathVariable Integer id, @RequestBody User usuarioActualizado) {
        return usuarioService.buscarPorId(id)
                .map(usuario -> {
                    usuarioActualizado.setId(id);
                    User actualizado = usuarioService.guardar(usuarioActualizado);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
        if (usuarioService.buscarPorId(id).isPresent()) {
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}