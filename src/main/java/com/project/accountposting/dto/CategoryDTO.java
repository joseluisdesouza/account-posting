package com.project.accountposting.dto;

import com.project.accountposting.domain.Category;
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

    public CategoryDTO(Category category){
        id = category.getId();
        activated = category.getActivated();
        description = category.getDescription();
    }
}
