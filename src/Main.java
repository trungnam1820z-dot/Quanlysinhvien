import dto.Student;
import logger.MyLogger;
import service.StudentService;
import util.File_DSSV;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        StudentService studentService = new StudentService();
        MyLogger logger = new MyLogger();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Hiển thị danh sách");
            System.out.println("3. Tìm theo ID");
            System.out.println("4. Cập nhật thông tinh sinh viên");
            System.out.println("5. Xóa sinh viên");
//            System.out.println("6. Lưu file");
//            System.out.println("7. Đọc file");
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
                    logger.log("Notice", "Đã thêm sinh viên vào database");
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
                    if (student == null) {
                        System.out.println("Không tìm thấy sinh viên");
                    } else {
                        System.out.print("Name mới: ");
                        String name = sc.nextLine();

                        System.out.print("Age mới: ");
                        int age = Integer.parseInt(sc.nextLine());

                        System.out.print("Gender mới: ");
                        String gender = sc.nextLine();

                        Student newStudent = new Student(cid, name, age, gender);
                        studentService.updateStudent(newStudent);
                        logger.log("Notice", "Cập nhật thông tin thành công");
                        break;
                    }
                case 5:
                    System.out.print("Nhập ID cần xóa: ");
                    studentService.deleteStudent(sc.nextLine());
                    logger.log("Notice", "Đã Xóa Sinh Viên");
                    break;
//                case 6:
//                    File_DSSV.savaToFile(studentService.getStudents());
//                    logger.log("Notice" , "Đã lưu dữ liệu vào File");
//                    break;
//                case 7:
//                    List<Student> list =  File_DSSV.readFromFile("students.txt");
//                    if(list!=null){
//                        studentService.setAll(list);
//                        logger.log("Notice: ", "Đã đọc file ");
//                    }
                case 0:
                    System.exit(0);
                    System.out.println("Chương Trình Kết Thúc");
            }
        }
    }
}