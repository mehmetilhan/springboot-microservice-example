package com.groupmanagement.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author mehmet
 */
@Data
public class GroupDTO implements Serializable {

    private Long id;

    @NotNull(message = "name can not be null!")
    private String name;


}
