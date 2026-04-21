package ua.voloschenko.theme14;

import java.lang.reflect.*;
import java.util.Arrays;

public class ClassInfoPrinter {

    public static void print(Class<?> clazz) {
        System.out.println("Metadata of " + clazz.getSimpleName());

        System.out.println(".getName(): " + clazz.getName());

        Class<?> superclass = clazz.getSuperclass();
        System.out.println(".getSuperclass(): " + (superclass != null ? superclass.getName() : "Nothing"));

        Class<?>[] interfaces = clazz.getInterfaces();
        String[] interfaceNames = Arrays.stream(interfaces)
                .map(Class::getName)
                .toArray(String[]::new);
        System.out.println(".getInterfaces(): " + (interfaces.length > 0 ? Arrays.toString(interfaceNames) : "Not realized"));

        System.out.println("\n.getDeclaredFields");
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length == 0) {
            System.out.println("No fields");
        } else {
            for (Field field : fields) {
                System.out.printf(".getModifiers: %d, .getSimpleName: %s, .getName: %s%n",
                        field.getModifiers(), field.getType().getSimpleName(), field.getName());
            }
        }

        System.out.println("\n.getDeclaredMethods");
        Method[] methods = clazz.getDeclaredMethods();
        if (methods.length == 0) {
            System.out.println("No methods");
        } else {
            for (Method method : methods) {
                System.out.printf(".getReturnType: %s, .getName: %s, .getParameterCount: %d%n",
                        method.getReturnType().getSimpleName(), method.getName(), method.getParameterCount());
            }
        }
    }
}
