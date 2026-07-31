package com.nehagupta;

import com.nehagupta.dao.StudentDAO;
import com.nehagupta.model.Student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentDAOTest {

    private StudentDAO studentDAO;
    private Student testStudent;


    @BeforeEach
    void setUp() {

        studentDAO = new StudentDAO();

        String testEmail =
                "junit." + System.currentTimeMillis()
                        + "@example.com";

        Student student = new Student(
                "JUnit Test Student",
                testEmail,
                "Computer Science",
                4
        );

        boolean added =
                studentDAO.addStudent(student);

        assertTrue(added);

        List<Student> students =
                studentDAO.getAllStudents();

        for (Student currentStudent : students) {

            if (testEmail.equals(
                    currentStudent.getEmail())) {

                testStudent = currentStudent;
                break;
            }
        }

        assertNotNull(testStudent);
    }


    @AfterEach
    void cleanUp() {

        if (testStudent != null) {

            Student existingStudent =
                    studentDAO.getStudentById(
                            testStudent.getId()
                    );

            if (existingStudent != null) {

                studentDAO.deleteStudent(
                        testStudent.getId()
                );
            }
        }
    }


    @Test
    void shouldRetrieveStudentById() {

        Student foundStudent =
                studentDAO.getStudentById(
                        testStudent.getId()
                );

        assertNotNull(foundStudent);

        assertEquals(
                testStudent.getId(),
                foundStudent.getId()
        );

        assertEquals(
                "JUnit Test Student",
                foundStudent.getName()
        );

        assertEquals(
                "Computer Science",
                foundStudent.getCourse()
        );

        assertEquals(
                4,
                foundStudent.getYear()
        );
    }


    @Test
    void shouldUpdateStudent() {

        Student updatedStudent = new Student(
                testStudent.getId(),
                "Updated JUnit Student",
                testStudent.getEmail(),
                "Information Science",
                3
        );

        boolean updated =
                studentDAO.updateStudent(
                        updatedStudent
                );

        assertTrue(updated);

        Student result =
                studentDAO.getStudentById(
                        testStudent.getId()
                );

        assertNotNull(result);

        assertEquals(
                "Updated JUnit Student",
                result.getName()
        );

        assertEquals(
                "Information Science",
                result.getCourse()
        );

        assertEquals(
                3,
                result.getYear()
        );
    }


    @Test
    void shouldDeleteStudent() {

        int studentId =
                testStudent.getId();

        boolean deleted =
                studentDAO.deleteStudent(
                        studentId
                );

        assertTrue(deleted);

        Student result =
                studentDAO.getStudentById(
                        studentId
                );

        assertNull(result);
    }
}