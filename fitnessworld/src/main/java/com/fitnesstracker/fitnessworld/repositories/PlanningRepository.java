package com.fitnesstracker.fitnessworld.repositories;

import com.fitnesstracker.fitnessworld.entities.Planning;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PlanningRepository extends JpaRepository<Planning, Long> {

    // Pagination and Sorting
    Page<Planning> findAll(Pageable pageable);

    // Custom JPQL Query: Find Planning by Plan Name
    @Query("SELECT p FROM Planning p WHERE p.planName LIKE %:name%")
    List<Planning> findByPlanName(@Param("name") String name);
}
