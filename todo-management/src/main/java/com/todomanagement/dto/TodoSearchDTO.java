package com.todomanagement.dto;

import com.common.enums.TodoPriority;
import com.common.enums.TodoStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mehmet
 */
@Data
public class TodoSearchDTO implements Serializable {

    private String name;

    private TodoStatus todoStatus;

    private TodoPriority todoPriority;

    private Long groupId;

    private Date startDate;

    private Date endDate;
}
