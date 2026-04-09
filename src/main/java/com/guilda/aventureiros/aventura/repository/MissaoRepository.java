package com.guilda.aventureiros.aventura.repository;

import com.guilda.aventureiros.aventura.entity.Missao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MissaoRepository extends JpaRepository<Missao, Long> {

    Page<Missao> findByStatus(String status, Pageable pageable);

    Page<Missao> findByNivelPerigo(String nivelPerigo, Pageable pageable);

    Page<Missao> findByCreatedAtBetween(LocalDateTime inicio, LocalDateTime fim, Pageable pageable);

    @Query("SELECT m FROM Missao m WHERE " +
            "(:status IS NULL OR m.status = :status) AND " +
            "(:nivelPerigo IS NULL OR m.nivelPerigo = :nivelPerigo)")
    Page<Missao> buscarComFiltros(
            @Param("status") String status,
            @Param("nivelPerigo") String nivelPerigo,
            Pageable pageable);

    @Query("SELECT m, COUNT(p), COALESCE(SUM(p.recompensaOuro), 0) " +
            "FROM Missao m LEFT JOIN ParticipacaoMissao p ON p.missao = m " +
            "WHERE m.createdAt BETWEEN :inicio AND :fim " +
            "GROUP BY m")
    List<Object[]> relatorioMissoesComMetricas(
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim);
}