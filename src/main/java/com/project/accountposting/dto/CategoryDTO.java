package com.project.accountposting.dto;

import lombok.*;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    private Integer activated;
    private String description;
}
