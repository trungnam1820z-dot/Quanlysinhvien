package dao;

import config.JDBCConfig;
import dto.Student;
import logger.MyLogger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements DAOInterface<Student> {
    MyLogger logger = new MyLogger();
    private Connection connection;
    public StudentDAO() throws IOException {
    }
    public StudentDAO(Connection connection) throws IOException {
        this.connection = connection;
    }
    @Override
    public void insert(List<Student> list) throws IOException {
        String sql = "INSERT INTO student VALUES (?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCConfig.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            int batchSize = 4;
            int count = 0;
            for (Student student : list) {
                ps.setString(1, student.getStudentID());
                ps.setString(2, student.getStudentName());
                ps.setInt(3, student.getAge());
                ps.setString(4, student.getGender());
                ps.addBatch();
                count++;
                if (count % batchSize == 0) {
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
            ps.executeBatch();
            conn.commit();
            System.out.println("Successfully inserted student into table");
        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (Exception ex) {
                logger.log("Error", "Lỗi SQL khi rollback");
            }
            logger.log("Error", "Lỗi SQL khi thêm sinh viên");
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                logger.log("Error","Lỗi khi đóng kết nối batch và SQL");
            }
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
    public synchronized void update(Student student) throws IOException {
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
    @Override
    public boolean existById(String id) throws IOException {
        String sql = "SELECT 1 FROM student WHERE id = ?";
        try (Connection conn = JDBCConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id.trim());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            logger.log("Error", "Lỗi SQL check tồn tại bản ghi");
        }
        return false;
    }
    @Override
    public List<Student> getAllToPage(int offset, int limit) throws IOException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM student LIMIT ? OFFSET ?";
        try(Connection conn = JDBCConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Student s = new Student(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender")
                );
                list.add(s);
            }
        }catch (SQLException e){
            logger.log("Error", "Lỗi SQL khi lấy dữ liệu");
        }
        return list;
    }

    @Override
    public int count() throws IOException{
        String sql = "SELECT COUNT(*) FROM student";
        try(Connection conn = JDBCConfig.getConnection();
            Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
        }catch (SQLException e){
            logger.log("Error", "Lỗi SQL khi lấy dữ liệu");
        }
        return 0;
    }

//    @Override
//    public void insertBatch(List<Student> list) throws IOException {
//        String sql = "INSERT INTO student VALUES (?, ?, ?, ?)";
//        try(Connection conn = JDBCConfig.getConnection();
//        PreparedStatement ps = conn.prepareStatement(sql)) {
//            conn.setAutoCommit(false);
//            for (Student student : list) {
//                ps.setString(1, student.getStudentID());
//                ps.setString(2, student.getStudentName());
//                ps.setInt(3, student.getAge());
//                ps.setString(4, student.getGender());
//                ps.addBatch();
//            }
//            int[] result = ps.executeBatch();
//            conn.commit();
//        }
//        catch (Exception e)
//        {
//            logger.log("Error", "Lỗi SQL khi thêm dữ liệu vào 1 batch");
//        }

}
