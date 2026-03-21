package dto;

import java.io.Serializable;

public class Student implements Serializable {
    private final String studentID;
    private final String studentName;
    private final int Age;
    private final String Gender;

    public Student(String studentID, String studentName, int Age, String Gender) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.Age = Age;
        this.Gender = Gender;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getGender() {
        return Gender;
    }

    public int getAge() {
        return Age;
    }

    @Override
    public String toString() {
        return "Student { " +
                "StudentID = '" + studentID + '\'' +
                ", StudentName = '" + studentName + '\'' +
                ", Age = '" + Age + '\'' +
                ", Gender = '" + Gender + '\'' +
                '}';
    }
}
