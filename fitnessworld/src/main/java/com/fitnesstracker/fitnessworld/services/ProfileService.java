package com.fitnesstracker.fitnessworld.services;

import com.fitnesstracker.fitnessworld.entities.Profile;
import com.fitnesstracker.fitnessworld.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Optional<Profile> getProfileById(Long id) {
        return profileRepository.findById(id);
    }

    public Profile addProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile updateProfile(Long id, Profile updatedProfile) {
        return profileRepository.findById(id).map(profile -> {
            profile.setUserName(updatedProfile.getUserName());
            profile.setEmail(updatedProfile.getEmail());
            profile.setPhoneNumber(updatedProfile.getPhoneNumber());
            return profileRepository.save(profile);
        }).orElseThrow(() -> new IllegalArgumentException("Profile not found"));
    }

    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }

    public int deleteProfileByEmail(String email) {
        return profileRepository.deleteByEmail(email);
    }
    
    public int updateEmail(Long id, String email) {
        return profileRepository.updateEmailById(id, email);
    }
    
    public Profile getProfileByEmail(String email) {
        return profileRepository.findByEmail(email);
    }
    
    
}
