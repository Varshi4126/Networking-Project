package com.ava.Networking.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ava.Networking.Model.Company;
import com.ava.Networking.Model.Student;
import com.ava.Networking.Model.User;
import com.ava.Networking.Repository.StudentRepository;
import com.ava.Networking.Repository.UserRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }
   

 // You might also want to add these methods for consistency
    public Student findStudentByName(String name) {
        return studentRepository.findByName(name);
    }
    
    public Student updateStudentProfile(Student student) {
        // First, update the User entity
        User user = userRepository.findByEmail(student.getEmail());
        if (user != null) {
            user.setEmail(student.getEmail());
            userRepository.save(user);
        }
        
        Student existingStudent = studentRepository.findById(student.getUserId()).orElse(null);
        if (existingStudent != null) {
            existingStudent.setName(student.getName());
            existingStudent.setHeadline(student.getHeadline());
            existingStudent.setAbout(student.getAbout());
            existingStudent.setEducationalBackground(student.getEducationalBackground());
            existingStudent.setSkills(student.getSkills());
            existingStudent.setLanguages(student.getLanguages());
            existingStudent.setCertifications(student.getCertifications());
            return studentRepository.save(existingStudent);
        }
        // Then, update the Student entity
        return studentRepository.save(student);
    }

    public Student createStudent(User user) {
        Student student = new Student();
        student.setUser(user);
        student.setEmail(user.getEmail());
        return studentRepository.save(student);
    }
    
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> searchStudents(String query) {
        return studentRepository.searchStudents(query);
    }
    
}