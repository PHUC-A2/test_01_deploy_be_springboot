package com.example.laptopshop.service;

import com.example.laptopshop.model.User;
import com.example.laptopshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // thêm user
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        return this.userRepository.save(user);
    }

    // lấy danh sách user
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    // lấy user theo id
    public Optional<User> getUserById(Long userId) {
        return this.userRepository.findById(userId);
    }

    // cập nhật user
    public User updateUser(Long userId, User userUpdate) {
        return this.userRepository.findById(userId).map(user -> {
            user.setName(userUpdate.getName());
            user.setEmail(userUpdate.getEmail());
            return this.userRepository.save(user);
        }).orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    // xóa user
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NoSuchElementException("User not found");
        }
        this.userRepository.deleteById(userId);
    }

    // xóa hết user
    public void deleteAllUsers() {
        this.userRepository.deleteAll();
    }
}
