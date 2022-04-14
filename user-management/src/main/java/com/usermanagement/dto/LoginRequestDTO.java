package com.usermanagement.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author mehmet
 */
@Data
public class LoginRequestDTO {

    @NotNull
    private String email;

    @NotNull
    private String password;

}
