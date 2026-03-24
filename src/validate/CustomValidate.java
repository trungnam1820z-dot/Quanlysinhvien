package validate;

import dao.StudentDAO;
import dto.Student;

import java.io.IOException;

public class CustomValidate {
    StudentDAO studentDAO = new StudentDAO();

    public CustomValidate() throws IOException {
    }

    public String CustomValidate(Student student) throws IOException {
        if (student.getStudentID() == null || student.getStudentID().trim().isEmpty())
            return "ID không được để trống";

        if (student.getStudentName() == null || student.getStudentName().trim().isEmpty())
            return "Tên không được để trống";

        if (student.getAge() <= 0 || student.getAge() > 120)
            return "Tuổi không hợp lệ";

        if (!student.getGender().equalsIgnoreCase("Nam") &&
                !student.getGender().equalsIgnoreCase("Nữ"))
            return "Giới tính phải là Nam/Nữ";

        if (studentDAO.existById(student.getStudentID())) {
            return "Student already exists";
        }
        return null;
    }
}
