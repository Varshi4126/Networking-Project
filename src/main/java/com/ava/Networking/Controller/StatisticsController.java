package com.ava.Networking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class StatisticsController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/api/company-statistics")
    public List<Map<String, Object>> getCompanyStatistics() {
        String sql = "SELECT " +
            "c.name AS company_name, " +
            "c.jobs_count AS job_count, " +
            "c.posts_count AS post_count " +
            "FROM " +
            "companies c " +
            "JOIN " +
            "users u ON c.user_id = u.user_id " +
            "WHERE " +
            "u.user_type = 'company'";

        return jdbcTemplate.queryForList(sql);
    }
}