package com.guilda.aventureiros.elastic.controller;

import com.guilda.aventureiros.elastic.entity.Produto;
import com.guilda.aventureiros.elastic.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/busca/nome")
    public ResponseEntity<List<Produto>> buscarPorNome(@RequestParam String termo) throws IOException {
        return ResponseEntity.ok(produtoService.buscarPorNome(termo));
    }

    @GetMapping("/busca/descricao")
    public ResponseEntity<List<Produto>> buscarPorDescricao(@RequestParam String termo) throws IOException {
        return ResponseEntity.ok(produtoService.buscarPorDescricao(termo));
    }

    @GetMapping("/busca/frase")
    public ResponseEntity<List<Produto>> buscarPorFrase(@RequestParam String termo) throws IOException {
        return ResponseEntity.ok(produtoService.buscarPorFrase(termo));
    }

    @GetMapping("/busca/fuzzy")
    public ResponseEntity<List<Produto>> buscarFuzzy(@RequestParam String termo) throws IOException {
        return ResponseEntity.ok(produtoService.buscarFuzzy(termo));
    }

    @GetMapping("/busca/multicampos")
    public ResponseEntity<List<Produto>> buscarMultiCampos(@RequestParam String termo) throws IOException {
        return ResponseEntity.ok(produtoService.buscarMultiCampos(termo));
    }

    @GetMapping("/busca/com-filtro")
    public ResponseEntity<List<Produto>> buscarComFiltro(
            @RequestParam String termo,
            @RequestParam String categoria) throws IOException {
        return ResponseEntity.ok(produtoService.buscarComFiltro(termo, categoria));
    }

    @GetMapping("/busca/faixa-preco")
    public ResponseEntity<List<Produto>> buscarPorFaixaPreco(
            @RequestParam Float min,
            @RequestParam Float max) throws IOException {
        return ResponseEntity.ok(produtoService.buscarPorFaixaPreco(min, max));
    }

    @GetMapping("/busca/avancada")
    public ResponseEntity<List<Produto>> buscarAvancada(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String raridade,
            @RequestParam(required = false) Float min,
            @RequestParam(required = false) Float max) throws IOException {
        return ResponseEntity.ok(produtoService.buscarAvancada(categoria, raridade, min, max));
    }

    @GetMapping("/agregacoes/por-categoria")
    public ResponseEntity<Map<String, Long>> porCategoria() throws IOException {
        return ResponseEntity.ok(produtoService.agregacaoPorCategoria());
    }

    @GetMapping("/agregacoes/por-raridade")
    public ResponseEntity<Map<String, Long>> porRaridade() throws IOException {
        return ResponseEntity.ok(produtoService.agregacaoPorRaridade());
    }

    @GetMapping("/agregacoes/preco-medio")
    public ResponseEntity<Double> precoMedio() throws IOException {
        return ResponseEntity.ok(produtoService.precoMedio());
    }

    @GetMapping("/agregacoes/faixas-preco")
    public ResponseEntity<Map<String, Long>> faixasPreco() throws IOException {
        return ResponseEntity.ok(produtoService.faixasPreco());
    }
}
