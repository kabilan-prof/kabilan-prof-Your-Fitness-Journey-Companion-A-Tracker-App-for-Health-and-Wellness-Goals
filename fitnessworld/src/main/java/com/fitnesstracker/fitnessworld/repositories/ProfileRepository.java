package com.fitnesstracker.fitnessworld.repositories;

import com.fitnesstracker.fitnessworld.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("SELECT p FROM Profile p WHERE LOWER(p.email) = LOWER(:email)")
    Profile findByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("UPDATE Profile p SET p.email = :email WHERE p.id = :id")
    int updateEmailById(@Param("id") Long id, @Param("email") String email);

    @Modifying
    @Transactional
    @Query("DELETE FROM Profile p WHERE p.email = :email")
    int deleteByEmail(@Param("email") String email);
}
