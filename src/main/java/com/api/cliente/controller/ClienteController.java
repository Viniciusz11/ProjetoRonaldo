package com.api.cliente.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.cliente.dto.ClienteDTO;
import com.api.cliente.model.Cliente;
import com.api.cliente.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    
    // Listar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        List<Cliente> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }
    
    // Buscar cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarPorId(id);
        
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Cadastrar novo cliente
    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setSenha(clienteDTO.getSenha());
        
        Cliente novoCliente = clienteService.salvar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }
    
    // Atualizar cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        if (!clienteService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        
        Optional<Cliente> clienteExistente = clienteService.buscarPorId(id);
        
        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();
            cliente.setNome(clienteDTO.getNome());
            cliente.setEmail(clienteDTO.getEmail());
            cliente.setSenha(clienteDTO.getSenha());
            
            Cliente clienteSalvo = clienteService.salvar(cliente);
            return ResponseEntity.ok(clienteSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Deletar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!clienteService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint de login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody ClienteDTO clienteDTO) {
        Optional<Cliente> cliente = clienteService.buscarPorEmailESenha(clienteDTO.getEmail(), clienteDTO.getSenha());
        
        if (cliente.isPresent()) {
            return ResponseEntity.ok("Login bem-sucedido!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");
        }
    }
}

