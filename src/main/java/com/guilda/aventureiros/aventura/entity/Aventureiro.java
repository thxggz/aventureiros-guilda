package com.guilda.aventureiros.aventura.entity;

import com.guilda.aventureiros.audit.entity.Organization;
import com.guilda.aventureiros.audit.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "aventureiros", schema = "aventura")
public class Aventureiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "organizacao_id", nullable = false)
    private Organization organizacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuarioCadastro;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, length = 50)
    private String classe;

    @Column(nullable = false)
    private Integer nivel;

    @Column(nullable = false)
    private Boolean ativo;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
