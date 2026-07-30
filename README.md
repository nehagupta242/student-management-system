# Student Management System

A console-based Student Management System built using Java, JDBC, Maven, and MySQL.

The application allows users to manage student records through a menu-driven command-line interface and demonstrates CRUD operations with a relational database.

## Features

- Add a new student
- View all students
- Search for a student by ID
- Update student details
- Delete a student
- Input validation
- Email validation
- MySQL database integration
- Secure database password handling using environment variables

## Tech Stack

- Java 21
- MySQL
- JDBC
- Maven
- Git
- GitHub

## Project Structure

```text
student-management-system/
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── nehagupta/
│                   ├── App.java
│                   ├── dao/
│                   │   └── StudentDAO.java
│                   ├── database/
│                   │   └── DBConnection.java
│                   └── model/
│                       └── Student.java
│
├── database.sql
├── pom.xml
├── .gitignore
└── README.md