package com.fitnesstracker.fitnessworld.controllers;

import com.fitnesstracker.fitnessworld.entities.ActivityLog;
import com.fitnesstracker.fitnessworld.services.ActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;


import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping
    public ResponseEntity<List<ActivityLog>> getAllActivities() {
        return ResponseEntity.ok(activityService.getAllActivities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityLog> getActivityById(@PathVariable Long id) {
        ActivityLog activity = activityService.getActivityById(id);
        return activity != null ? ResponseEntity.ok(activity) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ActivityLog> addActivity(@RequestBody ActivityLog activityLog) {
        return ResponseEntity.ok(activityService.addActivity(activityLog));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityLog> updateActivity(@PathVariable Long id, @RequestBody ActivityLog updatedActivity) {
        ActivityLog activity = activityService.updateActivity(id, updatedActivity);
        return activity != null ? ResponseEntity.ok(activity) : ResponseEntity.notFound().build();
    }
    

//     @GetMapping("/activities")
// public List<ActivityLog> getActivities() {
//     return activityService.getAllActivities(); // Return only the content
// }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
    return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllActivities() {
        activityService.deleteAllActivities();
    return ResponseEntity.noContent().build();
    }
    @GetMapping("/paginated")
    public ResponseEntity<List<ActivityLog>> getPaginatedActivities(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
    Page<ActivityLog> paginatedActivities = activityService.getPaginatedActivities(page, size); // Call service method
    List<ActivityLog> activities = paginatedActivities.getContent(); // Extract only the paginated content
    return ResponseEntity.ok(activities);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ActivityLog>> getAllActivitiesPaginated(
            @RequestParam(defaultValue = "10") int size // Page size
    ) {
        List<ActivityLog> allActivities = activityService.getAllActivitiesPaginated(size);
        return ResponseEntity.ok(allActivities);
    }
    

}
