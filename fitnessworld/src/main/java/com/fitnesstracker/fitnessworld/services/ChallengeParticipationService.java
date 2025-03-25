package com.fitnesstracker.fitnessworld.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fitnesstracker.fitnessworld.entities.ChallengeParticipation;
import com.fitnesstracker.fitnessworld.repositories.ChallengeParticipationRepository;

@Service
public class ChallengeParticipationService {

    private final ChallengeParticipationRepository challengeParticipationRepository;

    @Autowired
    public ChallengeParticipationService(ChallengeParticipationRepository challengeParticipationRepository) {
        this.challengeParticipationRepository = challengeParticipationRepository;
    }

    public ChallengeParticipation saveParticipation(ChallengeParticipation participation) {
        return challengeParticipationRepository.save(participation);
    }

    public List<ChallengeParticipation> getUserParticipations(Long userId) {
        return challengeParticipationRepository.findByUserId(userId);
    }

    public List<ChallengeParticipation> getChallengeParticipants(Long challengeId) {
        return challengeParticipationRepository.findByChallengeId(challengeId);
    }

    public void deleteParticipation(Long id) {
        challengeParticipationRepository.deleteById(id);
    }
}

    