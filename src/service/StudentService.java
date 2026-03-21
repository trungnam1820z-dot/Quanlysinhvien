package service;

import dto.Student;
import DAO.StudentDAO;

import java.io.IOException;

public class StudentService {

    private StudentDAO studentDAO = new StudentDAO();

    public StudentService() throws IOException {
    }

    public void addStudent(Student student) throws IOException {
        studentDAO.insertStudent(student);
    }

    public void getAllStudent() throws IOException {
        studentDAO.getAllStudents();

    }

    public Student findStudentById(String id) throws IOException {
        return studentDAO.getStudentById(id);
    }

    public void updateStudent(Student student) throws IOException {
        studentDAO.updateStudent(student);
    }

    public void deleteStudent(String id) throws IOException {
        studentDAO.deleteStudentById(id);
    }
}
