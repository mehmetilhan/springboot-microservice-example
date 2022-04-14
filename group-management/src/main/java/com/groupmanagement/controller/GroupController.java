package com.groupmanagement.controller;

import com.groupmanagement.client.UserFeignClient;
import com.groupmanagement.dto.GroupDTO;
import com.groupmanagement.service.GroupService;
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
@RequestMapping("groups")
public class GroupController {

    @Autowired
    GroupService groupService;

    @Autowired
    UserFeignClient userFeignClient;

    @PostMapping
    public ResponseEntity<GroupDTO> save(@Valid @RequestBody GroupDTO groupDTO, HttpServletRequest request) throws Exception {
        return groupService.save(groupDTO, request);
    }

    @PutMapping("{id}")
    public ResponseEntity<GroupDTO> update(@PathVariable Long id, @Valid @RequestBody GroupDTO groupDTO,
                                           HttpServletRequest request) throws Exception {
        return groupService.update(id, groupDTO, request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest request) throws Exception {
        return groupService.delete(id, request);
    }


    @GetMapping()
    public ResponseEntity<List<GroupDTO>> getAll(HttpServletRequest request) throws Exception {
        return groupService.getAll(request);
    }


}
