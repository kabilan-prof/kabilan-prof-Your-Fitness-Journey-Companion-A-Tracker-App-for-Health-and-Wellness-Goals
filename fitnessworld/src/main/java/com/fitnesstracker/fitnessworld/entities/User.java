package com.fitnesstracker.fitnessworld.entities;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    private Long id;

    private String Username;

    private String email;

    private String password;

    private String phoneNumber;

    
    private String fitnessGoals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Username;
    }

    public void setName(String name) {
        this.Username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFitnessGoals() {
        return fitnessGoals;
    }

    public void setFitnessGoals(String fitnessGoals) {
        this.fitnessGoals = fitnessGoals;
    }

    public void setphoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    
    public String getphoneNumber()
    {
        return phoneNumber;
    }
}