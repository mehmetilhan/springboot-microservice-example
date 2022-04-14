package com.groupmanagement.mapper;

import com.groupmanagement.dto.GroupDTO;
import com.groupmanagement.entity.Group;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author mehmet
 */
@Mapper(componentModel = "spring")
public interface GroupMapper {

    Group toEntity(GroupDTO dto);

    GroupDTO toDto(Group entity);

    List<Group> toEntityList(List<GroupDTO> dtos);

    List<GroupDTO> toDtoList(List<Group> entities);
}

