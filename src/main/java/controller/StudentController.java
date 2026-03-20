package controller;

import database.DatabaseManager;
import model.Student;

import java.util.List;

public class StudentController {

    private DatabaseManager dbManager = new DatabaseManager();

    public List<Student> getAllStudents() {
        return dbManager.getAllStudents();
    }

    public boolean addStudent(String firstName, String lastName, String email, String course, int year) {
        Student student = new Student(firstName, lastName, email, course, year);
        return dbManager.addStudent(student);
    }

    public boolean updateStudent(Student student) {
        return dbManager.updateStudent(student);
    }

    public boolean deleteStudent(int studentId) {
        return dbManager.deleteStudent(studentId);
    }
}
