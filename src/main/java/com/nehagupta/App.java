package com.nehagupta;

import com.nehagupta.dao.StudentDAO;
import com.nehagupta.model.Student;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StudentDAO studentDAO = new StudentDAO();

        int choice;

        do {

            displayMenu();

            choice = readInt(
                    scanner,
                    "Enter your choice: ",
                    1,
                    7
            );

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
                    filterStudents(scanner, studentDAO);
                    break;

                case 7:
                    System.out.println(
                            "Exiting Student Management System..."
                    );
                    break;

                default:
                    break;
            }

        } while (choice != 7);

        scanner.close();
    }


    // =========================
    // DISPLAY MENU
    // =========================

    private static void displayMenu() {

        System.out.println();
        System.out.println("================================");
        System.out.println("   STUDENT MANAGEMENT SYSTEM");
        System.out.println("================================");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Students");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Filter Students");
        System.out.println("7. Exit");
        System.out.println("================================");
    }


    // =========================
    // CREATE
    // =========================

    private static void addStudent(
            Scanner scanner,
            StudentDAO studentDAO) {

        System.out.println();
        System.out.println("--- Add Student ---");

        String name = readNonEmptyString(
                scanner,
                "Enter student name: "
        );

        String email = readEmail(scanner);

        String course = readNonEmptyString(
                scanner,
                "Enter student course: "
        );

        int year = readInt(
                scanner,
                "Enter student year (1-4): ",
                1,
                4
        );

        Student student =
                new Student(name, email, course, year);

        boolean added =
                studentDAO.addStudent(student);

        if (added) {
            System.out.println(
                    "Student added successfully!"
            );
        } else {
            System.out.println(
                    "Could not add student."
            );
        }
    }


    // =========================
    // READ ALL
    // =========================

    private static void viewAllStudents(
            StudentDAO studentDAO) {

        System.out.println();
        System.out.println("--- All Students ---");

        List<Student> students =
                studentDAO.getAllStudents();

        if (students.isEmpty()) {

            System.out.println(
                    "No students found."
            );

            return;
        }

        displayStudentList(students);
    }


    // =========================
    // SEARCH MENU
    // =========================

    private static void searchStudent(
            Scanner scanner,
            StudentDAO studentDAO) {

        System.out.println();
        System.out.println("--- Search Students ---");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.println("3. Back");

        int choice = readInt(
                scanner,
                "Enter your choice: ",
                1,
                3
        );

        switch (choice) {

            case 1:
                searchStudentById(scanner, studentDAO);
                break;

            case 2:
                searchStudentsByName(scanner, studentDAO);
                break;

            case 3:
                return;

            default:
                break;
        }
    }


    // =========================
    // SEARCH BY ID
    // =========================

    private static void searchStudentById(
            Scanner scanner,
            StudentDAO studentDAO) {

        System.out.println();
        System.out.println("--- Search Student by ID ---");

        int id = readPositiveInt(
                scanner,
                "Enter student ID: "
        );

        Student student =
                studentDAO.getStudentById(id);

        if (student == null) {

            System.out.println(
                    "Student not found."
            );

            return;
        }

        System.out.println();
        System.out.println("Student found!");

        displayStudent(student);
    }


    // =========================
    // SEARCH BY NAME
    // =========================

    private static void searchStudentsByName(
            Scanner scanner,
            StudentDAO studentDAO) {

        System.out.println();
        System.out.println("--- Search Students by Name ---");

        String name = readNonEmptyString(
                scanner,
                "Enter student name: "
        );

        List<Student> students =
                studentDAO.searchStudentsByName(name);

        if (students.isEmpty()) {

            System.out.println(
                    "No students found matching \"" +
                            name +
                            "\"."
            );

            return;
        }

        System.out.println();
        System.out.println(
                students.size() +
                        " student(s) found:"
        );

        displayStudentList(students);
    }


    // =========================
    // UPDATE
    // =========================

    private static void updateStudent(
            Scanner scanner,
            StudentDAO studentDAO) {

        System.out.println();
        System.out.println("--- Update Student ---");

        int id = readPositiveInt(
                scanner,
                "Enter student ID to update: "
        );

        Student existingStudent =
                studentDAO.getStudentById(id);

        if (existingStudent == null) {

            System.out.println(
                    "Student not found."
            );

            return;
        }

        System.out.println();
        System.out.println(
                "Updating student:"
        );

        displayStudent(existingStudent);

        System.out.println();

        String name = readNonEmptyString(
                scanner,
                "Enter new name: "
        );

        String email = readEmailWithPrompt(
                scanner,
                "Enter new email: "
        );

        String course = readNonEmptyString(
                scanner,
                "Enter new course: "
        );

        int year = readInt(
                scanner,
                "Enter new year (1-4): ",
                1,
                4
        );

        Student updatedStudent =
                new Student(
                        id,
                        name,
                        email,
                        course,
                        year
                );

        boolean updated =
                studentDAO.updateStudent(
                        updatedStudent
                );

        if (updated) {

            System.out.println(
                    "Student updated successfully!"
            );

        } else {

            System.out.println(
                    "Could not update student."
            );
        }
    }


    // =========================
    // DELETE
    // =========================

    private static void deleteStudent(
            Scanner scanner,
            StudentDAO studentDAO) {

        System.out.println();
        System.out.println("--- Delete Student ---");

        int id = readPositiveInt(
                scanner,
                "Enter student ID to delete: "
        );

        Student student =
                studentDAO.getStudentById(id);

        if (student == null) {

            System.out.println(
                    "Student not found."
            );

            return;
        }

        System.out.println();
        System.out.println(
                "Student selected:"
        );

        displayStudent(student);

        System.out.println();

        System.out.print(
                "Are you sure you want to delete this student? (y/n): "
        );

        String confirmation =
                scanner.nextLine().trim();

        if (!confirmation.equalsIgnoreCase("y")) {

            System.out.println(
                    "Deletion cancelled."
            );

            return;
        }

        boolean deleted =
                studentDAO.deleteStudent(id);

        if (deleted) {

            System.out.println(
                    "Student deleted successfully!"
            );

        } else {

            System.out.println(
                    "Could not delete student."
            );
        }
    }


    // =========================
    // FILTER MENU
    // =========================

    private static void filterStudents(
            Scanner scanner,
            StudentDAO studentDAO) {

        System.out.println();
        System.out.println("--- Filter Students ---");
        System.out.println("1. Filter by Course");
        System.out.println("2. Filter by Year");
        System.out.println("3. Back");

        int choice = readInt(
                scanner,
                "Enter your choice: ",
                1,
                3
        );

        switch (choice) {

            case 1:
                filterByCourse(scanner, studentDAO);
                break;

            case 2:
                filterByYear(scanner, studentDAO);
                break;

            case 3:
                return;

            default:
                break;
        }
    }


    // =========================
    // FILTER BY COURSE
    // =========================

    private static void filterByCourse(
            Scanner scanner,
            StudentDAO studentDAO) {

        System.out.println();
        System.out.println("--- Filter by Course ---");

        String course = readNonEmptyString(
                scanner,
                "Enter course: "
        );

        List<Student> students =
                studentDAO.getStudentsByCourse(course);

        if (students.isEmpty()) {

            System.out.println(
                    "No students found for course \"" +
                            course +
                            "\"."
            );

            return;
        }

        System.out.println();
        System.out.println(
                students.size() +
                        " student(s) found:"
        );

        displayStudentList(students);
    }


    // =========================
    // FILTER BY YEAR
    // =========================

    private static void filterByYear(
            Scanner scanner,
            StudentDAO studentDAO) {

        System.out.println();
        System.out.println("--- Filter by Year ---");

        int year = readInt(
                scanner,
                "Enter year (1-4): ",
                1,
                4
        );

        List<Student> students =
                studentDAO.getStudentsByYear(year);

        if (students.isEmpty()) {

            System.out.println(
                    "No students found in year " +
                            year +
                            "."
            );

            return;
        }

        System.out.println();
        System.out.println(
                students.size() +
                        " student(s) found:"
        );

        displayStudentList(students);
    }


    // =========================
    // DISPLAY ONE STUDENT
    // =========================

    private static void displayStudent(
            Student student) {

        System.out.println(
                "ID     : " + student.getId()
        );

        System.out.println(
                "Name   : " + student.getName()
        );

        System.out.println(
                "Email  : " + student.getEmail()
        );

        System.out.println(
                "Course : " + student.getCourse()
        );

        System.out.println(
                "Year   : " + student.getYear()
        );
    }


    // =========================
    // DISPLAY STUDENT LIST
    // =========================

    private static void displayStudentList(
            List<Student> students) {

        System.out.println();

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


    // =========================
    // INTEGER VALIDATION
    // =========================

    private static int readInt(
            Scanner scanner,
            String message,
            int min,
            int max) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            try {

                int value =
                        Integer.parseInt(input);

                if (value >= min &&
                        value <= max) {

                    return value;
                }

                System.out.println(
                        "Please enter a number between " +
                                min +
                                " and " +
                                max +
                                "."
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }


    // =========================
    // POSITIVE INTEGER VALIDATION
    // =========================

    private static int readPositiveInt(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            try {

                int value =
                        Integer.parseInt(input);

                if (value > 0) {
                    return value;
                }

                System.out.println(
                        "ID must be greater than 0."
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a valid number."
                );
            }
        }
    }


    // =========================
    // EMPTY STRING VALIDATION
    // =========================

    private static String readNonEmptyString(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                    "This field cannot be empty."
            );
        }
    }


    // =========================
    // EMAIL VALIDATION
    // =========================

    private static String readEmail(
            Scanner scanner) {

        return readEmailWithPrompt(
                scanner,
                "Enter student email: "
        );
    }


    private static String readEmailWithPrompt(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String email =
                    scanner.nextLine().trim();

            if (isValidEmail(email)) {
                return email;
            }

            System.out.println(
                    "Invalid email address. Please try again."
            );
        }
    }


    private static boolean isValidEmail(
            String email) {

        return email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        );
    }
}