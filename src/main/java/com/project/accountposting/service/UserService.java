package com.project.accountposting.service;

import com.project.accountposting.domain.User;
import com.project.accountposting.dto.UserDTO;
import com.project.accountposting.dto.UserResponseDTO;
import com.project.accountposting.repository.UserRepository;
import com.project.accountposting.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO create(UserDTO userDTO) {

        //colcoar regra para insertir o acteveted
        return new UserDTO(userRepository.save(toDTO(userDTO))
                .withActivated(1));
    }

    public Page<UserDTO> findAll(Optional<Long> id,
                                 Optional<String> name,
                                 final Pageable pageable) {
        return userRepository.findAll(UserSpecification.builder()
                .id(id)
                .name(name)
                .build(), pageable).map(UserDTO::new);
    }

    public UserResponseDTO update(Long id, UserDTO userDTO) {
        var user = getUserById(id);
//        user.setName(userDTO.getName());
//        user.setEmail(userDTO.getEmail());
//        user.setPassword(userDTO.getPassword());
//        user.setActivated(userDTO.getActivated());
        user.setDescription(userDTO.getDescription()); //atualizar somente a descrição como esta na especificação PUT: para atualização da descrição;
        //  user.setRoles(userDTO.getRoles());
        final var userUpdated = userRepository.save(user);
        return new UserResponseDTO(userUpdated);
    }

    /**
     * DELETE: para desativação do registro, note que nesse caso vamos passar o valor 0 para o
     * atributo “activated”
     *
     * @param id
     */
    public UserDTO deactivation(Long id, UserDTO userDTO) {
        var user = getUserById(id);
        userDTO.setActivated(0);
        return new UserDTO(userRepository.save(user));
    }

    private User getUserById(final Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    private User toDTO(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .activated(userDTO.getActivated())
                //   .roles(userDTO.getRoles())
                .build();
    }
}
