package com.example.partb.dao;

import com.example.partb.model.Student;
import java.util.List;

/**
 * Data Access Object interface for Student CRUD operations
 */
public interface StudentDAO {
    
    // Create
    void saveStudent(Student student);
    
    // Read
    Student getStudentById(Long id);
    List<Student> getAllStudents();
    
    // Update
    void updateStudent(Student student);
    
    // Delete
    void deleteStudent(Long id);
    
    // Additional query methods
    List<Student> getStudentsByCourse(String course);
    Student getStudentByEmail(String email);
}

