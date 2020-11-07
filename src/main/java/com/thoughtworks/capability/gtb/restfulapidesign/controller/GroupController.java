package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.GroupNotExistException;
import com.thoughtworks.capability.gtb.restfulapidesign.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/groups/{number}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Group> groupStudent(@PathVariable("number") Integer groupNumber) {
        return groupService.groupStudent(groupNumber);
    }

    @GetMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    public List<Group> findGroups() {
        return groupService.findGroups();
    }

    @PatchMapping("/groups/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Group updateGroup(@PathVariable("id") Integer id, @RequestBody String groupName) throws GroupNotExistException {
        return groupService.updateGroupName(id, groupName);
    }
}
