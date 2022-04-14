package com.usermanagement.service;

import com.usermanagement.dto.UserDTO;
import com.usermanagement.entity.User;
import com.usermanagement.jwt.JwtUtil;
import com.usermanagement.mapper.UserMapper;
import com.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author mehmet
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public ResponseEntity<String> save(UserDTO userDTO) throws Exception {

        if (checkExist(userDTO.getEmail())) {
            return ResponseEntity.ok("This user already exists!");
        }

        try {
            userRepository.save(userMapper.toEntity(userDTO));
        } catch (Exception ex) {
            throw new Exception("Failed to create user");
        }
        return ResponseEntity.ok("User created!");
    }

    @Override
    public Boolean validate(String token) {

        String userName = "";
        try {
            userName = jwtUtil.extractUsername(token);
        } catch (Exception ex) {
            return Boolean.FALSE;
        }
        User user = userRepository.findByEmail(userName).orElse(null);
        if (user != null && jwtUtil.validateToken(token,
                new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                        new ArrayList<>()))) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean checkExist(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        return user != null;
    }

    @Override
    public Long getUserIdByToken(String token) {

        String email = "";
        try {
            email = jwtUtil.extractUsername(token);
        } catch (Exception ex) {
            return null;
        }
        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null) {
            return user.getId();
        }
        return null;
    }
}
