package com.guilda.aventureiros.aventura.controller;

import com.guilda.aventureiros.aventura.entity.Aventureiro;
import com.guilda.aventureiros.aventura.repository.AventureiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aventureiros")
public class AventureiroController {

    @Autowired
    private AventureiroRepository repository;

    @GetMapping
    public ResponseEntity<List<Aventureiro>> listar(
            @RequestParam(required = false) Boolean ativo,
            @RequestParam(required = false) String classe,
            @RequestParam(required = false) Integer nivelMin,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sort,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction dir = direction.equalsIgnoreCase("desc") ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        return ResponseEntity.ok(
                repository.buscarComFiltros(ativo, classe, nivelMin,
                        PageRequest.of(page, size, Sort.by(dir, sort))).getContent());
    }

    @GetMapping("/busca")
    public ResponseEntity<List<Aventureiro>> buscarPorNome(
            @RequestParam String nome,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(
                repository.findByNomeContainingIgnoreCase(nome,
                        PageRequest.of(page, size)).getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aventureiro> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}