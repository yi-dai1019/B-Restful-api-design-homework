package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.GroupNotExistException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class GroupRepository {
    private static Map<Integer, Group> groupMap = new HashMap<>();

    public void save(Map<Integer, List<Student>> studentMap) {
        for (Integer i = 0; i < studentMap.size(); i++) {
            groupMap.put(i + 1, new Group(i+1, "group " + (i + 1), studentMap.get(i), null));
        }
    }

    public List<Group> findAll() {
        return groupMap.values().stream().collect(Collectors.toList());
    }

    public Group updateGroupName(Integer id, String groupName) throws GroupNotExistException {
        Group oldGroup = null;
        if (groupMap.containsKey(id)) {
            oldGroup = groupMap.get(id);
            oldGroup.setName(groupName);
            groupMap.put(id,oldGroup);
        } else throw new GroupNotExistException("Not find group, please check");
        return oldGroup;
    }
}
