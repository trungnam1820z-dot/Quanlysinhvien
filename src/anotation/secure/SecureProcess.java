package anotation.secure;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.Scanner;

public class SecureProcess {
    public static void invoke(Object object, String methodName) throws Exception {
        Method method = object.getClass().getMethod(methodName);
        if(method.isAnnotationPresent(Secure.class)) {
            Secure secure = method.getAnnotation(Secure.class);
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập UserName: ");
            String userName = sc.nextLine();
            System.out.print("Nhập password: ");
            String password = sc.nextLine();
            if(!secure.userName().equals(userName) || !secure.password().equals(password)) {
                System.out.println("Sai Thông tin đăng nhập!");
                return;
            }
            System.out.println("Đăng nhập thành công");
        }
        method.invoke(object);
    }
}
