package com.project.accountposting.mapper;

import com.project.accountposting.domain.User;
import com.project.accountposting.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userDTO(User user);
}
