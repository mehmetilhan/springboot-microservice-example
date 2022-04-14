package com.todomanagement.dto;

import com.common.enums.TodoPriority;
import com.common.enums.TodoStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author mehmet
 */
@Data
public class TodoDTO implements Serializable {

    private Long Id;

    @NotNull(message = "name can not be null!")
    private String name;

    @NotNull(message = "groupId can not be null!")
    private Long groupId;

    @NotNull(message = "dueDate can not be null!")
    private Date dueDate;

    private TodoStatus todoStatus = TodoStatus.TODO;

    @NotNull(message = "Priority can not be null!")
    private TodoPriority todoPriority;

}
