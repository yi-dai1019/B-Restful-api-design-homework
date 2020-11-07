package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.StudentNotExistException;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.addStudent(student);
    }

    public void deleteStudent(Integer id) throws StudentNotExistException {
        if (studentRepository.studentExist(id)) {
            studentRepository.deleteStudent(id);
        }
    }

    public Student findStudent(Integer id) throws StudentNotExistException {
        Student student = null;
        if (studentRepository.studentExist(id)) {
            student = studentRepository.findStudent(id);
        }
        return student;
    }

    public List<Student> findStudents(String gender) throws StudentNotExistException {
        if (gender == null){
            return studentRepository.findAll();
        }
        return studentRepository.findStudentsByGender(gender);
    }

    public Student updateStudentInfo(Integer id, Student student) throws StudentNotExistException {
        Student studentUpdated = null;
        if (studentRepository.studentExist(id)) {
            studentUpdated = studentRepository.updateStudentInfo(id, student);
        }
        return studentUpdated;
    }
}
