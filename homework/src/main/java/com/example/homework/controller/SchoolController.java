package com.example.homework.controller;

import com.example.homework.domain.Student;
import com.example.homework.domain.Teacher;
import com.example.homework.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return schoolService.createStudent(student);
    }

    @PostMapping("/students/{studentId}/teachers")
    public Teacher createTeacher(@PathVariable Long studentId, @RequestBody Teacher teacher) {
        return schoolService.createTeacher(studentId, teacher);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return schoolService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> studentOptional = schoolService.getStudentById(id);
        if (studentOptional.isPresent()) {
            return ResponseEntity.ok(studentOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/students/{id}/teachers")
    public List<Teacher> getTeachersByStudentId(@PathVariable Long id){
        return schoolService.getTeachersForStudent(id);
    }
    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers(){
        return schoolService.getAllTeachers();
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        ResponseEntity<Student> response = schoolService.updateStudent(id, student);
        return response;
    }

}