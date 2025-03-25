package com.fitnesstracker.fitnessworld.services;

import com.fitnesstracker.fitnessworld.entities.*;
import com.fitnesstracker.fitnessworld.repositories.UserRepository;
import com.fitnesstracker.fitnessworld.repositories.ActivityLogRepository;
import com.fitnesstracker.fitnessworld.repositories.ChallengeParticipationRepository;
import com.fitnesstracker.fitnessworld.repositories.FitnessGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityLogRepository activityLogRepository;

    @Autowired
    private ChallengeParticipationRepository challengeParticipationRepository;

    @Autowired
    private FitnessGoalRepository fitnessGoalRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Page<User> getAllUsersPaginated(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
            existingUser.setAddress(updatedUser.getAddress());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());

            if (updatedUser.getActivitiesLog() != null) {
                updatedUser.getActivitiesLog().forEach(log -> log.setUser(existingUser));
                activityLogRepository.saveAll(updatedUser.getActivitiesLog());
                existingUser.setActivitiesLog(updatedUser.getActivitiesLog());
            }

            if (updatedUser.getChallenges() != null) {
                updatedUser.getChallenges().forEach(challenge -> challenge.setUser(existingUser));
                challengeParticipationRepository.saveAll(updatedUser.getChallenges());
                existingUser.setChallenges(updatedUser.getChallenges());
            }

            if (updatedUser.getGoals() != null) {
                updatedUser.getGoals().forEach(goal -> goal.setUser(existingUser));
                fitnessGoalRepository.saveAll(updatedUser.getGoals());
                existingUser.setGoals(updatedUser.getGoals());
            }

            return userRepository.save(existingUser);
        }).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<User> getUsersWithPagination(int page, int size) {
        Page<User> pagedUsers = userRepository.findAll(PageRequest.of(page, size));
        return pagedUsers.getContent();
    }

    public User insertUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
    }
}
