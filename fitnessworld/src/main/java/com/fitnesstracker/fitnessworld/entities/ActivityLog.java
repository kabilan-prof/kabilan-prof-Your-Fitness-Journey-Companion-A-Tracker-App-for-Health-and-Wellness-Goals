package com.fitnesstracker.fitnessworld.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activity_logs")
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String activityName;
    private LocalDateTime timestamp;

    private int duration; 
    private int caloriesBurned;
    public void setId(Long id2) {
        id = id2;   
    }
    public Long getId() {
        return id;
    }
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
    public String getActivityName() {
        return activityName;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setDuration(int duration) {
            this.duration = duration;
    }
    public int getDuration() {
        return duration;
    }
    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }
    public int getCaloriesBurned() {
        return caloriesBurned;
    }
    
}
