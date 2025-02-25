package com.fitnesstracker.fitnessworld.services;

import com.fitnesstracker.fitnessworld.entities.ActivityLog;
import com.fitnesstracker.fitnessworld.repositories.ActivityLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private static ActivityLogRepository activityLogRepository;

    public List<ActivityLog> getActivityLogs(String activityName, LocalDateTime startTimestamp) {
        return activityLogRepository.findByActivityNameAndTimestampAfter(activityName, startTimestamp);
    }

    
    public List<ActivityLog> getAllActivities() {
        return activityLogRepository.findAll();
    }
    
    public ActivityLog getActivityById(Long id) {
        return activityLogRepository.findById(id).orElse(null);
    }
    
    public ActivityLog addActivity(ActivityLog activityLog) {
        return activityLogRepository.save(activityLog);
    }
    
    public ActivityLog updateActivity(Long id, ActivityLog updatedActivity) {
        if (activityLogRepository.existsById(id)) {
            updatedActivity.setId(id);
            return activityLogRepository.save(updatedActivity);
        }
        return null;
    }
    public ActivityService(ActivityLogRepository activityLogRepository) {
        if (activityLogRepository == null) {
            throw new IllegalStateException("ActivityLogRepository is null!");
        }
        ActivityService.activityLogRepository = activityLogRepository;
    }
    
    public void deleteActivity(Long id) {
        activityLogRepository.deleteById(id);
    }
    public void deleteAllActivities() {
        activityLogRepository.deleteAll();
    }
    public Page<ActivityLog> getPaginatedActivities(int page, int size) {
        Pageable pageable = PageRequest.of(page, size); // Create pagination configuration
        return activityLogRepository.findAll(pageable); // Fetch paginated data
    }
    public List<ActivityLog> getAllActivitiesPaginated(int size) {
        int page = 0;
        List<ActivityLog> allActivities = new ArrayList<>();

        while (true) {
            Pageable pageable = PageRequest.of(page, size);
            Page<ActivityLog> activityPage = activityLogRepository.findAll(pageable);

            // Add current page content to the list
            allActivities.addAll(activityPage.getContent());

            // Check if there are more pages
            if (!activityPage.hasNext()) {
                break;
            }
            page++;
        }

        return allActivities;
    }
    
}

