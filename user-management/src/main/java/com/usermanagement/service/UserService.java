package com.usermanagement.service;

import com.usermanagement.dto.UserDTO;
import com.usermanagement.entity.User;
import org.springframework.http.ResponseEntity;

/**
 * @author mehmet
 */
public interface UserService {

    User getUserByEmail(String email);

    ResponseEntity<String> save(UserDTO user) throws Exception;

    Boolean validate(String token);

    Boolean checkExist(String email);

    Long getUserIdByToken(String token);
}
