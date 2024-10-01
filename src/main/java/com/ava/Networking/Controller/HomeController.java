package com.ava.Networking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ava.Networking.Model.Company;
import com.ava.Networking.Model.Connection;
import com.ava.Networking.Model.Notification;
import com.ava.Networking.Model.Student;
import com.ava.Networking.Model.User;
import com.ava.Networking.Service.CompanyService;
import com.ava.Networking.Service.ConnectionService;
import com.ava.Networking.Service.NotificationService;
import com.ava.Networking.Service.StudentService;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class HomeController {
    
    @Autowired
    private StudentService studentService;

    @Autowired
    private CompanyService companyService;
    
    @Autowired
    private ConnectionService connectionService;
    
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "home";
        }
        
        if ("student".equals(user.getUserType())) {
            return "redirect:/student-home";
        } else if ("company".equals(user.getUserType())) {
            return "redirect:/company-home";
        }
        
        return "home";
    }
    

    @GetMapping("/student-home")
    public String studentDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || !"student".equals(user.getUserType())) {
            return "redirect:/login1";
        }
        Student student = studentService.getStudentByEmail(user.getEmail());
        model.addAttribute("student", student);
        return "student-home";
    }

    @GetMapping("/company-home")
    public String companyDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || !"company".equals(user.getUserType())) {
            return "redirect:/login1";
        }
        Company company = companyService.getCompanyByEmail(user.getEmail());
        model.addAttribute("company", company);
        return "company-home";
    }

    @GetMapping("/search")
    @ResponseBody
    public String search(@RequestParam String query, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        List<Student> students = studentService.searchStudents(query);
        List<Company> companies = companyService.searchCompanies(query);

        List<SearchResult> searchResults = Stream.concat(
            students.stream().map(s -> new SearchResult("student", s.getName(), s.getHeadline(), s.getUserId())),
            companies.stream().map(c -> new SearchResult("company", c.getName(), c.getAbout(), c.getUserId()))
        ).collect(Collectors.toList());

        StringBuilder htmlBuilder = new StringBuilder();
        if (searchResults.isEmpty()) {
            htmlBuilder.append("<div class='alert alert-info'>No results found.</div>");
        } else {
            for (SearchResult result : searchResults) {
                Connection.ConnectionStatus status = connectionService.getConnectionStatus(currentUser.getUserId(), result.getUserId());
                htmlBuilder.append("<div class='col'>");
                htmlBuilder.append("<div class='card h-100 shadow-sm'>");
                htmlBuilder.append("<div class='card-body'>");
                htmlBuilder.append("<h5 class='card-title'>").append(result.getName()).append("</h5>");
                htmlBuilder.append("<p class='card-text text-muted'>").append(result.getDescription()).append("</p>");
                String profileUrl = "student".equals(result.getType()) ? 
                    "/student-profile-readonly" : "/company-profile-readonly";
                htmlBuilder.append("<a href='").append(profileUrl).append("?id=").append(result.getUserId())
                    .append("' class='btn btn-outline-primary'>View Profile</a>");
                if ("student".equals(result.getType())) {
                    if (status == null) {
                        htmlBuilder.append("<button class='btn btn-outline-success ms-2 connect-btn' data-user-id='")
                            .append(result.getUserId()).append("' data-url='/connections/connect'>Connect</button>");
                    } else if (status == Connection.ConnectionStatus.PENDING) {
                        htmlBuilder.append("<button class='btn btn-outline-secondary ms-2' disabled>Pending</button>");
                    } else if (status == Connection.ConnectionStatus.CONNECTED) {
                        htmlBuilder.append("<button class='btn btn-outline-secondary ms-2' disabled>Connected</button>");
                    }
                }
                if (status != Connection.ConnectionStatus.FOLLOWING) {
                    htmlBuilder.append("<button class='btn btn-outline-secondary ms-2 follow-btn' data-user-id='")
                        .append(result.getUserId()).append("' data-url='/connections/follow'>Follow</button>");
                } else {
                    htmlBuilder.append("<button class='btn btn-outline-secondary ms-2' disabled>Following</button>");
                }
                htmlBuilder.append("</div></div></div>");
            }
        }
        return htmlBuilder.toString();
    }



    @GetMapping("/student-profile-readonly")
    public String studentProfileReadonly(@RequestParam Long id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("profile", student);
        return "student-profile-readonly";
    }

    @GetMapping("/company-profile-readonly")
    public String companyProfileReadonly(@RequestParam Long id, Model model) {
        Company company = companyService.findById(id);
        model.addAttribute("profile", company);
        return "company-profile-readonly";
    }

    @GetMapping("/logout")
    public String logout() {
          return "redirect:/home";  // Redirecting to /home
    }
    
  

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login1";
        }
 
        List<Notification> notifications = notificationService.getUnreadNotifications(user.getUserId());
        model.addAttribute("notifications", notifications);
        
        return "dashboard";
    }
    
    private static class SearchResult {
        private String type;
        private String name;
        private String description;
        private Long userId;

        public SearchResult(String type, String name, String description, Long userId) {
            this.type = type;
            this.name = name;
            this.description = description;
            this.userId = userId;
        }

        // Getters
        public String getType() { return type; }
        public String getName() { return name; }
        public String getDescription() { return description; }
        public Long getUserId() { return userId; }
    }
}