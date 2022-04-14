package com.todomanagement.repository;

import com.todomanagement.dto.TodoSearchDTO;
import com.todomanagement.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mehmet
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query(value = "select td from Todo td where (:#{#todoSearchDTO.name} is null or td.name like %:#{#todoSearchDTO" +
            ".name}%) " +
            " and (:#{#todoSearchDTO.todoStatus} is null or td.todoStatus = :#{#todoSearchDTO.todoStatus}) " +
            " and (:#{#todoSearchDTO.todoPriority} is null or td.todoPriority = :#{#todoSearchDTO.todoPriority}) " +
            " and (cast(cast(:#{#todoSearchDTO.startDate} as text) as timestamp) is null or " +
            " td.dueDate >= cast(cast(:#{#todoSearchDTO.startDate} as text) as timestamp)) " +
            " and (cast(cast(:#{#todoSearchDTO.endDate} as text) as timestamp) is null or " +
            " td.dueDate <= cast(cast(:#{#todoSearchDTO.endDate} as text) as timestamp)) " +
            " and (:#{#todoSearchDTO.groupId} is null or td.groupId = :#{#todoSearchDTO.groupId}) ")
    List<Todo> filterTodos(TodoSearchDTO todoSearchDTO);
}
