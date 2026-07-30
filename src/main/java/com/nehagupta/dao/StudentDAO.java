package com.nehagupta.dao;

import com.nehagupta.database.DBConnection;
import com.nehagupta.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StudentDAO {


    // =========================
    // CREATE - ADD STUDENT
    // =========================

    public boolean addStudent(Student student) {

        String sql =
                "INSERT INTO students (name, email, course, year) " +
                "VALUES (?, ?, ?, ?)";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    student.getName()
            );

            statement.setString(
                    2,
                    student.getEmail()
            );

            statement.setString(
                    3,
                    student.getCourse()
            );

            statement.setInt(
                    4,
                    student.getYear()
            );

            int rowsInserted =
                    statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Failed to add student."
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================
    // READ - GET ALL STUDENTS
    // =========================

    public List<Student> getAllStudents() {

        List<Student> students =
                new ArrayList<>();

        String sql =
                "SELECT * FROM students";

        try (
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet resultSet =
                    statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Student student =
                        createStudentFromResultSet(
                                resultSet
                        );

                students.add(student);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to retrieve students."
            );

            e.printStackTrace();
        }

        return students;
    }


    // =========================
    // READ - GET STUDENT BY ID
    // =========================

    public Student getStudentById(int id) {

        String sql =
                "SELECT * FROM students WHERE id = ?";

        try (
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            try (
                ResultSet resultSet =
                        statement.executeQuery()
            ) {

                if (resultSet.next()) {

                    return createStudentFromResultSet(
                            resultSet
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to search for student."
            );

            e.printStackTrace();
        }

        return null;
    }


    // =========================
    // READ - SEARCH BY NAME
    // =========================

    public List<Student> searchStudentsByName(
            String name) {

        List<Student> students =
                new ArrayList<>();

        String sql =
                "SELECT * FROM students " +
                "WHERE name LIKE ?";

        try (
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    "%" + name + "%"
            );

            try (
                ResultSet resultSet =
                        statement.executeQuery()
            ) {

                while (resultSet.next()) {

                    Student student =
                            createStudentFromResultSet(
                                    resultSet
                            );

                    students.add(student);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to search students by name."
            );

            e.printStackTrace();
        }

        return students;
    }


    // =========================
    // UPDATE STUDENT
    // =========================

    public boolean updateStudent(
            Student student) {

        String sql =
                "UPDATE students " +
                "SET name = ?, email = ?, course = ?, year = ? " +
                "WHERE id = ?";

        try (
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    student.getName()
            );

            statement.setString(
                    2,
                    student.getEmail()
            );

            statement.setString(
                    3,
                    student.getCourse()
            );

            statement.setInt(
                    4,
                    student.getYear()
            );

            statement.setInt(
                    5,
                    student.getId()
            );

            int rowsUpdated =
                    statement.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Failed to update student."
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================
    // DELETE STUDENT
    // =========================

    public boolean deleteStudent(int id) {

        String sql =
                "DELETE FROM students WHERE id = ?";

        try (
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            int rowsDeleted =
                    statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Failed to delete student."
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================
    // FILTER BY COURSE
    // =========================

    public List<Student> getStudentsByCourse(
            String course) {

        List<Student> students =
                new ArrayList<>();

        String sql =
                "SELECT * FROM students " +
                "WHERE LOWER(course) = LOWER(?)";

        try (
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    course
            );

            try (
                ResultSet resultSet =
                        statement.executeQuery()
            ) {

                while (resultSet.next()) {

                    Student student =
                            createStudentFromResultSet(
                                    resultSet
                            );

                    students.add(student);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to filter students by course."
            );

            e.printStackTrace();
        }

        return students;
    }


    // =========================
    // FILTER BY YEAR
    // =========================

    public List<Student> getStudentsByYear(
            int year) {

        List<Student> students =
                new ArrayList<>();

        String sql =
                "SELECT * FROM students " +
                "WHERE year = ?";

        try (
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    year
            );

            try (
                ResultSet resultSet =
                        statement.executeQuery()
            ) {

                while (resultSet.next()) {

                    Student student =
                            createStudentFromResultSet(
                                    resultSet
                            );

                    students.add(student);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to filter students by year."
            );

            e.printStackTrace();
        }

        return students;
    }


    // =========================
    // STATISTICS - TOTAL STUDENTS
    // =========================

    public int getTotalStudents() {

        String sql =
                "SELECT COUNT(*) FROM students";

        try (
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet resultSet =
                    statement.executeQuery()
        ) {

            if (resultSet.next()) {

                return resultSet.getInt(1);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to get total number of students."
            );

            e.printStackTrace();
        }

        return 0;
    }


    // =========================
    // STATISTICS - COUNT BY YEAR
    // =========================

    public int getStudentCountByYear(
            int year) {

        String sql =
                "SELECT COUNT(*) FROM students " +
                "WHERE year = ?";

        try (
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    year
            );

            try (
                ResultSet resultSet =
                        statement.executeQuery()
            ) {

                if (resultSet.next()) {

                    return resultSet.getInt(1);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to get student count by year."
            );

            e.printStackTrace();
        }

        return 0;
    }


    // =========================
    // STATISTICS - COUNT BY COURSE
    // =========================

    public Map<String, Integer>
            getStudentCountByCourse() {

        Map<String, Integer> courseCounts =
                new LinkedHashMap<>();

        String sql =
                "SELECT course, COUNT(*) AS student_count " +
                "FROM students " +
                "GROUP BY course " +
                "ORDER BY course";

        try (
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet resultSet =
                    statement.executeQuery()
        ) {

            while (resultSet.next()) {

                String course =
                        resultSet.getString(
                                "course"
                        );

                int count =
                        resultSet.getInt(
                                "student_count"
                        );

                courseCounts.put(
                        course,
                        count
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to get student count by course."
            );

            e.printStackTrace();
        }

        return courseCounts;
    }


    // =========================
    // HELPER METHOD
    // =========================

    private Student createStudentFromResultSet(
            ResultSet resultSet)
            throws SQLException {

        int id =
                resultSet.getInt("id");

        String name =
                resultSet.getString("name");

        String email =
                resultSet.getString("email");

        String course =
                resultSet.getString("course");

        int year =
                resultSet.getInt("year");

        return new Student(
                id,
                name,
                email,
                course,
                year
        );
    }
}