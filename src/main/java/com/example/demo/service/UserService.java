package com.example.demo.service;

import com.example.demo.dal.model.User;
import com.example.demo.dal.repository.UserRepository;
import com.example.demo.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    /**
     * @param userId user id to delete
     * @return option of object which was deleted, empty means nothing done
     */
    public Optional<User> deleteUserById(Integer userId) {
        final Optional<User> userToDelete = getUserById(userId);
        if (userToDelete.isPresent()) {
            userRepository.deleteById(userId);
            return userToDelete;
        } else {
            return Optional.empty();
        }
    }

    public Optional<User> createUser(User user) {
        if (user.getId() != null && userRepository.existsById(user.getId())) {
            return Optional.empty();
        }
        return Optional.of(userRepository.save(user));
    }

    public Optional<User> updateUser(User user) {
        if (user.getId() == null || !userRepository.existsById(user.getId())) {
            return Optional.empty();
        }
        return Optional.of(userRepository.save(user));
    }
}
