package com.example.Integrador.repository;

import com.example.Integrador.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

}
