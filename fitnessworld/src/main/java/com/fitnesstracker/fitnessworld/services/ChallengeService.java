package com.fitnesstracker.fitnessworld.services;

import com.fitnesstracker.fitnessworld.entities.Challenge;
import com.fitnesstracker.fitnessworld.entities.ChallengeParticipation;
import com.fitnesstracker.fitnessworld.entities.User;
import com.fitnesstracker.fitnessworld.repositories.ChallengeRepository;
import com.fitnesstracker.fitnessworld.repositories.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private UserRepository userRepository;

    public Page<Challenge> getAllChallenges(int page, int size, String sortBy, String sortDir) {
        Sort sort = Sort.by(sortBy);
        sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return challengeRepository.findAll(pageable);
    }

    public Challenge addChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
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

    public List<Challenge> getChallengesByReward(Double reward, String sortBy, String order) {
        Sort sort = Sort.by(sortBy);
        sort = order.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
        return challengeRepository.findByRewardGreaterThanEqual(reward, sort);
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

    public boolean deleteChallenge(Long id) {
        if (!challengeRepository.existsById(id)) {
            throw new IllegalArgumentException("Challenge with ID " + id + " not found.");
        }
        challengeRepository.deleteById(id);
        return true;
    }

    public Page<Challenge> getChallengesByDateRange(LocalDate startDate, LocalDate endDate, int page, int size, String sortBy, String sortDir) {
        Sort sort = Sort.by(sortBy);
        sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return challengeRepository.findChallengesByDateRange(startDate, endDate, pageable);
    }

    // ✅ Corrected participateInChallenge method
    @Transactional
public String participateInChallenge(Long challengeId, Long userId) {
    if (challengeId == null || userId == null) {
        throw new IllegalArgumentException("Challenge ID and User ID are required.");
    }

    Optional<Challenge> challengeOpt = challengeRepository.findById(challengeId);
    Optional<User> userOpt = userRepository.findById(userId);

    if (challengeOpt.isPresent() && userOpt.isPresent()) {
        Challenge challenge = challengeOpt.get();
        User user = userOpt.get();

        // Ensure participants list is initialized
        if (challenge.getParticipants() == null) {
            challenge.setParticipants(new ArrayList<>());
        }

        // Create a new ChallengeParticipation entry
        ChallengeParticipation participation = new ChallengeParticipation();
        participation.setChallenge(challenge);
        participation.setUser(user);

        challenge.getParticipants().add(participation);

        challengeRepository.save(challenge);
        return "Successfully participated in challenge with ID: " + challengeId;
    } else {
        throw new IllegalArgumentException("Challenge or User not found.");
    }
}

    }
