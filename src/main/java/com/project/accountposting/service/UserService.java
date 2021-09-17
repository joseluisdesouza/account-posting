package com.project.accountposting.service;

import com.project.accountposting.domain.User;
import com.project.accountposting.dto.UserDTO;
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
        return new UserDTO(userRepository.save(toDTO(userDTO)));
    }

    public Page<UserDTO> findAll(Optional<Long> id,
                                 Optional<String> name,
                                 final Pageable pageable) {
        return userRepository.findAll(UserSpecification.builder()
                .id(id)
                .name(name)
                .build(), pageable).map(UserDTO::new);
    }

    public UserDTO update(Long id, UserDTO userDTO) {
        var user = getUserById(id);
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setActivated(userDTO.getActivated());
        user.setRoles(userDTO.getRoles());
        return new UserDTO(userRepository.save(user));
    }

    public void delete(Long id) {
        var user = getUserById(id);
        userRepository.deleteById(user.getId());
    }

    private User getUserById(final Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    private User toDTO(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .activated(userDTO.getActivated())
                .roles(userDTO.getRoles())
                .build();
    }
}
