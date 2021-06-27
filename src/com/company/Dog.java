package com.company;

public class Dog {

    @Value("Dog")
    private String name;

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}
