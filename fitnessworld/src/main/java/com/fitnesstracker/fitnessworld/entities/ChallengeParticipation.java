package com.fitnesstracker.fitnessworld.entities;

public class ChallengeParticipation {

    private Long id;
    private String status;
    private Long userId; // User identifier
    private Long challengeId; // Challenge identifier

    // Constructors
    public ChallengeParticipation() {
    }

    public ChallengeParticipation(Long id, String status, Long userId, Long challengeId) {
        this.id = id;
        this.status = status;
        this.userId = userId;
        this.challengeId = challengeId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }
}
