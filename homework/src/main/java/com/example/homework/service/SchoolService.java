package com.example.homework.service;

import com.example.homework.domain.Student;
import com.example.homework.domain.Teacher;
import com.example.homework.repository.StudentRepository;
import com.example.homework.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Teacher createTeacher(Long studentId, Teacher teacher) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        teacher.setStudent(student);
        return teacherRepository.save(teacher);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public List<Teacher> getTeachersForStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow();
        return student.getTeachers();
    }

    public ResponseEntity<Student> updateStudent(Long id, Student student) {
        Optional<Student> existingStudentOptional = studentRepository.findById(id);
        if (existingStudentOptional.isPresent()) {
            Student existingStudent = existingStudentOptional.get();
            existingStudent.setName(student.getName());
            Student updatedStudent = studentRepository.save(existingStudent);
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}