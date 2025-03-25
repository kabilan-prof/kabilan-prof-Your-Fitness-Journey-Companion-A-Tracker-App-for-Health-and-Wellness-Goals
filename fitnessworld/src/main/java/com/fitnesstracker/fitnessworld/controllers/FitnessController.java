// FitnessController.java
package com.fitnesstracker.fitnessworld.controllers;

import com.fitnesstracker.fitnessworld.entities.FitnessGoal;
import com.fitnesstracker.fitnessworld.services.FitnessGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/goals")
public class FitnessController {

    @Autowired
    private FitnessGoalService fitnessGoalService;

    // Create a goal for a specific user
    @PostMapping("/user/{userId}")
    public ResponseEntity<FitnessGoal> createGoal(@PathVariable Long userId, @RequestBody FitnessGoal goal) {
        try {
            FitnessGoal createdGoal = fitnessGoalService.createGoal(userId, goal);
            return new ResponseEntity<>(createdGoal, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Get all goals with pagination and sorting
    @GetMapping
    public ResponseEntity<List<FitnessGoal>> getAllGoals(
            @RequestParam(defaultValue = "goalType") String sortBy,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<FitnessGoal> goals = fitnessGoalService.getAllGoals(sortBy, order, page, size);
            return new ResponseEntity<>(goals, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get goals by user and goal type
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FitnessGoal>> getGoalsByUser(
            @PathVariable Long userId,
            @RequestParam(required = false) String goalType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<FitnessGoal> goals = fitnessGoalService.getGoalsByUser(userId, goalType, page, size);
            return new ResponseEntity<>(goals, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get goal by ID
    @GetMapping("/{id}")
    public ResponseEntity<FitnessGoal> getGoalById(@PathVariable Long id) {
        Optional<FitnessGoal> goal = fitnessGoalService.getGoalById(id);
        return goal.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    // Update goal
    @PutMapping("/{id}")
    public ResponseEntity<FitnessGoal> updateGoal(@PathVariable Long id, @RequestBody FitnessGoal goal) {
        try {
            FitnessGoal updatedGoal = fitnessGoalService.updateGoal(id, goal);
            if (updatedGoal != null) {
                return new ResponseEntity<>(updatedGoal, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete goal
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long id) {
        try {
            boolean deleted = fitnessGoalService.deleteGoal(id);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
