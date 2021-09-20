package com.project.accountposting.resource;

import com.project.accountposting.dto.UserDTO;
import com.project.accountposting.dto.UserResponseDTO;
import com.project.accountposting.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserResource {

    private final UserService userService;

    @PostMapping
    public UserDTO create(@Validated @RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @GetMapping
    public Page<UserDTO> findAll(@RequestParam(required = false) Optional<Long> id,
                                 @RequestParam(required = false) Optional<String> name,
                                 @RequestParam(defaultValue = "0") Integer page,
                                 @RequestParam(defaultValue = "10") Integer linesPerPage,
                                 @RequestParam(defaultValue = "id") String orderBy,
                                 @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
        return userService.findAll(id, name, PageRequest.of(page, linesPerPage, Sort.by(direction, orderBy)));
    }

    @PutMapping("/{id}")
    public UserResponseDTO update(@PathVariable Long id, UserDTO userDTO) {
        return userService.update(id, userDTO);
    }

    @PutMapping("/{id}")
    public UserDTO deactivation(@PathVariable Long id, UserDTO userDTO) {
        return userService.deactivation(id, userDTO);
    }
}
