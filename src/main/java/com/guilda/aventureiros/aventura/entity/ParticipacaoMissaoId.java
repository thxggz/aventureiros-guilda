package com.guilda.aventureiros.aventura.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

@Data
@Embeddable
public class ParticipacaoMissaoId implements Serializable {

    private Long missaoId;
    private Long aventureiroId;
}
