package ua.voloschenko.theme14;

public class ServiceFactory {

    public static <T> T create(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            return (T) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error creating object for : " + className, e);
        }
    }
}