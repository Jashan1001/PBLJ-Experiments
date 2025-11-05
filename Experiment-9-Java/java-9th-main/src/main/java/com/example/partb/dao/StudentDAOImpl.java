package com.example.partb.dao;

import com.example.partb.model.Student;
import com.example.partb.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Implementation of StudentDAO using Hibernate
 * Demonstrates CRUD operations with Hibernate
 */
public class StudentDAOImpl implements StudentDAO {
    
    @Override
    public void saveStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            System.out.println("Student saved successfully: " + student.getFirstName());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Failed to save student", e);
        }
    }
    
    @Override
    public Student getStudentById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Student student = session.get(Student.class, id);
            if (student != null) {
                System.out.println("Student found: " + student.getFirstName());
            } else {
                System.out.println("No student found with ID: " + id);
            }
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get student by ID", e);
        }
    }
    
    @Override
    public List<Student> getAllStudents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Student> query = session.createQuery("FROM Student", Student.class);
            List<Student> students = query.list();
            System.out.println("Retrieved " + students.size() + " students");
            return students;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get all students", e);
        }
    }
    
    @Override
    public void updateStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
            System.out.println("Student updated successfully: " + student.getFirstName());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Failed to update student", e);
        }
    }
    
    @Override
    public void deleteStudent(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.delete(student);
                System.out.println("Student deleted successfully with ID: " + id);
            } else {
                System.out.println("No student found with ID: " + id);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Failed to delete student", e);
        }
    }
    
    @Override
    public List<Student> getStudentsByCourse(String course) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Student> query = session.createQuery(
                "FROM Student WHERE course = :course", Student.class);
            query.setParameter("course", course);
            List<Student> students = query.list();
            System.out.println("Found " + students.size() + " students in course: " + course);
            return students;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get students by course", e);
        }
    }
    
    @Override
    public Student getStudentByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Student> query = session.createQuery(
                "FROM Student WHERE email = :email", Student.class);
            query.setParameter("email", email);
            Student student = query.uniqueResult();
            if (student != null) {
                System.out.println("Student found with email: " + email);
            }
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get student by email", e);
        }
    }
}

