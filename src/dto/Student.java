package dto;

import anotation.entities.Column;
import anotation.entities.Entity;

@Entity(table = "student")
public class Student {
    @Column(name = "id")
    private  String studentID;
    @Column(name = "Name")
    private  String studentName;
    @Column(name = "Age")
    private  int Age;
    @Column(name = "Gender")
    private  String Gender;

    public Student() {

    }

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
