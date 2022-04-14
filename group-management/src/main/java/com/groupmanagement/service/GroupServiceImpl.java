package com.groupmanagement.service;

import com.groupmanagement.client.UserFeignClient;
import com.groupmanagement.dto.GroupDTO;
import com.groupmanagement.entity.Group;
import com.groupmanagement.mapper.GroupMapper;
import com.groupmanagement.repository.GroupRepository;
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
public class GroupServiceImpl implements GroupService {


    @Autowired
    GroupRepository groupRepository;

    @Autowired
    GroupMapper groupMapper;

    @Autowired
    UserFeignClient userFeignClient;


    @Override
    public ResponseEntity<GroupDTO> save(GroupDTO groupDTO, HttpServletRequest request) throws Exception {

        Group group = groupMapper.toEntity(groupDTO);
        group.setUserId(getUserIdFromToken(request));

        return ResponseEntity.ok(groupMapper.toDto(groupRepository.save(group)));
    }

    @Override
    public ResponseEntity<List<GroupDTO>> getAll(HttpServletRequest request) throws Exception {
        return ResponseEntity.ok(groupMapper.toDtoList(groupRepository.getAllByUserId(getUserIdFromToken(request))));
    }

    @Override
    public ResponseEntity<GroupDTO> update(Long id, GroupDTO groupDTO, HttpServletRequest request) throws Exception {
        Group oldGroup;

        oldGroup = groupRepository.getByUserIdAndId(getUserIdFromToken(request), id);

        if (oldGroup == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        oldGroup.setName(groupDTO.getName());
        return ResponseEntity.ok(groupMapper.toDto(groupRepository.save(oldGroup)));
    }

    @Override
    public ResponseEntity<Void> delete(Long id, HttpServletRequest request) throws Exception {

        Group group;
        group = groupRepository.getByUserIdAndId(getUserIdFromToken(request), id);
        if (group == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        groupRepository.delete(group);

        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<List<GroupDTO>> getAllByUserId(Long userId) {
        return ResponseEntity.ok(groupMapper.toDtoList(groupRepository.getAllByUserId(userId)));
    }


    private Long getUserIdFromToken(HttpServletRequest request) throws Exception {

        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        }

        if (token == null) {
            throw new Exception("token not found!");
        }

        return userFeignClient.getUserId(request.getHeader("Authorization").substring(7)).getBody();
    }

}
