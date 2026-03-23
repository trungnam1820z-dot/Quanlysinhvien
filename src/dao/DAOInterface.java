package dao;

import java.io.IOException;
import java.util.List;

public interface DAOInterface<T> {
    public void insertStudent(T t) throws IOException;
    public List<T> getAllStudents() throws IOException;
    public T getStudentById(String id) throws IOException;
    public void updateStudent(T t) throws IOException;
    public void deleteStudentById(String id) throws IOException;

}
