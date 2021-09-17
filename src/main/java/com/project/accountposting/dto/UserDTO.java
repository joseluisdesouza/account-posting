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
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Integer activated;
    private List<Role> roles;

    public UserDTO(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        password = user.getPassword();
        activated = user.getActivated();
        roles = user.getRoles();
    }
}
