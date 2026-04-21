package com.guilda.aventureiros.aventura.repository;

import com.guilda.aventureiros.aventura.entity.ParticipacaoMissao;
import com.guilda.aventureiros.aventura.entity.ParticipacaoMissaoId;
import com.guilda.aventureiros.aventura.entity.StatusMissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ParticipacaoMissaoRepository extends JpaRepository<ParticipacaoMissao, ParticipacaoMissaoId> {

    List<ParticipacaoMissao> findByMissaoId(Long missaoId);

    List<ParticipacaoMissao> findByAventureiroId(Long aventureiroId);

    @Query("SELECT p.aventureiro, COUNT(p), COALESCE(SUM(p.recompensaOuro), 0), " +
            "SUM(CASE WHEN p.mvp = true THEN 1 ELSE 0 END) " +
            "FROM ParticipacaoMissao p " +
            "WHERE (:status IS NULL OR p.missao.status = :status) " +
            "AND p.createdAt BETWEEN :inicio AND :fim " +
            "GROUP BY p.aventureiro " +
            "ORDER BY COUNT(p) DESC")
    List<Object[]> rankingAventureiros(
            @Param("status") StatusMissao status,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim);
}