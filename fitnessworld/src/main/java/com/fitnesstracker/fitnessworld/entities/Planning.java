package com.fitnesstracker.fitnessworld.entities;

import jakarta.persistence.*;

@Entity
public class Planning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String planName;
    private String description;
    private String dietFoods;
    private String gainFoodInfo;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDietFoods() {
        return dietFoods;
    }

    public void setDietFoods(String dietFoods) {
        this.dietFoods = dietFoods;
    }

    public String getGainFoodInfo() {
        return gainFoodInfo;
    }

    public void setGainFoodInfo(String gainFoodInfo) {
        this.gainFoodInfo = gainFoodInfo;
    }
}
