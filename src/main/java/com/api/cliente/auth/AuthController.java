package com.api.cliente.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestBody String credentials) {
        // O Spring Security lida com a autenticação. Este método pode retornar um token JWT ou uma mensagem de sucesso.
        return "Login bem-sucedido!";
    }
}


