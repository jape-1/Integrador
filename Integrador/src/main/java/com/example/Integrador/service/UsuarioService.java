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

    public Optional<User> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Optional<User> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public User guardar(User usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public boolean existePorUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }
}

