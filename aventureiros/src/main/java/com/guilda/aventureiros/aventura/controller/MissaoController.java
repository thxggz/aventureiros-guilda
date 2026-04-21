package com.guilda.aventureiros.aventura.controller;

import com.guilda.aventureiros.aventura.entity.Missao;
import com.guilda.aventureiros.aventura.entity.NivelPerigo;
import com.guilda.aventureiros.aventura.entity.StatusMissao;
import com.guilda.aventureiros.aventura.repository.MissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/missoes-aventura")
public class MissaoController {

    @Autowired
    private MissaoRepository repository;

    @GetMapping
    public ResponseEntity<List<Missao>> listar(
            @RequestParam(required = false) StatusMissao status,
            @RequestParam(required = false) NivelPerigo nivelPerigo,
            @RequestParam(required = false) String inicio,
            @RequestParam(required = false) String fim,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "titulo") String sort,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction dir = direction.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        LocalDateTime dataInicio = inicio != null
                ? LocalDateTime.parse(inicio)
                : LocalDateTime.of(2000, 1, 1, 0, 0);

        LocalDateTime dataFim = fim != null
                ? LocalDateTime.parse(fim)
                : LocalDateTime.of(2100, 12, 31, 23, 59, 59);

        return ResponseEntity.ok(
                repository.buscarComFiltros(
                        status,
                        nivelPerigo,
                        dataInicio,
                        dataFim,
                        PageRequest.of(page, size, Sort.by(dir, sort))
                ).getContent()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Missao> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}