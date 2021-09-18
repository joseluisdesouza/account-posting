package com.project.accountposting.dto;

import com.project.accountposting.domain.Title;
import com.project.accountposting.enums.SituationEnum;
import com.project.accountposting.enums.TypeEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TitleDTO {

    private Long id;
    private Long categoryId;
    private Instant createDate;// não é necessario ser passado no DTO, o metodo que insere deve ser responsavel por informar essa data.
    private Instant paymentDate;
    private Instant expirationDate;
    private TypeEnum type;
    private SituationEnum situation; // Não pé necessario ser passado no DTO, pois todo titulo entra no sistema como pendente
    private String description;
    private BigDecimal discount;
    private BigDecimal value;

    public TitleDTO(Title title) {
        id = title.getId();
        categoryId = title.getCategoryId();
        createDate = title.getCreateDate();
        paymentDate = title.getPaymentDate();
        expirationDate = title.getExpirationDate();
        type = title.getType();
        situation = title.getSituation();
        description = title.getDescription();
        discount = title.getDiscount();
        value = title.getValue();
    }
}
