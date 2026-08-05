package com.nehagupta.controller;

import com.nehagupta.model.Student;
import com.nehagupta.service.StudentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public Student getStudentById(@PathVariable("id") int id) {
        return studentService.getStudentById(id);
    }

    @PostMapping("/students")
    public String addStudent(@RequestBody Student student) {

        boolean added = studentService.addStudent(student);

        if (added) {
            return "Student added successfully!";
        }

        return "Failed to add student.";
    }

    @PutMapping("/students/{id}")
    public String updateStudent(
            @PathVariable("id") int id,
            @RequestBody Student student) {

        student.setId(id);

        boolean updated = studentService.updateStudent(student);

        if (updated) {
            return "Student updated successfully!";
        }

        return "Failed to update student.";
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(
            @PathVariable("id") int id) {

        boolean deleted = studentService.deleteStudent(id);

        if (deleted) {
            return "Student deleted successfully!";
        }

        return "Failed to delete student.";
    }
}