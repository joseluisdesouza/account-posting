package com.project.accountposting.dto;

import com.project.accountposting.domain.Role;
import lombok.*;

import java.util.List;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private int activated;
    private List<Role> roles;
}
