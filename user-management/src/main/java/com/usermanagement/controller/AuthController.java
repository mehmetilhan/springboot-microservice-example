package com.usermanagement.controller;

import com.usermanagement.dto.LoginRequestDTO;
import com.usermanagement.dto.UserDTO;
import com.usermanagement.jwt.JwtUtil;
import com.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author mehmet
 */
@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> createToken(@Valid @RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(),
                    loginRequestDTO.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorret username or password", ex);
        }
        return ResponseEntity.ok(jwtUtil.generateToken(loginRequestDTO.getEmail()));

    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO) throws Exception {
        return userService.save(userDTO);
    }

    @PostMapping("/validate")
    public Boolean validateToken(@RequestBody String token) {
        return userService.validate(token);
    }

    @GetMapping("/get-user-id-by-token/{token}")
    public ResponseEntity<Long> getUserId(@PathVariable String token) {
        return ResponseEntity.ok(userService.getUserIdByToken(token));
    }


}
