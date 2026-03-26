package anotation.entities;

import config.JDBCConfig;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CRUDRepository {
    public <T> List<T> findAll(Class<T> clazz) {
        List<T> list = new ArrayList<>();

        try (Connection conn = JDBCConfig.getConnection();
             Statement st = conn.createStatement()) {
            Entity entity = clazz.getAnnotation(Entity.class);
            if (entity == null) {
                throw new RuntimeException("Entity not found");
            }
            String tableName = entity.table();
            String sql = "SELECT * FROM " + tableName;
            ResultSet rs = st.executeQuery(sql);

            Field[] fields = clazz.getDeclaredFields();
            while (rs.next()) {
                T obj = clazz.getDeclaredConstructor().newInstance();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Column column = field.getAnnotation(Column.class);
                    if (column == null) continue;
                    String columnName = column.name();
                    Object value = rs.getObject(columnName);
                    field.set(obj, value);
                }
                list.add(obj);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}