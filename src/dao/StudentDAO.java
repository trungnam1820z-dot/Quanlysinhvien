package dao;

import Config.JDBCConfig;
import dto.Student;
import logger.MyLogger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements DAOInterface<Student> {
    MyLogger logger = new MyLogger();
    public StudentDAO() throws IOException {
    }
    @Override
    public void insert(Student student) throws IOException {
        String sql = "INSERT INTO student VALUES (?,?,?,?)";
        try(Connection conn = JDBCConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getStudentID());
            ps.setString(2, student.getStudentName());
            ps.setInt(3, student.getAge());
            ps.setString(4, student.getGender());
            int check = ps.executeUpdate();
            if (check > 0) {
                System.out.println("Thêm sinh viên thành công!");
            }
            else {
                System.out.println("Thêm sinh viên thất bại");
            }
        } catch (SQLException e) {
            logger.log("Error", "Lỗi SQL khi thêm sinh viên");
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Student> getAll() throws IOException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try(Connection conn = JDBCConfig.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Student s = new Student(
                rs.getString("ID"),
                rs.getString("Name"),
                rs.getInt("Age"),
                rs.getString("Gender"));
                students.add(s);
            }
            System.out.println("Thông tin sinh viên\n");
            for (Student s : students)
                System.out.println(s);
        }catch (SQLException e){
            logger.log("Error", "Lỗi SQL khi hiển thị thông tin sinh viên");
            throw new RuntimeException(e);
        }
        return students;
    }
@Override
    public Student getById(String id) throws IOException {
        String sql = "SELECT * FROM student WHERE ID = ?";
        try (Connection conn = JDBCConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student student = new Student(
                        rs.getString("id"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getString("Gender")
                );
                System.out.println("Sinh viên có ID: " + id);
                System.out.println(student);
                return student;
            }
        }catch (SQLException e){
            logger.log("Error", "Lỗi SQL khi lấy SV theo id");
            throw new RuntimeException(e);
        }
        return null;
    }
    @Override
    public void update(Student student) throws IOException {
        String sql = "UPDATE student SET Name=?, Age=?, Gender=? WHERE ID=?";
        try(Connection conn = JDBCConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,student.getStudentName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getGender());
            ps.setString(4, student.getStudentID());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Cập nhật thành công");
            }
            else {
                System.out.println("Không tìm thấy sinh viên!");
            }

        }catch (SQLException e) {
            logger.log("Error", "Lỗi SQL khi cập nhật thông tin sinh viên");
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteById(String id) throws IOException {
        String sql = "DELETE FROM student WHERE ID = ?";
        try(Connection conn = JDBCConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, id);
                int row = ps.executeUpdate();
                if (row > 0) {
                    System.out.println("Đã xóa thành công");
                }
                else {
                    System.out.println("Không tìm thấy sinh viên!");
                }
        }catch (SQLException e){
            logger.log("Error", "Lỗi SQL không xóa được sinh viên");
            throw new RuntimeException(e);
        }
    }
}
