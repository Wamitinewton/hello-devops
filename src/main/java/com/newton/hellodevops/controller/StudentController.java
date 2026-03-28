package com.newton.hellodevops.controller;

import com.newton.hellodevops.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @GetMapping
    public List<Student> getStudents() {
        return List.of(
                new Student(1L, "Alice Johnson", "alice.johnson@example.com", 20, "Computer Science"),
                new Student(2L, "Bob Smith", "bob.smith@example.com", 22, "Software Engineering"),
                new Student(3L, "Carol White", "carol.white@example.com", 21, "Data Science"),
                new Student(4L, "David Brown", "david.brown@example.com", 23, "Cybersecurity"),
                new Student(5L, "Eva Martinez", "eva.martinez@example.com", 19, "Information Technology")
        );
    }
}
