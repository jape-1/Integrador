package com.example.Integrador.Auth;

import com.example.Integrador.jwt.JwtService;
import com.example.Integrador.model.Role;
import com.example.Integrador.model.User;
import com.example.Integrador.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;



    public AuthResponse login(LoginRequest request) {
        return null;
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .firstname((request.getFirstname()))
                .lastname(request.lastname)
                .country(request.getCountry())
                .role(Role.USER)
                .build();


        usuarioRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
