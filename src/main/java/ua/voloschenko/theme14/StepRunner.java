package ua.voloschenko.theme14;

import java.lang.reflect.*;
import java.util.*;

public class StepRunner {

    public static void run(Object target) {
        Class<?> clazz = target.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> stepMethods = new ArrayList<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Step.class)) {
                if (method.getReturnType() != void.class || method.getParameterCount() != 0) {
                    throw new StepRunnerException("Error: " + method.getName() + " not a void without arguments");
                }
                stepMethods.add(method);
            }
        }

        stepMethods.sort(Comparator.comparingInt(m -> m.getAnnotation(Step.class).order()));

        for (Method method : stepMethods) {
            try {
                method.setAccessible(true);
                method.invoke(target);
            } catch (InvocationTargetException e) {
                throw new StepRunnerException("InvocationTargetException " + method.getName(), e.getCause());
            } catch (IllegalAccessException e) {
                throw new StepRunnerException("IllegalAccessException " + method.getName(), e);
            }
        }
    }
}