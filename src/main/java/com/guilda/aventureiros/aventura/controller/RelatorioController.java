package com.guilda.aventureiros.aventura.controller;

import com.guilda.aventureiros.aventura.entity.StatusMissao;
import com.guilda.aventureiros.aventura.repository.MissaoRepository;
import com.guilda.aventureiros.aventura.repository.ParticipacaoMissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private MissaoRepository missaoRepository;

    @Autowired
    private ParticipacaoMissaoRepository participacaoRepository;

    @GetMapping("/missoes")
    public ResponseEntity<List<Object[]>> relatorioMissoes(
            @RequestParam(defaultValue = "2024-01-01T00:00:00") String inicio,
            @RequestParam(defaultValue = "2026-12-31T23:59:59") String fim) {

        LocalDateTime dataInicio = LocalDateTime.parse(inicio);
        LocalDateTime dataFim = LocalDateTime.parse(fim);

        return ResponseEntity.ok(
                missaoRepository.relatorioMissoesComMetricas(dataInicio, dataFim)
        );
    }

    @GetMapping("/ranking-participacao")
    public ResponseEntity<List<Object[]>> rankingParticipacao(
            @RequestParam(required = false) StatusMissao status,
            @RequestParam(required = false) String inicio,
            @RequestParam(required = false) String fim) {

        LocalDateTime dataInicio = inicio != null
                ? LocalDateTime.parse(inicio)
                : LocalDateTime.of(2000, 1, 1, 0, 0);

        LocalDateTime dataFim = fim != null
                ? LocalDateTime.parse(fim)
                : LocalDateTime.of(2100, 12, 31, 23, 59, 59);

        return ResponseEntity.ok(
                participacaoRepository.rankingAventureiros(status, dataInicio, dataFim)
        );
    }
}