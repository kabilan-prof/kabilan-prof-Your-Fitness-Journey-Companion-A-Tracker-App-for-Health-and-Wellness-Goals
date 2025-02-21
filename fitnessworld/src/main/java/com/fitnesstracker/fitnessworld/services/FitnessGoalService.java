package com.fitnesstracker.fitnessworld.services;

import com.fitnesstracker.fitnessworld.entities.FitnessGoal;
import com.fitnesstracker.fitnessworld.repositories.FitnessGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FitnessGoalService {

    @Autowired
    private FitnessGoalRepository fitnessGoalRepository;

    public FitnessGoal createGoal(FitnessGoal goal) {
        return fitnessGoalRepository.save(goal);
    }

    public List<FitnessGoal> getAllGoals(String sortBy, String order, int page, int size) {
        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<FitnessGoal> goalPage = fitnessGoalRepository.findAll(pageable);
        return goalPage.getContent();
    }

    public List<FitnessGoal> getGoalsByUserId(Long userId) {
        return fitnessGoalRepository.findByUserId(userId);
    }

    public FitnessGoal updateGoal(Long id, FitnessGoal updatedGoal) {
        return fitnessGoalRepository.findById(id).map(goal -> {
            goal.setGoalType(updatedGoal.getGoalType());
            goal.setDescription(updatedGoal.getDescription());
            return fitnessGoalRepository.save(goal);
        }).orElse(null);
    }

    public boolean deleteGoal(Long id) {
        if (fitnessGoalRepository.existsById(id)) {
            fitnessGoalRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<FitnessGoal> getGoalsByUser(Long userId, String goalType, int page, int size) {
        throw new UnsupportedOperationException("Unimplemented method 'getGoalsByUser'");
    }
    
}
