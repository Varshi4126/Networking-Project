package com.ava.Networking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ava.Networking.Model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);
    
    @Query("SELECT s FROM Student s WHERE LOWER(CAST(s.name AS string)) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(CAST(s.headline AS string)) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Student> searchStudents(@Param("query") String query);

	Student findByName(String username);
}