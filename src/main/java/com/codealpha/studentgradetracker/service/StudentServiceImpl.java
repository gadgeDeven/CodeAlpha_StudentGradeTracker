package com.codealpha.studentgradetracker.service;

import com.codealpha.studentgradetracker.entity.Student;
import com.codealpha.studentgradetracker.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student saveStudent(Student student) {
        // Validation: Check grades range (0-100)
        if (student.getGrades() != null && student.getGrades().stream().anyMatch(g -> g < 0 || g > 100)) {
            throw new IllegalArgumentException("Grades must be between 0 and 100!");
        }
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student not found with id: " + id);
        }
        student.setId(id);
        return saveStudent(student);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAllWithSummary() {
        // Just get all – summary calcs entity getters se honge (avg, highest, lowest)
        return getAllStudents();
    }
}