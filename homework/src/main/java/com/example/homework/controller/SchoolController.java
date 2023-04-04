package com.example.homework.controller;

import com.example.homework.domain.Student;
import com.example.homework.domain.Teacher;
import com.example.homework.service.SchoolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/homework")
public class SchoolController {
    @Autowired
    private SchoolServiceImpl schoolServiceImpl;

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return schoolServiceImpl.createStudent(student);
    }

    @PostMapping("/teachers")
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return schoolServiceImpl.createTeacher(teacher);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return schoolServiceImpl.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> studentOptional = schoolServiceImpl.getStudentById(id);
        if (studentOptional.isPresent()) {
            return ResponseEntity.ok(studentOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/students/{id}/teachers")
    public List<Teacher> getTeachersByStudentId(@PathVariable Long id){
        return schoolServiceImpl.getTeachersForStudent(id);
    }
    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers(){
        return schoolServiceImpl.getAllTeachers();
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        ResponseEntity<Student> response = schoolServiceImpl.updateStudent(id, student);
        return response;
    }

}