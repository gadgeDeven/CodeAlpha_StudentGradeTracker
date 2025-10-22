package com.codealpha.studentgradetracker.controller;

import com.codealpha.studentgradetracker.entity.Student;
import com.codealpha.studentgradetracker.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Home: List all students
    @GetMapping
    public String listStudents(Model model) {
        List<Student> students = studentService.getAllWithSummary();
        model.addAttribute("students", students);
        return "students";  // students.jsp
    }

    // Add form
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("student", new Student());
        return "index";  // index.jsp
    }

    // Save student (full try-catch around parsing & validation)
    @PostMapping("/add")
    public String saveStudent(@Valid @ModelAttribute Student student, BindingResult result,
                              @RequestParam(value = "gradesString", required = false) String gradesString,
                              RedirectAttributes redirectAttributes, Model model) {
        // Name validation first (from @Valid)
        if (result.hasErrors()) {
            model.addAttribute("error", "Please fix the errors below (e.g., name required).");
            model.addAttribute("gradesString", gradesString);  // Preserve grades input
            return "index";
        }

        // Grades parsing & validation in try-catch
        List<Integer> grades = new ArrayList<>();
        if (gradesString != null && !gradesString.isEmpty()) {
            try {
                String[] gradesArray = gradesString.split(",");
                for (String g : gradesArray) {
                    int grade = Integer.parseInt(g.trim());
                    if (grade < 0 || grade > 100) {
                        throw new IllegalArgumentException("Grades must be between 0 and 100");
                    }
                    grades.add(grade);
                }
            } catch (NumberFormatException e) {
                model.addAttribute("error", "Invalid grades: Please enter numbers only (e.g., 85,92)");
                model.addAttribute("student", student);
                model.addAttribute("gradesString", gradesString);
                return "index";
            } catch (IllegalArgumentException e) {  // Specific catch for range
                model.addAttribute("error", e.getMessage());
                model.addAttribute("student", student);
                model.addAttribute("gradesString", gradesString);
                return "index";
            }
        } else {
            model.addAttribute("error", "Grades are required!");
            model.addAttribute("student", student);
            return "index";
        }

        // Set grades & check @NotEmpty (now works with validation starter)
        student.setGrades(grades);
        if (student.getGrades().isEmpty()) {
            model.addAttribute("error", "At least one grade is needed!");
            model.addAttribute("student", student);
            model.addAttribute("gradesString", gradesString);
            return "index";
        }

        // Save if all good
        try {
            studentService.saveStudent(student);
            redirectAttributes.addFlashAttribute("success", "Student added successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Unexpected error saving: " + e.getMessage());
            model.addAttribute("student", student);
            model.addAttribute("gradesString", gradesString);
            return "index";
        }
        return "redirect:/students";
    }

    // Edit form
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        model.addAttribute("student", student);
        model.addAttribute("gradesString", student.getGrades().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",")));
        return "edit-student";
    }

    // Update (similar structure)
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, @Valid @ModelAttribute Student student, BindingResult result,
                                @RequestParam(value = "gradesString", required = false) String gradesString,
                                RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "Please fix the errors below.");
            model.addAttribute("gradesString", gradesString);
            return "edit-student";
        }

        List<Integer> grades = new ArrayList<>();
        if (gradesString != null && !gradesString.isEmpty()) {
            try {
                String[] gradesArray = gradesString.split(",");
                for (String g : gradesArray) {
                    int grade = Integer.parseInt(g.trim());
                    if (grade < 0 || grade > 100) {
                        throw new IllegalArgumentException("Grades must be between 0 and 100");
                    }
                    grades.add(grade);
                }
            } catch (NumberFormatException e) {
                model.addAttribute("error", "Invalid grades: Please enter numbers only");
                model.addAttribute("gradesString", gradesString);
                return "edit-student";
            } catch (IllegalArgumentException e) {
                model.addAttribute("error", e.getMessage());
                model.addAttribute("gradesString", gradesString);
                return "edit-student";
            }
        } else {
            model.addAttribute("error", "Grades are required!");
            return "edit-student";
        }

        student.setGrades(grades);
        if (student.getGrades().isEmpty()) {
            model.addAttribute("error", "At least one grade is needed!");
            model.addAttribute("gradesString", gradesString);
            return "edit-student";
        }

        try {
            studentService.updateStudent(id, student);
            redirectAttributes.addFlashAttribute("success", "Student updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Unexpected error updating: " + e.getMessage());
        }
        return "redirect:/students";
    }

    // Delete
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            studentService.deleteStudent(id);
            redirectAttributes.addFlashAttribute("success", "Student deleted successfully!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/students";
    }

    // Report summary
    @GetMapping("/report")
    public String report(Model model) {
        List<Student> students = studentService.getAllWithSummary();
        model.addAttribute("students", students);
        return "report";  // report.jsp
    }

    // REST API for Postman (JSON)
    @RestController
    @RequestMapping("/api/students")
    public class StudentApiController {
        @GetMapping("/{id}/summary")
        public Student getSummary(@PathVariable Long id) {
            return studentService.getStudentById(id).orElse(null);
        }
    }
}