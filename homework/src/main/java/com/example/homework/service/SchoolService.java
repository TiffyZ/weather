package com.example.homework.service;

import com.example.homework.domain.Student;
import com.example.homework.domain.Teacher;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface SchoolService {
    Student createStudent(Student student);
    Teacher createTeacher(Teacher teacher);
    List<Student> getAllStudents();
    List<Teacher> getAllTeachers();
    Optional<Student> getStudentById(Long id);
    List<Teacher> getTeachersForStudent(Long id);
    ResponseEntity<Student> updateStudent(Long id, Student student);
}
