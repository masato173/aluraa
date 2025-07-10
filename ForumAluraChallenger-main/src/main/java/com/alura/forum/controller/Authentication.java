package com.alura.forum.controller;

import com.alura.forum.infra.security.TokenResponseDTO;
import com.alura.forum.model.dto.request.LoginDTO;
import com.alura.forum.model.dto.request.RegisterDTO;
import com.alura.forum.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Autenticação", description = "Endpoints para autenticação e registro de usuários")
public class Authentication {

    @Autowired
    AuthenticationService service;

    @Autowired
    AuthenticationManager manager;

    @PostMapping("/login")
    @Operation(summary = "Login de usuário", description = "Autentica um usuário e retorna um token JWT para acesso à API")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid LoginDTO dados) {
        String token = service.login(dados, manager);
        return ResponseEntity.ok(new TokenResponseDTO(token));
    }

    @PostMapping("/register")
    @Operation(summary = "Registro de usuário", description = "Cadastra um novo usuário no sistema")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO dados) {
        service.register(dados);
        return ResponseEntity.ok().build();
    }


}
