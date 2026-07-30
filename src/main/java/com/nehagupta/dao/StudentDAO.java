package com.nehagupta.dao;

import com.nehagupta.database.DBConnection;
import com.nehagupta.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // CREATE - Add a new student
    public boolean addStudent(Student student) {

        String sql =
                "INSERT INTO students (name, email, course, year) VALUES (?, ?, ?, ?)";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getCourse());
            statement.setInt(4, student.getYear());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {

            System.out.println("Failed to add student.");
            e.printStackTrace();

            return false;
        }
    }


    // READ - Get all students
    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String course = resultSet.getString("course");
                int year = resultSet.getInt("year");

                Student student =
                        new Student(id, name, email, course, year);

                students.add(student);
            }

        } catch (SQLException e) {

            System.out.println("Failed to retrieve students.");
            e.printStackTrace();
        }

        return students;
    }
    // READ - Get student by ID
public Student getStudentById(int id) {

    String sql = "SELECT * FROM students WHERE id = ?";

    try (
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)
    ) {

        statement.setInt(1, id);

        try (ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {

                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String course = resultSet.getString("course");
                int year = resultSet.getInt("year");

                return new Student(
                        id,
                        name,
                        email,
                        course,
                        year
                );
            }
        }

    } catch (SQLException e) {
        System.out.println("Failed to search for student.");
        e.printStackTrace();
    }

    return null;
}
// UPDATE - Update an existing student
public boolean updateStudent(Student student) {

    String sql =
            "UPDATE students SET name = ?, email = ?, course = ?, year = ? WHERE id = ?";

    try (
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)
    ) {

        statement.setString(1, student.getName());
        statement.setString(2, student.getEmail());
        statement.setString(3, student.getCourse());
        statement.setInt(4, student.getYear());
        statement.setInt(5, student.getId());

        int rowsUpdated = statement.executeUpdate();

        return rowsUpdated > 0;

    } catch (SQLException e) {

        System.out.println("Failed to update student.");
        e.printStackTrace();

        return false;
    }
}
// DELETE - Delete student by ID
public boolean deleteStudent(int id) {

    String sql = "DELETE FROM students WHERE id = ?";

    try (
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)
    ) {

        statement.setInt(1, id);

        int rowsDeleted = statement.executeUpdate();

        return rowsDeleted > 0;

    } catch (SQLException e) {

        System.out.println("Failed to delete student.");
        e.printStackTrace();

        return false;
    }
}
}