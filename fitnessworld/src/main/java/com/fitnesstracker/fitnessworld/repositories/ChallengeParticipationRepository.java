package com.fitnesstracker.fitnessworld.repositories;

import com.fitnesstracker.fitnessworld.entities.ChallengeParticipation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChallengeParticipationRepository extends JpaRepository<ChallengeParticipation, Long> {
    
    // Custom query methods (if needed)
    List<ChallengeParticipation> findByUserId(Long userId);

    List<ChallengeParticipation> findByChallengeId(Long challengeId);

}

