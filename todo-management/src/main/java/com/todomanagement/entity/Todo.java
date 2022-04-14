package com.todomanagement.entity;

import com.common.entity.BaseEntity;
import com.common.enums.TodoPriority;
import com.common.enums.TodoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author mehmet
 */
@Data
@Entity
@Table(name = "todos")
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted <> '1'")
@SQLDelete(sql = "UPDATE todos SET deleted = '1' WHERE id = ?")
public class Todo extends BaseEntity {

    private String name;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TodoStatus todoStatus;

    @Column(name = "priority", nullable = false)
    @Enumerated(EnumType.STRING)
    private TodoPriority todoPriority;

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", dueDate=" + dueDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Todo todo = (Todo) o;
        return Objects.equals(getName(), todo.getName()) && Objects.equals(getGroupId(), todo.getGroupId()) && Objects.equals(getDueDate(), todo.getDueDate()) && getTodoStatus() == todo.getTodoStatus() && getTodoPriority() == todo.getTodoPriority();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getGroupId(), getDueDate(), getTodoStatus(), getTodoPriority());
    }
}
