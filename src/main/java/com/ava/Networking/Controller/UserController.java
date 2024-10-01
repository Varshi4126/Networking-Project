package com.ava.Networking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ava.Networking.Model.User;
import com.ava.Networking.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String email, @RequestParam String password, @RequestParam String userType, Model model) {
        try {
            User user = userService.registerUser(email, password, userType);
            if (user != null) {
                return "redirect:/login1?registered";
            } else {
                model.addAttribute("error", "Registration failed. Please try again.");
                return "register";
            }
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login1")
    public String showLoginForm() {
        return "login1";
    }

    @PostMapping("/login1")
    public String loginUser(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        User user = userService.loginUser(email, password);

        if (user != null) {
            session.setAttribute("user", user);
            if ("student".equals(user.getUserType())) {
                return "redirect:/student-home";
            } else if ("company".equals(user.getUserType())) {
                return "redirect:/company-home";
            }
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login1";
        }

        return "redirect:/login1?error";
    }

    @GetMapping("/delete-account")
    public String deleteAccount(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            userService.deleteUser(user.getUserId());
            session.invalidate();
        }
        return "redirect:/";
    }
}