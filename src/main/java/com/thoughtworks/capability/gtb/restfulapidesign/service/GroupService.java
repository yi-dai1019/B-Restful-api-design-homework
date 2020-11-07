package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.GroupNotExistException;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.GroupRepository;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroupService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public GroupService(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    public List<Group> groupStudent(Integer groupNumber) {
        List<Student> studentList = studentRepository.findAll();
        Collections.shuffle(studentList);
        Map<Integer, List<Student>> studentMap = new HashMap<>();
        Integer leastStudentNumber = studentList.size() / groupNumber;
        Integer leftStudent = studentList.size() % groupNumber;
        Integer studentListIndex = 0;
        for (Integer i = 0; i < groupNumber; i++) {
            if (i < leftStudent) {
                studentMap.put(i, studentList.subList(studentListIndex, studentListIndex + leastStudentNumber + 1));
                studentListIndex = studentListIndex + leastStudentNumber + 1;
                continue;
            }
            studentMap.put(i, studentList.subList(studentListIndex, studentListIndex + leastStudentNumber));
            studentListIndex = studentListIndex + leastStudentNumber;
        }
        groupRepository.save(studentMap);
        return groupRepository.findAll();
    }

    public List<Group> findGroups() {
        return groupRepository.findAll();
    }

    public Group updateGroupName(Integer id, String groupName) throws GroupNotExistException {
        return groupRepository.updateGroupName(id, groupName);
    }
}
