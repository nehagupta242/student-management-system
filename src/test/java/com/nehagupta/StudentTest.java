package com.nehagupta;

import com.nehagupta.model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {

    @Test
    void shouldCreateStudentWithCorrectDetails() {

        Student student = new Student(
                "Neha Gupta",
                "neha@example.com",
                "Computer Science",
                4
        );

        assertEquals(
                "Neha Gupta",
                student.getName()
        );

        assertEquals(
                "neha@example.com",
                student.getEmail()
        );

        assertEquals(
                "Computer Science",
                student.getCourse()
        );

        assertEquals(
                4,
                student.getYear()
        );
    }


    @Test
    void shouldUpdateStudentDetails() {

        Student student = new Student(
                "Rahul Sharma",
                "rahul@example.com",
                "Computer Science",
                2
        );

        student.setName("Rahul Verma");

        student.setEmail(
                "rahul.verma@example.com"
        );

        student.setCourse(
                "Information Science"
        );

        student.setYear(3);

        assertEquals(
                "Rahul Verma",
                student.getName()
        );

        assertEquals(
                "rahul.verma@example.com",
                student.getEmail()
        );

        assertEquals(
                "Information Science",
                student.getCourse()
        );

        assertEquals(
                3,
                student.getYear()
        );
    }
}