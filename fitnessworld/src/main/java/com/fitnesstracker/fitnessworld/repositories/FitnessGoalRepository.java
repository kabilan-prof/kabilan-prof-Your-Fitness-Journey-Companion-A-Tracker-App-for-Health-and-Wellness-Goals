package com.fitnesstracker.fitnessworld.repositories;

import com.fitnesstracker.fitnessworld.entities.FitnessGoal;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FitnessGoalRepository extends JpaRepository<FitnessGoal, Long> {
    Page<FitnessGoal> findByUserId(Long userId, Pageable pageable);  // Changed to Page<FitnessGoal>

    Page<FitnessGoal> findByUserIdAndGoalType(Long userId, String goalType, Pageable pageable);

    @Query("SELECT fg FROM FitnessGoal fg WHERE fg.goalType = :goalType")
    List<FitnessGoal> findByGoalType(@Param("goalType") String goalType);

    @Modifying
    @Transactional
    @Query("DELETE FROM FitnessGoal fg WHERE fg.id = :goalId")
    void deleteFitnessGoal(@Param("goalId") Long goalId);
}
