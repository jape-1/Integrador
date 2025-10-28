package com.example.Integrador.Auth;

import com.example.Integrador.model.User;
import com.example.Integrador.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public AuthResponse login(LoginRequest request) {
        return null;
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .nombre(request.getFirstname())
                .apellido(request.getLastname())
                .build();

        return  AuthResponse.builder()
                .token(null)
                .build();
    }
}
