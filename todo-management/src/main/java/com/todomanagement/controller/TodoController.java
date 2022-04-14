package com.todomanagement.controller;

import com.todomanagement.dto.TodoDTO;
import com.todomanagement.dto.TodoSearchDTO;
import com.todomanagement.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author mehmet
 */
@RestController
@RequestMapping("todos")
public class TodoController {

    @Autowired
    TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoDTO> save(@Valid @RequestBody TodoDTO todoDTO, HttpServletRequest request) throws Exception {
        return todoService.save(todoDTO, request);
    }

    @PutMapping("{id}")
    public ResponseEntity<TodoDTO> update(@PathVariable Long id, @Valid @RequestBody TodoDTO todoDTO, HttpServletRequest request) {
        return todoService.update(id, todoDTO, request);
    }

    @PostMapping("filter")
    public ResponseEntity<List<TodoDTO>> getTodosBySearchParameters(@RequestBody TodoSearchDTO todoSearchDTO, HttpServletRequest request) {
        return todoService.filterTodos(todoSearchDTO, request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest request) {
        return todoService.delete(id, request);
    }

}
