package com.example.partb;

import com.example.partb.dao.StudentDAO;
import com.example.partb.dao.StudentDAOImpl;
import com.example.partb.model.Student;
import com.example.partb.util.HibernateUtil;

import java.util.List;

/**
 * Main class to demonstrate Hibernate CRUD Operations for Student
 * Part B: Hibernate Application for Student CRUD Operations
 */
public class PartBMain {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("Part B: Hibernate Student CRUD Demo");
        System.out.println("========================================\n");
        
        StudentDAO studentDAO = new StudentDAOImpl();
        
        try {
            // CREATE - Add new students
            System.out.println("\n--- CREATE: Adding Students ---");
            Student student1 = new Student("Alice", "Johnson", "alice@example.com", 20, "Computer Science");
            Student student2 = new Student("Bob", "Smith", "bob@example.com", 22, "Mathematics");
            Student student3 = new Student("Charlie", "Brown", "charlie@example.com", 21, "Computer Science");
            Student student4 = new Student("Diana", "Wilson", "diana@example.com", 23, "Physics");
            
            studentDAO.saveStudent(student1);
            studentDAO.saveStudent(student2);
            studentDAO.saveStudent(student3);
            studentDAO.saveStudent(student4);
            
            // READ - Get all students
            System.out.println("\n--- READ: Fetching All Students ---");
            List<Student> allStudents = studentDAO.getAllStudents();
            allStudents.forEach(System.out::println);
            
            // READ - Get student by ID
            System.out.println("\n--- READ: Fetching Student by ID ---");
            if (!allStudents.isEmpty()) {
                Long studentId = allStudents.get(0).getId();
                Student retrievedStudent = studentDAO.getStudentById(studentId);
                System.out.println("Retrieved: " + retrievedStudent);
            }
            
            // READ - Get students by course
            System.out.println("\n--- READ: Fetching Students by Course ---");
            List<Student> csStudents = studentDAO.getStudentsByCourse("Computer Science");
            csStudents.forEach(System.out::println);
            
            // READ - Get student by email
            System.out.println("\n--- READ: Fetching Student by Email ---");
            Student studentByEmail = studentDAO.getStudentByEmail("bob@example.com");
            if (studentByEmail != null) {
                System.out.println("Found: " + studentByEmail);
            }
            
            // UPDATE - Modify student details
            System.out.println("\n--- UPDATE: Updating Student Details ---");
            if (!allStudents.isEmpty()) {
                Student studentToUpdate = allStudents.get(0);
                studentToUpdate.setAge(21);
                studentToUpdate.setCourse("Data Science");
                studentDAO.updateStudent(studentToUpdate);
                
                // Verify update
                Student updatedStudent = studentDAO.getStudentById(studentToUpdate.getId());
                System.out.println("After Update: " + updatedStudent);
            }
            
            // DELETE - Remove a student
            System.out.println("\n--- DELETE: Removing a Student ---");
            if (allStudents.size() > 1) {
                Long studentIdToDelete = allStudents.get(1).getId();
                studentDAO.deleteStudent(studentIdToDelete);
                
                // Verify deletion
                System.out.println("\n--- Remaining Students After Deletion ---");
                List<Student> remainingStudents = studentDAO.getAllStudents();
                remainingStudents.forEach(System.out::println);
            }
            
            System.out.println("\n========================================");
            System.out.println("Part B Demo Completed Successfully!");
            System.out.println("========================================");
            
        } catch (Exception e) {
            System.err.println("Error occurred during operations:");
            e.printStackTrace();
        } finally {
            // Shutdown Hibernate
            HibernateUtil.shutdown();
        }
    }
}

