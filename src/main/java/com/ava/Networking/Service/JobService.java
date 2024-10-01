package com.ava.Networking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ava.Networking.Model.Job;
import com.ava.Networking.Model.User;
import com.ava.Networking.Repository.JobRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job createJob(User company, String title, String desc, String location, Double salary) {
        Job job = new Job();
        job.setCompany(company);
        job.setTitle(title);
        job.setLocation(location);
        job.setSalary(salary);
        job.setDescription(desc);
        job.setCreatedAt(LocalDateTime.now());
        
        return jobRepository.save(job);
    }

    public List<Job> getRecentJobs() {
        return jobRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Job> getRecentJobsByUser(User company) {
        return jobRepository.findByCompanyOrderByCreatedAtDesc(company);
    }
}