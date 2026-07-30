package com.nehagupta;

import com.nehagupta.dao.StudentDAO;
import com.nehagupta.database.DBConnection;
import com.nehagupta.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StudentDAO studentDAO = new StudentDAO();

        int choice;

        do {

            System.out.println();
            System.out.println("================================");
            System.out.println("   STUDENT MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.println("================================");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addStudent(scanner, studentDAO);
                    break;

                case 2:
                    viewAllStudents(studentDAO);
                    break;

                case 3:
                    searchStudent(scanner, studentDAO);
                    break;

                case 4:
                    updateStudent(scanner, studentDAO);
                    break;

                case 5:
                    deleteStudent(scanner, studentDAO);
                    break;

                case 6:
                    System.out.println("Exiting Student Management System...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);

        scanner.close();
    }


    private static void addStudent(
            Scanner scanner,
            StudentDAO studentDAO) {

        System.out.println();
        System.out.println("--- Add Student ---");

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student email: ");
        String email = scanner.nextLine();

        System.out.print("Enter student course: ");
        String course = scanner.nextLine();

        System.out.print("Enter student year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        Student student =
                new Student(name, email, course, year);

        boolean added = studentDAO.addStudent(student);

        if (added) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Could not add student.");
        }
    }


    private static void viewAllStudents(
            StudentDAO studentDAO) {

        System.out.println();
        System.out.println("--- All Students ---");

        List<Student> students =
                studentDAO.getAllStudents();

        if (students.isEmpty()) {

            System.out.println("No students found.");
            return;
        }

        System.out.printf(
                "%-5s %-20s %-30s %-25s %-5s%n",
                "ID",
                "Name",
                "Email",
                "Course",
                "Year"
        );

        System.out.println(
                "----------------------------------------------------------------------------------------"
        );

        for (Student student : students) {

            System.out.printf(
                    "%-5d %-20s %-30s %-25s %-5d%n",
                    student.getId(),
                    student.getName(),
                    student.getEmail(),
                    student.getCourse(),
                    student.getYear()
            );
        }
    }
private static void searchStudent(
        Scanner scanner,
        StudentDAO studentDAO) {

    System.out.println();
    System.out.println("--- Search Student ---");

    System.out.print("Enter student ID: ");
    int id = scanner.nextInt();
    scanner.nextLine();

    Student student = studentDAO.getStudentById(id);

    if (student == null) {
        System.out.println("Student not found.");
        return;
    }

    System.out.println();
    System.out.println("Student found!");
    System.out.println("ID     : " + student.getId());
    System.out.println("Name   : " + student.getName());
    System.out.println("Email  : " + student.getEmail());
    System.out.println("Course : " + student.getCourse());
    System.out.println("Year   : " + student.getYear());
}
private static void updateStudent(
        Scanner scanner,
        StudentDAO studentDAO) {

    System.out.println();
    System.out.println("--- Update Student ---");

    System.out.print("Enter student ID to update: ");
    int id = scanner.nextInt();
    scanner.nextLine();

    Student existingStudent = studentDAO.getStudentById(id);

    if (existingStudent == null) {
        System.out.println("Student not found.");
        return;
    }

    System.out.println("Updating: " + existingStudent.getName());

    System.out.print("Enter new name: ");
    String name = scanner.nextLine();

    System.out.print("Enter new email: ");
    String email = scanner.nextLine();

    System.out.print("Enter new course: ");
    String course = scanner.nextLine();

    System.out.print("Enter new year: ");
    int year = scanner.nextInt();
    scanner.nextLine();

    Student updatedStudent =
            new Student(id, name, email, course, year);

    boolean updated =
            studentDAO.updateStudent(updatedStudent);

    if (updated) {
        System.out.println("Student updated successfully!");
    } else {
        System.out.println("Could not update student.");
    }
}
private static void deleteStudent(
        Scanner scanner,
        StudentDAO studentDAO) {

    System.out.println();
    System.out.println("--- Delete Student ---");

    System.out.print("Enter student ID to delete: ");
    int id = scanner.nextInt();
    scanner.nextLine();

    Student student = studentDAO.getStudentById(id);

    if (student == null) {
        System.out.println("Student not found.");
        return;
    }

    System.out.println();
    System.out.println("Student: " + student.getName());

    System.out.print("Are you sure you want to delete this student? (y/n): ");
    String confirmation = scanner.nextLine();

    if (!confirmation.equalsIgnoreCase("y")) {
        System.out.println("Deletion cancelled.");
        return;
    }

    boolean deleted = studentDAO.deleteStudent(id);

    if (deleted) {
        System.out.println("Student deleted successfully!");
    } else {
        System.out.println("Could not delete student.");
    }
}
// DELETE - Delete student by ID
}
