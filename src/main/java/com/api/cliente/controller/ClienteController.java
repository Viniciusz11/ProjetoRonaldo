package com.api.cliente.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.cliente.dto.ClienteDTO;
import com.api.cliente.model.Cliente;
import com.api.cliente.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Listar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    // Buscar cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
        return clienteService.atualizarCliente(id, clienteDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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

    // Endpoint para login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ClienteDTO clienteDTO) {
        Optional<Cliente> clienteOpt = clienteService.buscarPorEmail(clienteDTO.getEmail());
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            if (cliente.getSenha().equals(clienteDTO.getSenha())) {
                return ResponseEntity.ok(cliente);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
    }
}
