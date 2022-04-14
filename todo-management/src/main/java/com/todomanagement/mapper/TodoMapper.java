package com.todomanagement.mapper;

import com.todomanagement.dto.TodoDTO;
import com.todomanagement.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * @author mehmet
 */
@Mapper(componentModel = "spring")
public interface TodoMapper {

    Todo toEntity(TodoDTO dto);

    TodoDTO toDto(Todo entity);

    List<Todo> toEntityList(List<TodoDTO> dtos);

    List<TodoDTO> toDtoList(List<Todo> entites);

    @Mapping(target = "id", ignore = true)
    Todo merge(@MappingTarget Todo entity, TodoDTO dto);

}
