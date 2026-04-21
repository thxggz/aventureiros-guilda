package com.guilda.aventureiros.aventura.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "participacoes_missao", schema = "aventura")
public class ParticipacaoMissao {

    @EmbeddedId
    private ParticipacaoMissaoId id;

    @ManyToOne(optional = false)
    @MapsId("missaoId")
    @JoinColumn(name = "missao_id", nullable = false)
    private Missao missao;

    @ManyToOne(optional = false)
    @MapsId("aventureiroId")
    @JoinColumn(name = "aventureiro_id", nullable = false)
    private Aventureiro aventureiro;

    @Enumerated(EnumType.STRING)
    @Column(name = "papel_missao", nullable = false, length = 50)
    private PapelMissao papelMissao;

    @PositiveOrZero
    @Column(name = "recompensa_ouro")
    private Double recompensaOuro;

    @Column(nullable = false)
    private Boolean mvp;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}