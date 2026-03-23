package service;

import dto.Student;
import dao.StudentDAO;

import java.io.IOException;

public class StudentService {

    private final StudentDAO studentDAO = new StudentDAO();

    public StudentService() throws IOException {
    }

    public void addStudent(Student student) throws IOException {
        studentDAO.insert(student);
    }

    public void getAllStudent() throws IOException {
        studentDAO.getAll();

    }

    public Student findStudentById(String id) throws IOException {
        return studentDAO.getById(id);
    }

    public void updateStudent(Student student) throws IOException {
        studentDAO.update(student);
    }

    public void deleteStudent(String id) throws IOException {
        studentDAO.deleteById(id);
    }
}
