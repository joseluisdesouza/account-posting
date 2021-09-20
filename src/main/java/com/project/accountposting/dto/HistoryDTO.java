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
    private TitleDTO titleDTO;
    private UserDTO userDTO;
    private Instant createDate;
    private String description;
}
