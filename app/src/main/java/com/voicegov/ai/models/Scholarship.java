package com.voicegov.ai.models;

public class Scholarship {
    private int id;
    private String title;
    private String description;
    private String deadline;
    private String amount;

    public Scholarship() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDeadline() { return deadline; }
    public void setDeadline(String deadline) { this.deadline = deadline; }
    public String getAmount() { return amount; }
    public void setAmount(String amount) { this.amount = amount; }
}