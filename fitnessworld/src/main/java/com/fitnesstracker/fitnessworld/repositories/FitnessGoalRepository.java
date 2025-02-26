package com.fitnesstracker.fitnessworld.repositories;

import com.fitnesstracker.fitnessworld.entities.FitnessGoal;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FitnessGoalRepository extends JpaRepository<FitnessGoal, Long> {
    List<FitnessGoal> findByUserId(Long userId);

    @Query("SELECT fg FROM FitnessGoal fg WHERE fg.goalType = :goalType")
    List<FitnessGoal> findByGoalType(@Param("goalType") String goalType);

    @Modifying
    @Transactional
    @Query("DELETE FROM FitnessGoal fg WHERE fg.id = :goalId")
    void deleteFitnessGoal(@Param("goalId") Long goalId);

    // @Modifying
    // @Transactional
    // @Query("UPDATE FitnessGoal fg SET fg.targetValue = :targetValue, fg.endDate = :endDate WHERE fg.id = :goalId")
    // int updateFitnessGoal(@Param("goalId") Long goalId, @Param("targetValue") Double targetValue, @Param("endDate") String endDate);
}