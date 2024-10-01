package com.ava.Networking.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ava.Networking.Model.Job;
import com.ava.Networking.Model.User;
import com.ava.Networking.Service.JobService;
import com.ava.Networking.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JobService jobService;
    

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody JobCreationRequest jobRequest) {
        logger.info("Received job creation request for user: " + jobRequest.getUsername());

        User author = userService.findByUsername(jobRequest.getUsername());

        if (author == null) {
            logger.error("User not found: " + jobRequest.getUsername());
            return ResponseEntity.badRequest().body(null);
        }

        Job createdJob = jobService.createJob(
            author, 
            jobRequest.getTitle(), 
            jobRequest.getDesc(), 
            jobRequest.getLocation(), 
            jobRequest.getSalary()
        );

        return ResponseEntity.ok(createdJob);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<Job>> getRecentJobs() {
        List<Job> recentJobs = jobService.getRecentJobs();
        return ResponseEntity.ok(recentJobs);
    }

    @GetMapping("/company/{username}")
    public ResponseEntity<List<Job>> getRecentJobsByCompany(@PathVariable String username) {
        User company = userService.findByUsername(username);
        if (company == null) {
            return ResponseEntity.notFound().build();
        }
        List<Job> companyJobs = jobService.getRecentJobsByUser(company);
        return ResponseEntity.ok(companyJobs);
    }
}

// Add this class at the end of the file or in a separate file
class JobCreationRequest {
    private String username;
    private String title;
    private String desc;
    private String location;
    private Double salary;

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }
}