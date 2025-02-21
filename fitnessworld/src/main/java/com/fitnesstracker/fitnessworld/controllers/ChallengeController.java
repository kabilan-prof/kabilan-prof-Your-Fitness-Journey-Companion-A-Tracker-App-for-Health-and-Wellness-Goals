// ChallengeController.java
package com.fitnesstracker.fitnessworld.controllers;

import com.fitnesstracker.fitnessworld.entities.Challenge;
import com.fitnesstracker.fitnessworld.services.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @GetMapping
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        try {
            List<Challenge> challenges = challengeService.getAllChallenges();
            return new ResponseEntity<>(challenges, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
public ResponseEntity<String> addChallenge(@RequestBody Challenge challenge) {
    challengeService.addChallenge(challenge);
    return new ResponseEntity<>("Challenge added successfully!", HttpStatus.CREATED);
}


    @PostMapping("/participate/{challengeId}")
    public ResponseEntity<String> participateInChallenge(@PathVariable Long challengeId, @RequestParam Long userId) {
        try {
            challengeService.participateInChallenge(challengeId, userId);
            return new ResponseEntity<>("Successfully joined the challenge.", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/startDate/{startDate}")
    public ResponseEntity<List<Challenge>> getChallengesByStartDate(@PathVariable String startDate) {
        try {
            LocalDate date = LocalDate.parse(startDate);
            List<Challenge> challenges = challengeService.getChallengesByStartDate(date);
            return new ResponseEntity<>(challenges, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
public ResponseEntity<Challenge> updateChallenge(@PathVariable Long id, @RequestBody Challenge updatedChallenge) {
    try {
        Challenge challenge = challengeService.updateChallenge(id, updatedChallenge);
        return new ResponseEntity<>(challenge, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}

@DeleteMapping("/{id}")
public ResponseEntity<String> deleteChallenge(@PathVariable Long id) {
    try {
        challengeService.deleteChallenge(id);
        return new ResponseEntity<>("Challenge deleted successfully.", HttpStatus.OK);
    } catch (IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}

@GetMapping("/all")
public ResponseEntity<List<Challenge>> getAllChallenge(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDir
) {
    Page<Challenge> challengesPage = challengeService.getAllChallenges(page, size, sortBy, sortDir);
    List<Challenge> challenges = challengesPage.getContent(); // Get only the list of challenges
    return new ResponseEntity<>(challenges, HttpStatus.OK);
}

}
