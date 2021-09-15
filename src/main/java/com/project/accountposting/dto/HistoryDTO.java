package com.project.accountposting.dto;

import lombok.*;

import java.time.Instant;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDTO {

    private Long id;
    private Long titleId;
    private Long userId;
    private Instant createDate;
    private String description;
}
