package com.guilda.aventureiros;

import com.guilda.aventureiros.operacoes.entity.PainelTaticoMissao;
import com.guilda.aventureiros.operacoes.service.PainelTaticoMissaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PainelTaticoMissaoServiceTest {

    @Autowired
    private PainelTaticoMissaoService service;

    @Test
    void deveBuscarTop10MissoesUltimosQuinzeDias() {
        List<PainelTaticoMissao> resultado = service.getTop10UltimosQuinzeDias();

        assertThat(resultado).isNotNull();
        assertThat(resultado.size()).isLessThanOrEqualTo(10);

        System.out.println("Total de missões retornadas: " + resultado.size());
        resultado.forEach(m -> System.out.println(
                "Missão: " + m.getTitulo() +
                        " | Índice prontidão: " + m.getIndiceProntidao() +
                        " | Última atualização: " + m.getUltimaAtualizacao()
        ));
    }
}