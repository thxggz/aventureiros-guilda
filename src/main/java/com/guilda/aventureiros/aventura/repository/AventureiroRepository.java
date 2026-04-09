package com.guilda.aventureiros.aventura.repository;

import com.guilda.aventureiros.aventura.entity.Aventureiro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AventureiroRepository extends JpaRepository<Aventureiro, Long> {

    Page<Aventureiro> findByAtivo(Boolean ativo, Pageable pageable);

    Page<Aventureiro> findByClasse(String classe, Pageable pageable);

    Page<Aventureiro> findByNivelGreaterThanEqual(Integer nivelMinimo, Pageable pageable);

    Page<Aventureiro> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    @Query("SELECT a FROM Aventureiro a WHERE " +
            "(:ativo IS NULL OR a.ativo = :ativo) AND " +
            "(:classe IS NULL OR a.classe = :classe) AND " +
            "(:nivelMinimo IS NULL OR a.nivel >= :nivelMinimo)")
    Page<Aventureiro> buscarComFiltros(
            @Param("ativo") Boolean ativo,
            @Param("classe") String classe,
            @Param("nivelMinimo") Integer nivelMinimo,
            Pageable pageable);
}