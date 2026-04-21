package com.guilda.aventureiros.audit.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "roles", schema = "audit")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "organizacao_id", nullable = false)
    private Organization organization;

    @Column(nullable = false)
    private String nome;

    @Column
    private String descricao;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @ManyToMany
    @JoinTable(
            schema = "audit",
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<Permission> permissions;
}