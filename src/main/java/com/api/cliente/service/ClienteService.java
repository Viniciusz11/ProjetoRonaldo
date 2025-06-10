package com.api.cliente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.cliente.model.Cliente;
import com.api.cliente.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }
    
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }
    
    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
    
    public boolean existePorId(Long id) {
        return clienteRepository.existsById(id);
    }

    public Optional<Cliente> buscarPorEmailESenha(String email, String senha) {
        return clienteRepository.findByEmailAndSenha(email, senha);
    }
}

