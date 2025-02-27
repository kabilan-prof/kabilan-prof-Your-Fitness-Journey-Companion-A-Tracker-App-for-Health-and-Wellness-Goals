package com.fitnesstracker.fitnessworld.services;
import com.fitnesstracker.fitnessworld.entities.Planning;
import com.fitnesstracker.fitnessworld.repositories.PlanningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlanningService {

    @Autowired
    private PlanningRepository planningRepository;

    // Create or Update
    public Planning saveOrUpdate(Planning planning) {
        return planningRepository.save(planning);
    }

    // Get All with Pagination & Sorting
    public List<Planning> getAllPlans(Pageable pageable) {
        Page<Planning> plansPage = planningRepository.findAll(pageable);
        return plansPage.getContent(); // Extract paginated content
    }

    // Get by ID
    public Optional<Planning> getById(Long id) {
        return planningRepository.findById(id);
    }

    // Delete
    public void delete(Long id) {
        planningRepository.deleteById(id);
    }

    // Custom Query: Find by Plan Name
    public List<Planning> findByPlanName(String name) {
        return planningRepository.findByPlanName(name);
    }
}
