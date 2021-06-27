package com.company;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException {
//        Class<User> userClass = User.class;
//        for (Field declaredField : userClass.getDeclaredFields()) {
//            if (declaredField.isAnnotationPresent(Value.class)) {
//                System.out.println(declaredField.getName());
//            }
//        }

        Class<User> userClass = User.class;
        for (Field declaredField : userClass.getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(Value.class)) {
                String value = declaredField.getDeclaredAnnotation(Value.class).value();
                System.out.println(value);
            }
        }

//        Class<User> a = User.class;
//
//        Constructor<?>[] declaredConstructors = a.getDeclaredConstructors();
//        Constructor<?> declaredConstructor = declaredConstructors[0];
//        Object o = declaredConstructor.newInstance();
//
//        Field name = a.getDeclaredField("name");
//        name.setAccessible(true);
//        name.set(o, "Test");
//
//        Method sayHello = a.getDeclaredMethod("sayHello");
//        sayHello.invoke(o);

//        Class<Calculator> a = Calculator.class;

//        Constructor<?>[] declaredConstructors = a.getDeclaredConstructors();
//        Constructor<?> declaredConstructor = declaredConstructors[0];

//        Object o = declaredConstructor.newInstance();

//        Field a1 = a.getDeclaredField("a");
//        Field b = a.getDeclaredField("b");
//        a1.setAccessible(true);
//        b.setAccessible(true);
//        a1.set(o, 3);
//        b.set(o, 4);


//        Method sum = a.getDeclaredMethod("sum");
//        Method sub = a.getDeclaredMethod("sub");
//        Method div = a.getDeclaredMethod("div");

//        int invoke = (int) sum.invoke(o);
//        System.out.println(invoke);


    }
}
