package com.guilda.aventureiros.elastic.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.guilda.aventureiros.elastic.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ElasticsearchClient client;

    private List<Produto> extrairResultados(SearchResponse<Produto> response) {
        return response.hits().hits().stream()
                .map(hit -> {
                    Produto produto = hit.source();
                    if (produto != null) {
                        produto.setId(hit.id());
                    }
                    return produto;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<Produto> buscarPorNome(String termo) throws IOException {
        var response = client.search(s -> s
                        .index("guilda_loja")
                        .query(q -> q.match(m -> m.field("nome").query(termo))),
                Produto.class);
        return extrairResultados(response);
    }

    public List<Produto> buscarPorDescricao(String termo) throws IOException {
        var response = client.search(s -> s
                        .index("guilda_loja")
                        .query(q -> q.match(m -> m.field("descricao").query(termo))),
                Produto.class);
        return extrairResultados(response);
    }

    public List<Produto> buscarPorFrase(String termo) throws IOException {
        var response = client.search(s -> s
                        .index("guilda_loja")
                        .query(q -> q.matchPhrase(m -> m.field("descricao").query(termo))),
                Produto.class);
        return extrairResultados(response);
    }

    public List<Produto> buscarFuzzy(String termo) throws IOException {
        var response = client.search(s -> s
                        .index("guilda_loja")
                        .query(q -> q.fuzzy(f -> f.field("nome").value(termo))),
                Produto.class);
        return extrairResultados(response);
    }

    public List<Produto> buscarMultiCampos(String termo) throws IOException {
        var response = client.search(s -> s
                        .index("guilda_loja")
                        .query(q -> q.multiMatch(m -> m
                                .fields("nome", "descricao")
                                .query(termo))),
                Produto.class);
        return extrairResultados(response);
    }

    public List<Produto> buscarComFiltro(String termo, String categoria) throws IOException {
        var response = client.search(s -> s
                        .index("guilda_loja")
                        .query(q -> q.bool(b -> b
                                .must(m -> m.match(mt -> mt.field("descricao").query(termo)))
                                .filter(f -> f.term(t -> t.field("categoria").value(categoria))))),
                Produto.class);
        return extrairResultados(response);
    }

    public List<Produto> buscarPorFaixaPreco(Float min, Float max) throws IOException {
        var response = client.search(s -> s
                        .index("guilda_loja")
                        .query(q -> q.range(r -> r
                                .number(n -> n.field("preco").gte((double) min).lte((double) max)))),
                Produto.class);
        return extrairResultados(response);
    }

    public List<Produto> buscarAvancada(String categoria, String raridade, Float min, Float max) throws IOException {
        var response = client.search(s -> s
                        .index("guilda_loja")
                        .query(q -> q.bool(b -> {
                            if (categoria != null) b.filter(f -> f.term(t -> t.field("categoria").value(categoria)));
                            if (raridade != null) b.filter(f -> f.term(t -> t.field("raridade").value(raridade)));
                            if (min != null && max != null) b.filter(f -> f.range(r -> r
                                    .number(n -> n.field("preco").gte((double) min).lte((double) max))));
                            return b;
                        })),
                Produto.class);
        return extrairResultados(response);
    }

    public Map<String, Long> agregacaoPorCategoria() throws IOException {
        var response = client.search(s -> s
                        .index("guilda_loja")
                        .size(0)
                        .aggregations("por_categoria", Aggregation.of(a -> a
                                .terms(t -> t.field("categoria")))),
                Produto.class);
        return response.aggregations().get("por_categoria").sterms().buckets().array()
                .stream().collect(Collectors.toMap(b -> b.key().stringValue(), b -> b.docCount()));
    }

    public Map<String, Long> agregacaoPorRaridade() throws IOException {
        var response = client.search(s -> s
                        .index("guilda_loja")
                        .size(0)
                        .aggregations("por_raridade", Aggregation.of(a -> a
                                .terms(t -> t.field("raridade")))),
                Produto.class);
        return response.aggregations().get("por_raridade").sterms().buckets().array()
                .stream().collect(Collectors.toMap(b -> b.key().stringValue(), b -> b.docCount()));
    }

    public Double precoMedio() throws IOException {
        var response = client.search(s -> s
                        .index("guilda_loja")
                        .size(0)
                        .aggregations("preco_medio", Aggregation.of(a -> a
                                .avg(avg -> avg.field("preco")))),
                Produto.class);
        return response.aggregations().get("preco_medio").avg().value();
    }

    public Map<String, Long> faixasPreco() throws IOException {
        var response = client.search(s -> s
                        .index("guilda_loja")
                        .size(0)
                        .aggregations("faixas", Aggregation.of(a -> a
                                .range(r -> r.field("preco")
                                        .ranges(rng -> rng.to(100.0))
                                        .ranges(rng -> rng.from(100.0).to(300.0))
                                        .ranges(rng -> rng.from(300.0).to(700.0))
                                        .ranges(rng -> rng.from(700.0))))),
                Produto.class);
        return response.aggregations().get("faixas").range().buckets().array()
                .stream().collect(Collectors.toMap(
                        b -> b.key(),
                        b -> b.docCount()));
    }
}