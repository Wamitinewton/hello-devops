package com.newton.hellodevops.model;

public class Student {

    private Long id;
    private String name;
    private String email;
    private int age;
    private String course;

    public Student(Long id, String name, String email, int age, String course) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.course = course;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public String getCourse() { return course; }
}
