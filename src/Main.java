import anotation.secure.SecureProcess;
import dao.StudentDAO;
import dto.Page;
import dto.Student;
import service.StudentService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        StudentDAO studentDAO = new StudentDAO();
        StudentService studentService = new StudentService(studentDAO);
        SecureProcess.invoke(studentService,"getAllStudent");
        Page<Student> page = studentService.getStudents(1, 10);
        System.out.println("Page: " + page.getPage());
        System.out.println("Total Pages: " + page.getTotalPages());
        System.out.println("Total Items: " + page.getTotalItems());
        for (Student s : page.getData()) {
            System.out.println(s.getStudentID() + " - " + s.getStudentName() + " - " + s.getAge() + " - " + s.getGender());
        }
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Hiển thị danh sách");
            System.out.println("3. Tìm theo ID");
            System.out.println("4. Cập nhật thông tinh sinh viên");
            System.out.println("5. Xóa sinh viên");
            System.out.println("0. Thoát");

            System.out.print("Nhập lựa chọn: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("StudentID: ");
                    String studentID = sc.nextLine();
                    System.out.print("StudentName: ");
                    String studentName = sc.nextLine();
                    System.out.print("Age: ");
                    int Age = Integer.parseInt(sc.nextLine());
                    System.out.print("Gender: ");
                    String Gender = sc.nextLine();
                    studentService.addStudent(new Student(studentID, studentName, Age, Gender));
                    System.out.println("Đã thêm vào database");
                    break;
                case 2:
                    studentService.getAllStudent();
                    break;
                case 3:
                    System.out.print("Nhập ID cần tìm: ");
                    String id = sc.nextLine();
                    studentService.findStudentById(id);
                    break;
                case 4:
                    System.out.print("Nhập vào StudentID cần cập nhật thông tin: ");
                    String cid = sc.nextLine();
                    Student student = studentService.findStudentById(cid);
                    if (student != null) {
                        System.out.print("Name mới: ");
                        String name = sc.nextLine();

                        System.out.print("Age mới: ");
                        int age = Integer.parseInt(sc.nextLine());

                        System.out.print("Gender mới: ");
                        String gender = sc.nextLine();

                        Student newStudent = new Student(cid, name, age, gender);
                        studentService.updateStudent(newStudent);
                        System.out.println(newStudent);
                        System.out.println("Cập nhật thông tin thành công");
                    } else {
                        System.out.println("Không tìm thấy sinh viên!");
                    }
                    break;
                case 5:
                    System.out.print("Nhập ID cần xóa: ");
                    studentService.deleteStudent(sc.nextLine());
                    System.out.println("Đã Xóa Sinh Viên");
                    break;
                case 0:
                    System.exit(0);
                    System.out.println("Chương Trình Kết Thúc");
            }
        }
    }
}