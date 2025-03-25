// package com.fitnesstracker.fitnessworld.controllers;

// import com.fitnesstracker.fitnessworld.dto.ChallengeParticipationDTO;
// import com.fitnesstracker.fitnessworld.entities.ChallengeParticipation;
// import com.fitnesstracker.fitnessworld.services.ChallengeParticipationService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/participation")
// public class ChallengeParticipationController {

//     @Autowired
//     private ChallengeParticipationService participationService;

//     @PostMapping("/add")
//     public ChallengeParticipation addParticipation(@RequestBody ChallengeParticipationDTO dto) {
//         return participationService.addParticipation(dto);
//     }

//     @GetMapping("/all")
//     public List<ChallengeParticipation> getAllParticipations() {
//         return participationService.getAllParticipations();
//     }

//     @GetMapping("/{id}")
//     public ChallengeParticipation getParticipationById(@PathVariable Long id) {
//         return participationService.getParticipationById(id).orElseThrow(() -> new RuntimeException("Participation not found"));
//     }

//     @PutMapping("/update/{id}")
//     public ChallengeParticipation updateParticipation(@PathVariable Long id, @RequestBody ChallengeParticipationDTO dto) {
//         return participationService.updateParticipation(id, dto);
//     }

//     @DeleteMapping("/delete/{id}")
//     public String deleteParticipation(@PathVariable Long id) {
//         participationService.deleteParticipation(id);
//         return "Participation deleted successfully";
//     }
// }
