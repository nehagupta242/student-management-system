package com.nehagupta.service;

import com.nehagupta.dao.StudentDAO;
import com.nehagupta.model.Student;

import java.util.List;

public class StudentService {

    private StudentDAO studentDAO;

    public StudentService() {
        studentDAO = new StudentDAO();
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    public Student getStudentById(int id) {
        return studentDAO.getStudentById(id);
    }

    public boolean addStudent(Student student) {
        return studentDAO.addStudent(student);
    }

    public boolean updateStudent(Student student) {
        return studentDAO.updateStudent(student);
    }

    public boolean deleteStudent(int id) {
        return studentDAO.deleteStudent(id);
    }
}