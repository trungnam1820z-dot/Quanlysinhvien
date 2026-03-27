import java.util.HashMap;
import java.util.Map;

public class IoCContainer {
    Map<Class<?>, Object> map = new HashMap<>();

    public <T> void register(Class<T> clazz, T obj) {
        map.put(clazz, obj);
    }
    public <T> T get(Class<T> clazz) {
        return clazz.cast(map.get(clazz));
    }
}
