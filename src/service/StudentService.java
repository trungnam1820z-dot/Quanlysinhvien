package service;

import anotation.entities.CRUDRepository;
import anotation.transactional.Transactional;
import dto.Student;
import dao.StudentDAO;
import dto.Page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private final StudentDAO studentDAO;
    private CRUDRepository crudRepository = new CRUDRepository();

    public StudentService(StudentDAO studentDAO){
        this.studentDAO = studentDAO;
    }
    @Transactional
    public void addStudent(Student student) throws IOException {
        List<Student> list = new ArrayList<>();
        list.add(student);
        studentDAO.insert(list);
    }

    public List<Student> getAllStudent() {
        return crudRepository.findAll(Student.class);
    }

    public Student findStudentById(String id) throws IOException {
        return studentDAO.getById(id);
    }
    @Transactional
    public void updateStudent(Student student) throws IOException {
        if (!studentDAO.existsById(student.getStudentID())) {
            System.out.println("ID không tồn tại");
            return;
        }
        studentDAO.update(student);
    }
    @Transactional
    public void deleteStudent(String id) throws IOException {
        if (!studentDAO.existsById(id)) {
            System.out.println("Không tồn tại sinh viên");
        }
        studentDAO.deleteById(id);
    }
    public Page<Student> getStudents(int page, int pageSize) {
        try {
            if (page <= 0) page = 1;
            if (pageSize <= 0) pageSize = 10;
            int total = studentDAO.count();
            int offset = (page - 1) * pageSize;
            List<Student> data = studentDAO.getAllToPage(offset, pageSize);
            return new Page<>(data, page, pageSize, total);
    }
        catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách sinh viên", e);
        }
    }
}
