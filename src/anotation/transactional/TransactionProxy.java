package anotation.transactional;

import config.JDBCConfig;

import java.lang.reflect.*;
import java.sql.Connection;

public class TransactionProxy implements InvocationHandler {
    private final Object target;
    public TransactionProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Method realMethod = target.getClass()
                .getMethod(method.getName(), method.getParameterTypes());

        if (!realMethod.isAnnotationPresent(Transactional.class)) {
            return method.invoke(target, args);
        }
        Connection conn;
        try {
            conn = JDBCConfig.getConnection();
            TransactionManager.begin(conn);
            Object result = method.invoke(target, args);
            TransactionManager.commit();
            return result;
        } catch (Exception e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            TransactionManager.close();
        }
    }
}
