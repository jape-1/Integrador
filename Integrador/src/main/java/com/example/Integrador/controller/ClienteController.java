package com.example.Integrador.controller;


import com.example.Integrador.model.Cliente;
import com.example.Integrador.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(
        origins = "http://127.0.0.1:5501",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // 🔹 Listar todos los clientes
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarTodos();
    }

    // 🔹 Obtener cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarPorId(id);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Crear nuevo cliente
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente nuevo = clienteService.guardar(cliente);
        return ResponseEntity.ok(nuevo);
    }

    // 🔹 Actualizar cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteActualizado) {
        return clienteService.buscarPorId(id)
                .map(c -> {
                    clienteActualizado.setIdCliente(id);
                    return ResponseEntity.ok(clienteService.guardar(clienteActualizado));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Eliminar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        if (clienteService.buscarPorId(id).isPresent()) {
            clienteService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
