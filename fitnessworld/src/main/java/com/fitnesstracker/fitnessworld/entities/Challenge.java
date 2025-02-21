package com.fitnesstracker.fitnessworld.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "challenges")
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String challengeName;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private String reward;

    @ElementCollection
    @CollectionTable(name = "challenge_participants", joinColumns = @JoinColumn(name = "challenge_id"))
    @Column(name = "participant")
    private List<String> participants;

    // Constructors
    public Challenge() {}

    public Challenge(String challengeName, LocalDate startDate, LocalDate endDate, String reward, List<String> participants) {
        this.challengeName = challengeName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reward = reward;
        this.participants = participants;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChallengeName() {
        return challengeName;
    }

    public void setChallengeName(String challengeName) {
        this.challengeName = challengeName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id=" + id +
                ", challengeName='" + challengeName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", reward='" + reward + '\'' +
                ", participants=" + participants +
                '}';
    }
}
