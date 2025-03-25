package com.fitnesstracker.fitnessworld.repositories;

import com.fitnesstracker.fitnessworld.entities.Challenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    // Find challenges by start date
    List<Challenge> findByStartDate(LocalDate startDate);

    // Find challenges within a date range
    @Query("SELECT c FROM Challenge c WHERE c.startDate >= :startDate AND c.endDate <= :endDate")
    Page<Challenge> findChallengesByDateRange(
        @Param("startDate") LocalDate startDate, 
        @Param("endDate") LocalDate endDate, 
        Pageable pageable
    );

    // Find challenges by name (case-insensitive search)
    @Query("SELECT c FROM Challenge c WHERE LOWER(c.challengeName) LIKE LOWER(CONCAT('%', :challengeName, '%'))")
    Page<Challenge> findByChallengeNameContainingIgnoreCase(
        @Param("challengeName") String challengeName, 
        Pageable pageable
    );

    // Find challenges by reward greater than or equal to a certain value
    @Query("SELECT c FROM Challenge c WHERE c.reward >= :reward")
    List<Challenge> findByRewardGreaterThanEqual(
        @Param("reward") Double reward,
        Sort sort
    );
}
