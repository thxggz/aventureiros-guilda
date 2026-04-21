package com.guilda.aventureiros.audit.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "audit_entries", schema = "audit")
public class AuditEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "organizacao_id", nullable = false)
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "actor_user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "actor_api_key_id")
    private ApiKey apiKey;

    @Column(nullable = false)
    private String action;

    @Column(name = "entity_schema", nullable = false)
    private String entitySchema;

    @Column(name = "entity_name", nullable = false)
    private String entityName;

    @Column(name = "entity_id")
    private String entityId;

    @Column(name = "occurred_at", nullable = false)
    private OffsetDateTime occurredAt;

    @Column(columnDefinition = "inet")
    private String ip;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(columnDefinition = "jsonb")
    private String diff;

    @Column(columnDefinition = "jsonb")
    private String metadata;

    @Column(nullable = false)
    private Boolean success;
}