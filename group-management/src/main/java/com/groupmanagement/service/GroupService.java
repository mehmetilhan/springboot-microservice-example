package com.groupmanagement.service;

import com.groupmanagement.dto.GroupDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author mehmet
 */
public interface GroupService {

    ResponseEntity<GroupDTO> save(GroupDTO groupDTO, HttpServletRequest request) throws Exception;

    ResponseEntity<List<GroupDTO>> getAll(HttpServletRequest request) throws Exception;

    ResponseEntity<GroupDTO> update(Long id, GroupDTO groupDTO, HttpServletRequest request) throws Exception;

    ResponseEntity<Void> delete(Long id, HttpServletRequest request) throws Exception;

    ResponseEntity<List<GroupDTO>> getAllByUserId(Long userId);
}
