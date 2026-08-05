package com.nehagupta.model;

public class Student {

    private int id;
    private String name;
    private String email;
    private String course;
    private int year;

    // Default constructor (Required by Spring Boot)
    public Student() {

    }

    // Constructor for adding a new student
    public Student(String name, String email, String course, int year) {
        this.name = name;
        this.email = email;
        this.course = course;
        this.year = year;
    }

    // Constructor for retrieving a student from the database
    public Student(int id, String name, String email, String course, int year) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
        this.year = year;
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCourse() {
        return course;
    }

    public int getYear() {
        return year;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setYear(int year) {
        this.year = year;
    }
}