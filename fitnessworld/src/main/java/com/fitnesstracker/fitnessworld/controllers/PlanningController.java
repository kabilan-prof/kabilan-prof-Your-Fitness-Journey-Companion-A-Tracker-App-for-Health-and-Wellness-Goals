package com.fitnesstracker.fitnessworld.controllers;

import com.fitnesstracker.fitnessworld.entities.Planning;
import com.fitnesstracker.fitnessworld.services.PlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plans")
public class PlanningController {

    @Autowired
    private PlanningService planningService;

    // Create or Update
    @PostMapping
    public ResponseEntity<Planning> createOrUpdate(@RequestBody Planning planning) {
        return ResponseEntity.ok(planningService.saveOrUpdate(planning));
    }

    // Get All with Pagination & Sorting
    @GetMapping
    public List<Planning> getAllPlans(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort;
        if (direction.equalsIgnoreCase("asc")) {
            sort = Sort.by(Sort.Order.asc(sortBy));
        } else {
            sort = Sort.by(Sort.Order.desc(sortBy));
        }
        return planningService.getAllPlans(PageRequest.of(page, size, sort));
    }

    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<Planning> getById(@PathVariable Long id) {
        Optional<Planning> planning = planningService.getById(id);
        return planning.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        planningService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Custom Query: Get Plans by Name
    @GetMapping("/search")
    public ResponseEntity<List<Planning>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(planningService.findByPlanName(name));
    }
}
