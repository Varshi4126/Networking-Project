package com.ava.Networking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ava.Networking.Model.Job;
import com.ava.Networking.Model.User;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findAllByOrderByCreatedAtDesc();
    List<Job> findByCompanyOrderByCreatedAtDesc(User company);
}