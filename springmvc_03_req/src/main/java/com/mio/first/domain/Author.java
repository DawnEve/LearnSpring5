package com.mio.first.domain;

public class Author {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Author[name: %s, age: %d]", name, age);
    }
}
