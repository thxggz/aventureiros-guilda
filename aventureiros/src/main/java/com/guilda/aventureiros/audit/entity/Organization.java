package com.guilda.aventureiros.audit.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "organizacoes", schema = "audit")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private Boolean ativo;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}