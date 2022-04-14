package com.groupmanagement.repository;

import com.groupmanagement.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mehmet
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Group getByUserIdAndId(Long userId, Long id);

    List<Group> getAllByUserId(Long userId);

}
