package com.codealpha.studentgradetracker.repository;

import com.codealpha.studentgradetracker.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Custom queries if needed (e.g., find by name)
    List<Student> findByNameContainingIgnoreCase(String name);
}