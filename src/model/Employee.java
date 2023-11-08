package model;

public class Employee {
    private int id;
    private String name;
    private String jobTitle;
    private String contactInfo;

    // Constructors, getters, and setters 
    public Employee(int id, String name, String contactInfo, String jobTitle) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.jobTitle = jobTitle;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setJobTitle(String jobTitle){
        this.jobTitle = jobTitle;
    }

    public String getJobTitle(){
        return jobTitle;
    }
}
