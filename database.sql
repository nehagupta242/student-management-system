-- Student Management System
-- Database setup script

CREATE DATABASE IF NOT EXISTS student_management_db;

USE student_management_db;

CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    course VARCHAR(100) NOT NULL,
    year INT NOT NULL
);