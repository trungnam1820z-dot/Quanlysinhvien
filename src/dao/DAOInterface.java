package dao;

import java.io.IOException;
import java.util.List;

public interface DAOInterface<T> {
    public void insert(T t) throws IOException;
    public List<T> getAll() throws IOException;
    public T getById(String id) throws IOException;
    public void update(T t) throws IOException;
    public void deleteById(String id) throws IOException;

}
