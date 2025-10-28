package com.example.Integrador.service;


import com.example.Integrador.model.User;
import com.example.Integrador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<User> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<User> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<User> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public User guardar(User usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    public boolean existePorEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}

