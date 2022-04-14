package com.todomanagement.service;

import com.todomanagement.client.UserFeignClient;
import com.todomanagement.dto.TodoDTO;
import com.todomanagement.dto.TodoSearchDTO;
import com.todomanagement.entity.Todo;
import com.todomanagement.mapper.TodoMapper;
import com.todomanagement.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author mehmet
 */
@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    TodoMapper todoMapper;

    @Autowired
    UserFeignClient userFeignClient;

    @Autowired
    RabbitMQProducer rabbitMQProducer;


    @Override
    public ResponseEntity<TodoDTO> save(TodoDTO todoDTO, HttpServletRequest request) {

        Todo todo = todoRepository.save(todoMapper.toEntity(todoDTO));

        rabbitMQProducer.sendToQueue(todo);
        return ResponseEntity.ok(todoMapper.toDto(todo));
    }

    @Override
    public ResponseEntity<TodoDTO> update(Long id, TodoDTO todoDTO, HttpServletRequest request) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if (todo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(todoMapper.toDto(todoRepository.save(todoMapper.merge(todo, todoDTO))));
    }

    @Override
    public ResponseEntity<Void> delete(Long id, HttpServletRequest request) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if (todo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        todoRepository.deleteById(id);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<List<TodoDTO>> filterTodos(TodoSearchDTO todoSearchDTO, HttpServletRequest request) {
        return ResponseEntity.ok(todoMapper.toDtoList(todoRepository.filterTodos(todoSearchDTO)));
    }


}
