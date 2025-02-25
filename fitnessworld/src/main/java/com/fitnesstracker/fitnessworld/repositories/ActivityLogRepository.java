package com.fitnesstracker.fitnessworld.repositories;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fitnesstracker.fitnessworld.entities.ActivityLog;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {

    @Query("SELECT a FROM ActivityLog a WHERE a.activityName = :activityName AND a.timestamp >= :startTimestamp")
    List<ActivityLog> findByActivityNameAndTimestampAfter(
            @Param("activityName") String activityName, 
            @Param("startTimestamp") LocalDateTime startTimestamp);
}
