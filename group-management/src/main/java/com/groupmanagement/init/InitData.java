package com.groupmanagement.init;

import com.groupmanagement.entity.Group;
import com.groupmanagement.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * @author mehmet
 */
@Component
public class InitData implements SmartLifecycle {

    @Autowired
    GroupRepository groupRepository;

    @Override
    public void start() {
        Group group = new Group();
        group.setName("Home");
        group.setUserId(1L);
        groupRepository.save(group);

        Group group2 = new Group();
        group2.setName("Work");
        group2.setUserId(1L);
        groupRepository.save(group2);

        Group group3 = new Group();
        group3.setName("Errands");
        group3.setUserId(2L);
        groupRepository.save(group3);


    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
