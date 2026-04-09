package com.guilda.aventureiros.aventura.repository;

import com.guilda.aventureiros.aventura.entity.Companheiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CompanheiroRepository extends JpaRepository<Companheiro, Long> {
    Optional<Companheiro> findByAventureiroId(Long aventureiroId);
}