package com.fitnesstracker.fitnessworld.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitnesstracker.fitnessworld.entities.ActivityLog;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
}
