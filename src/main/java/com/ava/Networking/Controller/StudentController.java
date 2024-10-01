package com.ava.Networking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.ava.Networking.Model.Student;
import com.ava.Networking.Service.StudentService;
import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student-profile")
    public String studentProfile(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("user");
        if (student == null) {
            return "redirect:/login1";
        }
        model.addAttribute("student", student);
        return "student-profile";
    }

    @PostMapping("/student-profile")
    public String updateStudentProfile(@ModelAttribute Student updatedStudent, HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("user");
        if (student == null) {
            return "redirect:/login1";
        }
        // Update student properties
        student.setName(updatedStudent.getName());
        student.setHeadline(updatedStudent.getHeadline());
        student.setAbout(updatedStudent.getAbout());
        student.setEducationalBackground(updatedStudent.getEducationalBackground());
        student.setSkills(updatedStudent.getSkills());
        student.setLanguages(updatedStudent.getLanguages());
        student.setCertifications(updatedStudent.getCertifications());

        Student updatedStudentProfile = studentService.updateStudentProfile(student);
        session.setAttribute("user", updatedStudentProfile);
        model.addAttribute("student", updatedStudentProfile);
        model.addAttribute("message", "Profile updated successfully!");
        return "student-profile";
    }
}
