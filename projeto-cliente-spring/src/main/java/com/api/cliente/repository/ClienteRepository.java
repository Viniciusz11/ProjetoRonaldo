package com.api.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.cliente.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
