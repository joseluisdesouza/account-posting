package com.project.accountposting.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Data
@Entity
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive //essa fica no DTO de update
    private Integer activated;
    private String description;
}
