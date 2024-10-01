package com.ava.Networking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class JobFetchController {

    private static final Logger logger = LoggerFactory.getLogger(JobFetchController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/api/jobFetch")
    public List<Map<String, Object>> getAllJobs() {
        logger.info("Fetching all jobs");
        String sql = "SELECT " +
                     "j.id, j.title, j.description, j.location, j.salary, j.created_at, " +
                     "c.name AS company_name " +
                     "FROM jobs j " +
                     "JOIN companies c ON j.user_id = c.user_id ";
        List<Map<String, Object>> jobs = jdbcTemplate.queryForList(sql);
        logger.info("Fetched {} jobs", jobs.size());
        return jobs;
    }
}