package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.StudentNotExistException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {
    private Map<Integer, Student> studentMap = new HashMap<>();

    {
        studentMap.put(1, new Student(1, "Lily", "female", null));
        studentMap.put(2, new Student(2, "Amy", "female", null));
        studentMap.put(3, new Student(3, "Bob", "male", null));
        studentMap.put(4, new Student(4, "Tom", "male", null));
        studentMap.put(5, new Student(5, "Allan", "male", null));
        studentMap.put(6, new Student(6, "CC", "female", null));

    }

    public Student addStudent(Student student) {
        Integer key = Integer.parseInt(studentMap.keySet().toArray()[studentMap.size() - 1].toString()) + 1;
        student.setId(key);
        studentMap.put(key, student);
        return student;
    }

    public void deleteStudent(Integer id) {
        studentMap.remove(id);
    }

    public Student findStudent(Integer id) {
        return studentMap.get(id);
    }

    public List<Student> findStudentsByGender(String gender) throws StudentNotExistException {
        List<Student> students = new ArrayList<>();
        for(Map.Entry<Integer, Student> entry : studentMap.entrySet()) {
            if (gender.equals(entry.getValue().getGender())) {
                students.add(entry.getValue());
            }
        }
        if (students.size() != 0){
            return students;
        }
        throw new StudentNotExistException("Not found student, please check.");
    }

    public Boolean studentExist(Integer id) throws StudentNotExistException {
        if (studentMap.containsKey(id)) {
            return true;
        } else throw new StudentNotExistException("Not found student, please check.");
    }

    public List<Student> findAll() {
        return studentMap.values().stream().collect(Collectors.toList());
    }

    public Student updateStudentInfo(Integer id, Student student) {
        Student oldStudent = studentMap.get(id);
        oldStudent.setName(student.getName());
        oldStudent.setGender(student.getGender());
        oldStudent.setNote(student.getNote());
        studentMap.put(id, oldStudent);
        return oldStudent;
    }
}
