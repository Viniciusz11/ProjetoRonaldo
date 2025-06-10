package com.api.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.api.cliente.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Métodos personalizados podem ser adicionados aqui se necessário
    Optional<Cliente> findByEmailAndSenha(String email, String senha);
}

