package com.ava.Networking.Model;

public class JobCreationRequest {
	private String username;
    private String title;
    private String desc;
    private String loc;
    private Double salary;

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }
    public String getLoc() { return loc; }
    public void setLoc(String loc) { this.loc = loc; }
    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }
    
    
}
