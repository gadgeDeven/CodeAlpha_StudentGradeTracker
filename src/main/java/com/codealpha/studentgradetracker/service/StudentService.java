package com.codealpha.studentgradetracker.service;

import com.codealpha.studentgradetracker.entity.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Long id);
    Student saveStudent(Student student);
    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);
    // Extra for report
    List<Student> getAllWithSummary();
}