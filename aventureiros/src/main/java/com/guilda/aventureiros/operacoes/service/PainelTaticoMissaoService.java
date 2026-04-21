package com.guilda.aventureiros.operacoes.service;

import com.guilda.aventureiros.operacoes.entity.PainelTaticoMissao;
import com.guilda.aventureiros.operacoes.repository.PainelTaticoMissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PainelTaticoMissaoService {

    @Autowired
    private PainelTaticoMissaoRepository repository;

    @Cacheable("topMissoesCache")
    public List<PainelTaticoMissao> getTop10UltimosQuinzeDias() {
        System.out.println("BUSCANDO NO BANCO - CACHE AINDA NAO USADO");
        LocalDateTime dataLimite = LocalDateTime.now().minusDays(15);
        return repository.findTop10UltimosQuinzeDias(
                dataLimite,
                PageRequest.of(0, 10)
        );
    }
}
