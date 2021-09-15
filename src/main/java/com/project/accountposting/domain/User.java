package com.project.accountposting.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@Entity
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @Positive
    private int activated;
    private List<Role> roles;
}
