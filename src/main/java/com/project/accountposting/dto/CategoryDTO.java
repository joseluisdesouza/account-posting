package com.project.accountposting.dto;

import com.project.accountposting.domain.Category;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    @NotNull
    private Integer activated;
    @NotBlank
    private String description;

    public CategoryDTO(Category category){
        id = category.getId();
        activated = category.getActivated();
        description = category.getDescription();
    }
}
