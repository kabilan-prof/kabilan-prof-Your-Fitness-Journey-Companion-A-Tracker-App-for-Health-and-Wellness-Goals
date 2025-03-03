// ChallengeService.java
package com.fitnesstracker.fitnessworld.services;

import com.fitnesstracker.fitnessworld.entities.Challenge;
import com.fitnesstracker.fitnessworld.repositories.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;


    public String participateInChallenge(Long challengeId, Long userId) {
        
        if (challengeId == null || userId == null) {
            throw new IllegalArgumentException("Challenge ID and User ID are required.");
        }
        return "Successfully participated in the challenge with ID: " + challengeId;
    }

    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    public Page<Challenge> getAllChallenges(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return challengeRepository.findAll(pageable);
    }

    public void addChallenge(Challenge challenge) {
        challengeRepository.save(challenge);
    }
    

    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll(); 
    }
    

    public List<Challenge> getChallengesByStartDate(LocalDate startDate) {
        if (startDate == null) {
            throw new IllegalArgumentException("Start date is required.");
        }
        return challengeRepository.findByStartDate(startDate);
    }

    public List<Challenge> getAllChallenges(Double reward, String sortBy, String order) {
        throw new UnsupportedOperationException("Unimplemented method 'getAllChallenges'");
    }

    public Challenge updateChallenge(Long id, Challenge updatedChallenge) {
        return challengeRepository.findById(id).map(existingChallenge -> {
            existingChallenge.setChallengeName(updatedChallenge.getChallengeName());
            existingChallenge.setStartDate(updatedChallenge.getStartDate());
            existingChallenge.setEndDate(updatedChallenge.getEndDate());
            existingChallenge.setReward(updatedChallenge.getReward());
            existingChallenge.setParticipants(updatedChallenge.getParticipants());
            return challengeRepository.save(existingChallenge);
        }).orElseThrow(() -> new IllegalArgumentException("Challenge with ID " + id + " not found."));
    }
    
    public void deleteChallenge(Long id) {
        if (!challengeRepository.existsById(id)) {
            throw new IllegalArgumentException("Challenge with ID " + id + " not found.");
        }
        challengeRepository.deleteById(id);
    }
 
    public Page<Challenge> getAllChallenge(int page, int size, String sortBy, String sortDir) {
    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
                ? Sort.by(sortBy).ascending() 
                : Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(page, size, sort);
    return challengeRepository.findAll(pageable);
}

public Page<Challenge> getChallengesByDateRange(LocalDate startDate, LocalDate endDate, int page, int size, String sortBy, String sortDir) {
    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
                ? Sort.by(sortBy).ascending() 
                : Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(page, size, sort);
    return challengeRepository.findChallengesByDateRange(startDate, endDate, pageable);
}


}
