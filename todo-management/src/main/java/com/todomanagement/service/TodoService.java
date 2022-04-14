package com.todomanagement.service;

import com.todomanagement.dto.TodoDTO;
import com.todomanagement.dto.TodoSearchDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author mehmet
 */

public interface TodoService {
    ResponseEntity<TodoDTO> save(TodoDTO todoDTO, HttpServletRequest request) throws Exception;

    ResponseEntity<TodoDTO> update(Long id, TodoDTO todoDTO, HttpServletRequest request);

    ResponseEntity<Void> delete(Long id, HttpServletRequest request);

    ResponseEntity<List<TodoDTO>> filterTodos(TodoSearchDTO todoSearchDTO, HttpServletRequest request);

}
