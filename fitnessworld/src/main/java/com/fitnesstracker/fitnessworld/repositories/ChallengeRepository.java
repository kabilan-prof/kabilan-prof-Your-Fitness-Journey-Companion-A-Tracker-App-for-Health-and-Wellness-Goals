// ChallengeRepository.java
package com.fitnesstracker.fitnessworld.repositories;

import com.fitnesstracker.fitnessworld.entities.Challenge;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    List<Challenge> findByStartDate(LocalDate startDate);
    @Query("SELECT c FROM Challenge c WHERE c.startDate >= :startDate AND c.endDate <= :endDate")
    Page<Challenge> findChallengesByDateRange(
        @Param("startDate") LocalDate startDate, 
        @Param("endDate") LocalDate endDate, 
        Pageable pageable
);

@Query("SELECT c FROM Challenge c WHERE c.challengeName LIKE %:challengeName%")
Page<Challenge> findByChallengeNameContaining(
        @Param("challengeName") String challengeName, 
        Pageable pageable
);


}
