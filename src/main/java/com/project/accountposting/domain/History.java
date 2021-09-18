package com.project.accountposting.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Data
@Entity
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
//manter o padrão dos nomes de tabelas
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long titleId;//usar a entidade
    private Long userId;//usar a entidade
    private Instant createDate;
    private String description;
}
