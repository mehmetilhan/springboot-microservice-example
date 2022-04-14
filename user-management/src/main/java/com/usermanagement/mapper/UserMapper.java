package com.usermanagement.mapper;

import com.usermanagement.dto.UserDTO;
import com.usermanagement.entity.User;
import org.mapstruct.Mapper;

/**
 * @author mehmet
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO dto);

    UserDTO toDTO(User user);
}
