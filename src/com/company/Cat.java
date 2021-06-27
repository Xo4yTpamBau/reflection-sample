package com.company;

public class Cat {

    @Value("Cat")
    private String name;

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }
}
