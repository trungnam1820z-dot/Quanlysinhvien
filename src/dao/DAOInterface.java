package dao;

import java.io.IOException;
import java.util.List;

public interface DAOInterface<T> {
    void insert(List<T> t) throws IOException;
    List<T> getAll() throws IOException;
    T getById(String id) throws IOException;
    void update(T t) throws IOException;
    void deleteById(String id) throws IOException;
    boolean existsById(String id) throws IOException;
    List<T> getAllToPage(int page, int pageSize) throws IOException;
    int count() throws IOException;
}
