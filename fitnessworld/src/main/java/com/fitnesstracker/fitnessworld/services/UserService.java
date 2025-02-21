package com.fitnesstracker.fitnessworld.services;

import com.fitnesstracker.fitnessworld.entities.User;
import com.fitnesstracker.fitnessworld.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Page<User> getAllUsersPaginated(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            return userRepository.save(user);
        }).orElse(null);
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<User> getUsersWithPagination(int page, int size) {
        Page<User> pagedUsers = userRepository.findAll(PageRequest.of(page, size));
        return pagedUsers.getContent();
    }

   public User insertUser(User user) {
    return userRepository.save(user);
}

public void deleteUserByEmail(String email) {
    userRepository.deleteByEmail(email);
}

}