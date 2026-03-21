package util;

import dto.Student;

import java.io.*;
import java.util.List;

public class File_DSSV {

    public static void savaToFile(List<Student> students) throws IOException {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("students.txt"));
            out.writeObject(students);
    }
    public static List<Student> readFromFile(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("students.txt"));
        return (List<Student>) in.readObject();
    }
}
