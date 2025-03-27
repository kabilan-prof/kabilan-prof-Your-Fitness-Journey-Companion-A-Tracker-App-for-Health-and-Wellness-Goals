package com.fitnesstracker.fitnessworld.services;

import com.fitnesstracker.fitnessworld.entities.ActivityLog;
import com.fitnesstracker.fitnessworld.repositories.ActivityLogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityService {

    private final ActivityLogRepository activityLogRepository;

    public ActivityService(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

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

    public void deleteActivity(Long id) {
        activityLogRepository.deleteById(id);
    }

    public void deleteAllActivities() {
        activityLogRepository.deleteAll();
    }

    public Page<ActivityLog> getPaginatedActivities(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return activityLogRepository.findAll(pageable);
    }

    public List<ActivityLog> getAllActivitiesPaginated(int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllActivitiesPaginated'");
    }
}
