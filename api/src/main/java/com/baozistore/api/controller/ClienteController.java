package com.baozistore.api.controller;

import com.baozistore.api.model.Cliente;
import com.baozistore.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    public Cliente criar(@RequestBody Cliente cliente) {
        return repository.save(cliente);
    }

    @GetMapping
    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> consultarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 (Sucesso, sem conteúdo)
        }
        return ResponseEntity.notFound().build(); // Retorna 404 se não achar
    }
}