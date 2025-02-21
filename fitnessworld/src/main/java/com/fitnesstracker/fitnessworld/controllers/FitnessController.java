// FitnessController.java
package com.fitnesstracker.fitnessworld.controllers;

import com.fitnesstracker.fitnessworld.entities.FitnessGoal;
import com.fitnesstracker.fitnessworld.services.FitnessGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class FitnessController {

    @Autowired
    private FitnessGoalService fitnessGoalService;

    @PostMapping
    public ResponseEntity<FitnessGoal> createGoal(@RequestBody FitnessGoal goal) {
        try {
            FitnessGoal createdGoal = fitnessGoalService.createGoal(goal);
            return new ResponseEntity<>(createdGoal, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    

    @GetMapping
public ResponseEntity<List<FitnessGoal>> getAllGoals(@RequestParam(defaultValue = "goalType") String sortBy,
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


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FitnessGoal>> getGoalsByUser(@PathVariable Long userId,
                                                            @RequestParam(required = false) String goalType,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        List<FitnessGoal> goals = fitnessGoalService.getGoalsByUser(userId, goalType, page, size);
        return new ResponseEntity<>(goals, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FitnessGoal> getGoalById(@PathVariable Long id) {
        FitnessGoal goal = (FitnessGoal) fitnessGoalService.getGoalsByUserId(id);
        if (goal != null) {
            return new ResponseEntity<>(goal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FitnessGoal> updateGoal(@PathVariable Long id, @RequestBody FitnessGoal goal) {
        try {
            FitnessGoal updatedGoal = fitnessGoalService.updateGoal(id, goal);
            return new ResponseEntity<>(updatedGoal, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long id) {
        try {
            fitnessGoalService.deleteGoal(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
