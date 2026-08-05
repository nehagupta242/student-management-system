package com.nehagupta.controller;

import com.nehagupta.model.Student;
import com.nehagupta.service.StudentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
public class StudentController {

    private StudentService studentService;

    public StudentController() {
        studentService = new StudentService();
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {

        return studentService.getAllStudents();
    }
    @GetMapping("/students/{id}")
public Student getStudentById(
        @PathVariable("id") int id) {

    return studentService.getStudentById(id);
}
@PostMapping("/students")
public String addStudent(
        @RequestBody Student student) {

    boolean added =
            studentService.addStudent(student);

    if (added) {
        return "Student added successfully!";
    }

    return "Failed to add student.";
}
}