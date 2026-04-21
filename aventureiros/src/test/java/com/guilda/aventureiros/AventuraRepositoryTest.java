package com.guilda.aventureiros;

import com.guilda.aventureiros.aventura.entity.Aventureiro;
import com.guilda.aventureiros.aventura.entity.Missao;
import com.guilda.aventureiros.aventura.repository.AventureiroRepository;
import com.guilda.aventureiros.aventura.repository.MissaoRepository;
import com.guilda.aventureiros.aventura.repository.ParticipacaoMissaoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AventuraRepositoryTest {

    @Autowired
    private AventureiroRepository aventureiroRepository;

    @Autowired
    private MissaoRepository missaoRepository;

    @Autowired
    private ParticipacaoMissaoRepository participacaoRepository;

    @Test
    void deveListarAventureirosComFiltros() {
        Page<Aventureiro> result = aventureiroRepository.buscarComFiltros(
                null, null, null,
                PageRequest.of(0, 10, Sort.by("nome"))
        );
        assertThat(result).isNotNull();
        System.out.println("Total aventureiros: " + result.getTotalElements());
        result.forEach(a -> System.out.println("  - " + a.getNome() + " | " + a.getClasse() + " | Nível " + a.getNivel()));
    }

    @Test
    void deveBuscarAventureiroPorNome() {
        Page<Aventureiro> result = aventureiroRepository.findByNomeContainingIgnoreCase(
                "a", PageRequest.of(0, 10)
        );
        assertThat(result).isNotNull();
        System.out.println("Aventureiros com 'a' no nome: " + result.getTotalElements());
    }

    @Test
    void deveListarMissoesComFiltros() {
        Page<Missao> result = missaoRepository.buscarComFiltros(
                null, null, null, null,
                PageRequest.of(0, 10, Sort.by("titulo"))
        );
        assertThat(result).isNotNull();
        System.out.println("Total missões: " + result.getTotalElements());
        result.forEach(m -> System.out.println("  - " + m.getTitulo() + " | " + m.getStatus()));
    }

    @Test
    void deveGerarRankingAventureiros() {
        List<Object[]> ranking = participacaoRepository.rankingAventureiros(
                null, null, null
        );
        assertThat(ranking).isNotNull();
        System.out.println("Ranking de aventureiros: " + ranking.size());
    }

    @Test
    void deveGerarRelatorioMissoes() {
        List<Object[]> relatorio = missaoRepository.relatorioMissoesComMetricas(
                LocalDateTime.now().minusYears(10),
                LocalDateTime.now()
        );
        assertThat(relatorio).isNotNull();
        System.out.println("Relatório de missões: " + relatorio.size() + " missões encontradas");
    }
}