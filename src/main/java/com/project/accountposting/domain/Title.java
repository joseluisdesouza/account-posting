package com.project.accountposting.domain;

import com.project.accountposting.enums.SituationEnum;
import com.project.accountposting.enums.TypeEnum;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
//manter o padrão dos nomes de tabelas
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long categoryId;// Usar a entidade
    private Instant createDate;
    private Instant paymentDate;
    private Instant expirationDate;
    private TypeEnum type;
    private SituationEnum situation;
    private String description;
    private BigDecimal discount;
    private BigDecimal value;
}
