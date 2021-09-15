package com.project.accountposting.dto;

import lombok.*;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    private Long id;
    private String authorities;
}
