package com.rest.service;

import com.rest.entity.Document;
import com.rest.entity.User;
import com.rest.exception.UserNotFoundException;
import com.rest.repository.IUserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserDAO userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public Set<Document> getUserDocuments(Long id) {
        User user = getUserById(id);
        return user.getDocuments();
    }

    public User updateUser(Long id, User newUserDetails) {
        User existingUser = getUserById(id);

        existingUser.setUsername(newUserDetails.getUsername());
        existingUser.setPassword(newUserDetails.getPassword());
        existingUser.setEmail(newUserDetails.getEmail());

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}
