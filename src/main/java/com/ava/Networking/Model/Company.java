package com.ava.Networking.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "companies")
public class Company extends User {

    private String name;

    @Column(columnDefinition = "TEXT")
    private String about;

    private int postsCount;

    private int jobsCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public int getPostsCount() {
		return postsCount;
	}

	public void setPostsCount(int postsCount) {
		this.postsCount = postsCount;
	}

	public int getJobsCount() {
		return jobsCount;
	}

	public void setJobsCount(int jobsCount) {
		this.jobsCount = jobsCount;
	}

	public void setUser(User user) {
		// TODO Auto-generated method stub
		
	}

   
}