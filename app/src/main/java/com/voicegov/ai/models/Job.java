package com.voicegov.ai.models;

public class Job {
    private int id;
    private String title;
    private String organization;
    private String location;
    private String lastDate;
    private String description;

    public Job() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getOrganization() { return organization; }
    public void setOrganization(String organization) { this.organization = organization; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getLastDate() { return lastDate; }
    public void setLastDate(String lastDate) { this.lastDate = lastDate; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}