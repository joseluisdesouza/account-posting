package com.project.accountposting.dto;

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
public class HistoryDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long titleId;
    private Long userId;
    private Instant createDate;
    private String description;
}
