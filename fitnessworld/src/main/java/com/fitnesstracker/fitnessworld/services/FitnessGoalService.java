package com.fitnesstracker.fitnessworld.services;

import com.fitnesstracker.fitnessworld.entities.FitnessGoal;
// import com.fitnesstracker.fitnessworld.entities.User;
import com.fitnesstracker.fitnessworld.repositories.FitnessGoalRepository;
import com.fitnesstracker.fitnessworld.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FitnessGoalService {

    @Autowired
    private FitnessGoalRepository fitnessGoalRepository;

    @Autowired
    private UserRepository userRepository;

    public FitnessGoal createGoal(Long userId, FitnessGoal goal) {
        return userRepository.findById(userId).map(user -> {
            goal.setUser(user);
            return fitnessGoalRepository.save(goal);
        }).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public List<FitnessGoal> getAllGoals(String sortBy, String order, int page, int size) {
        Sort sort = Sort.by(sortBy);
        sort = order.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return fitnessGoalRepository.findAll(pageable).getContent();
    }

    public Optional<FitnessGoal> getGoalById(Long id) {
        return fitnessGoalRepository.findById(id);
    }

    public FitnessGoal updateGoal(Long id, FitnessGoal updatedGoal) {
        return fitnessGoalRepository.findById(id).map(existingGoal -> {
            existingGoal.setGoalType(updatedGoal.getGoalType());
            existingGoal.setDescription(updatedGoal.getDescription());
            return fitnessGoalRepository.save(existingGoal);
        }).orElseThrow(() -> new IllegalArgumentException("Goal not found"));
    }

    public boolean deleteGoal(Long id) {
        if (fitnessGoalRepository.existsById(id)) {
            fitnessGoalRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<FitnessGoal> getGoalsByUser(Long userId, String goalType, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("goalType").ascending());
        Page<FitnessGoal> goalPage = (goalType != null && !goalType.isEmpty())
                ? fitnessGoalRepository.findByUserIdAndGoalType(userId, goalType, pageable)
                : fitnessGoalRepository.findByUserId(userId, pageable);
        return goalPage.getContent();
    }
}
