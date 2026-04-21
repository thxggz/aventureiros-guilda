package com.guilda.aventureiros.operacoes.controller;

import com.guilda.aventureiros.operacoes.entity.PainelTaticoMissao;
import com.guilda.aventureiros.operacoes.service.PainelTaticoMissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class PainelTaticoMissaoController {

    @Autowired
    private PainelTaticoMissaoService service;

    @GetMapping("/top15dias")
    public ResponseEntity<List<PainelTaticoMissao>> getTop15Dias() {
        List<PainelTaticoMissao> resultado = service.getTop10UltimosQuinzeDias();
        return ResponseEntity.ok(resultado);
    }
}