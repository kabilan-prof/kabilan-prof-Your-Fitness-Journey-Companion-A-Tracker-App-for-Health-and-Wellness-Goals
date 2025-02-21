package com.fitnesstracker.fitnessworld.repositories;

import com.fitnesstracker.fitnessworld.entities.FitnessGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FitnessGoalRepository extends JpaRepository<FitnessGoal, Long> {
    List<FitnessGoal> findByUserId(Long userId);
}