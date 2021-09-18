package com.project.accountposting.dto;

import com.project.accountposting.domain.Role;
import com.project.accountposting.domain.User;
import lombok.*;

import java.util.List;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private Integer activated;//deve ser informado na service, sempre que for criado
    private List<Role> roles;

    public UserResponseDTO(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        activated = user.getActivated();
   //     roles = user.getRoles();
    }
}
