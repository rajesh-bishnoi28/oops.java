package controller;

import org.springframework.web.bind.annotation.*;

import model.Student;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    private final List<Student> students = new ArrayList<>();

    public StudentController() {
        students.add(new Student(1L, "Alice Johnson", "alice@example.com", "Computer Science"));
        students.add(new Student(2L, "Bob Smith", "bob@example.com", "Mathematics"));
        students.add(new Student(3L, "Charlie Brown", "charlie@example.com", "Physics"));
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return students;
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return students.stream()
                .filter(student -> student.getStudentId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            Student current = students.get(i);
            if (current.getStudentId().equals(id)) {
                updatedStudent.setStudentId(id);
                students.set(i, updatedStudent);
                return updatedStudent;
            }
        }
        return null;
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable Long id) {
        students.removeIf(student -> student.getStudentId().equals(id));
    }
}