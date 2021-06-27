package com.company;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.*;

public class Factory {

    private Map<String, Object> container = new HashMap<>();

    public static void main(String[] args) {
        Factory factory = new Factory();
        Cat factory2 = factory.getInstance(Cat.class);
        Dog factory22 = factory.getInstance(Dog.class);
        User user = factory.getInstance(User.class);
        System.out.println(user);
        factory.close();


//        Class<? extends Factory> aClass = factory.getClass();
//        Class<Factory> factoryClass = Factory.class;
//
//
//        Calculator calculator = factory.get(Calculator.class);
//        System.out.println(calculator);
    }


    public void close() {
        container.clear();

    }


    public <T> T getInstance(Class<T> clazz) {
        Object o = null;
        try {
            for (Constructor<?> declaredConstructor : clazz.getDeclaredConstructors()) {
                if (declaredConstructor.getParameterCount() != 0) {
                    List<Object> a = new ArrayList<>();
                    for (Parameter parameter : declaredConstructor.getParameters()) {
                        if (parameter.isAnnotationPresent(Value.class)) {
                            Object ab = getValue(parameter);
                            a.add(ab);
                        } else {
                            String name = parameter.getType().getName();
                            a.add(container.get(name));
                        }
                    }
                    o = declaredConstructor.newInstance(a.toArray(new Object[0]));
                    for (Field declaredField : clazz.getDeclaredFields()) {
                        if (declaredField.isAnnotationPresent(Autowired.class)) {
                            String name = declaredField.getType().getName();
                            declaredField.setAccessible(true);
                            declaredField.set(o, container.get(name));
                        }
                        if (declaredField.isAnnotationPresent(Value.class)) {
                            String value = declaredField.getAnnotation(Value.class).value();
                            declaredField.setAccessible(true);
                            declaredField.set(o, value);
                        }
                    }

                } else {
                    o = declaredConstructor.newInstance();
                    for (Field declaredField : clazz.getDeclaredFields()) {
                        if (declaredField.isAnnotationPresent(Value.class)) {
                            String value = declaredField.getDeclaredAnnotation(Value.class).value();
                            declaredField.setAccessible(true);
                            declaredField.set(o, value);
                        }
                    }
                }
            }
            container.put(clazz.getName(), o);
            return (T) o;
        } catch (Exception e) {

        }
        return null;
    }

    private Object getValue(Parameter parameter) {
        if (parameter.getType().equals(String.class)) {
            return parameter.getDeclaredAnnotation(Value.class).value();
        } else if (parameter.getType().equals(Integer.class)) {
            return Integer.parseInt(parameter.getDeclaredAnnotation(Value.class).value());
        }
        return null;
    }

    public <T> T get(Class<T> clazz) {
        Object o = null;
        try {
            for (Constructor<?> declaredConstructor : clazz.getDeclaredConstructors()) {
                if (declaredConstructor.getParameterCount() != 0) {
                    o = declaredConstructor.newInstance();
                    break;
                }
            }
            for (Field declaredField : clazz.getDeclaredFields()) {
                if (declaredField.isAnnotationPresent(Value.class)) {
                    String value = declaredField.getDeclaredAnnotation(Value.class).value();
                    declaredField.setAccessible(true);
                    declaredField.set(o, value);
                }
            }
            return (T) o;
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public Object get(Class<?> clazz, Object... arg) {
//        Class<?>[] a = new Class[arg.length];
//        for (int i = 0; i < arg.length; i++) {
//            a[i] = arg[i].getClass();
//        }
//        try {
//            for (Constructor<?> declaredConstructor : clazz.getDeclaredConstructors()) {
//                if (Arrays.equals(declaredConstructor.getParameterTypes(), a)) {
//                    return declaredConstructor.newInstance(arg);
//                }
//            }
//        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    public <T> List<T> get(Class<T> clazz, int size, Object... arg) {
//        List<Object> all = new ArrayList<>();
//        Class<?>[] a = new Class[arg.length];
//        for (int i = 0; i < arg.length; i++) {
//            a[i] = arg[i].getClass();
//        }
//        try {
//            for (Constructor<?> declaredConstructor : clazz.getDeclaredConstructors()) {
//                if (Arrays.equals(declaredConstructor.getParameterTypes(), a)) {
//                    for (int i = 0; i < size; i++) {
//                        all.add(declaredConstructor.newInstance(arg));
//                    }
//                }
//            }
//            return (List<T>) all;
//        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
