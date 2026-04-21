package com.guilda.aventureiros.aventura.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Entity
@Table(name = "companheiros", schema = "aventura")
public class Companheiro {

    @Id
    private Long id;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "aventureiro_id", nullable = false)
    private Aventureiro aventureiro;

    @Column(nullable = false, length = 120)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EspecieCompanheiro especie;

    @Min(0)
    @Max(100)
    @Column(name = "indice_lealdade", nullable = false)
    private Integer indiceLealdade;
}