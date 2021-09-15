package com.project.accountposting.dto;

import lombok.*;

import java.time.Instant;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TitleDTO {

    private Long id;
    private Long categoryId;
    private Instant createDate;
    private Instant paymentDate;
    private Instant expirationDate;
}
