package com.project.accountposting.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
//manter o padrão dos nomes de tabelas
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authorities;// conferir na especificação o nome certo
}
