package com.guilda.aventureiros.aventura.controller;

import com.guilda.aventureiros.aventura.entity.Missao;
import com.guilda.aventureiros.aventura.repository.MissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes-aventura")
public class MissaoController {

    @Autowired
    private MissaoRepository repository;

    @GetMapping
    public ResponseEntity<List<Missao>> listar(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String nivelPerigo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "titulo") String sort,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction dir = direction.equalsIgnoreCase("desc") ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        return ResponseEntity.ok(
                repository.buscarComFiltros(status, nivelPerigo,
                        PageRequest.of(page, size, Sort.by(dir, sort))).getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Missao> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}