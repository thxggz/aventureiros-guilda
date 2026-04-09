package com.guilda.aventureiros.aventura.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "companheiros", schema = "aventura")
public class Companheiro {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "aventureiro_id")
    private Aventureiro aventureiro;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, length = 50)
    private String especie;

    @Column(name = "indice_lealdade", nullable = false)
    private Integer indiceLealdade;
}
