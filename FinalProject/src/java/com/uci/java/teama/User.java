package com.uci.java.teama;

public class User {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private boolean category; 

    // Default no parameter constructor
    public User() {
        this.firstName = "";
        this.lastName = "";
        this.emailAddress = "";
        this.password = "";
        this.category = false;
    }

    // Three parameter constructor
    public User(String firstName, String lastName, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = "";
        this.category = false;
    }

    // all parameters
    public User(String firstName, String lastName, String emailAddress, String password, boolean category) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.category = category;
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isCategory() {
        return category;
    }

    public void setCategory(boolean category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "User [emailAddress=" + emailAddress
                + ", password=" + password
                + ", category=" + category
                + ", firstName=" + firstName
                + ", lastName=" + lastName
                + "]";
    }
}
