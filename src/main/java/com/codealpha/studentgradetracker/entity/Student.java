package com.codealpha.studentgradetracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Student name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2-100 characters")
    private String name;

    @ElementCollection
    @CollectionTable(name = "student_grades", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "grade")
    private List<Integer> grades = new ArrayList<>();  // No validation here—controller handles

    // Default constructor
    public Student() {}

    // Parameterized constructor
    public Student(String name, List<Integer> grades) {
        this.name = name;
        this.grades = grades;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public void setGrades(List<Integer> grades) {
        this.grades = grades;
    }

    // Calculation methods
    public double getAverage() {
        if (grades.isEmpty()) return 0.0;
        return grades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public int getHighest() {
        return grades.stream().mapToInt(Integer::intValue).max().orElse(0);
    }

    public int getLowest() {
        return grades.stream().mapToInt(Integer::intValue).min().orElse(0);
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name='" + name + "', grades=" + grades + '}';
    }
}