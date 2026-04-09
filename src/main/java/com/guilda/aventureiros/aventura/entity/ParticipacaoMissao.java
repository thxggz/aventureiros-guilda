package com.guilda.aventureiros.aventura.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "participacoes_missao", schema = "aventura")
public class ParticipacaoMissao {

    @EmbeddedId
    private ParticipacaoMissaoId id;

    @ManyToOne
    @MapsId("missaoId")
    @JoinColumn(name = "missao_id")
    private Missao missao;

    @ManyToOne
    @MapsId("aventureiroId")
    @JoinColumn(name = "aventureiro_id")
    private Aventureiro aventureiro;

    @Column(name = "papel_missao", nullable = false, length = 50)
    private String papelMissao;

    @Column(name = "recompensa_ouro")
    private Double recompensaOuro;

    @Column(nullable = false)
    private Boolean mvp;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}