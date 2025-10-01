package com.example.laptopshop.controller;

import com.example.laptopshop.model.RestResponse;
import com.example.laptopshop.model.User;
import com.example.laptopshop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ✅ Trả về RestResponse JSON luôn
    @GetMapping("/hello")
    public ResponseEntity<RestResponse<String>> hello() {
        RestResponse<String> res = new RestResponse<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setStatus("success");
        res.setMessage("CALL API SUCCESS");
        res.setData("Hello");
        return ResponseEntity.ok(res);
    }


    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = this.userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    // lấy user bằng id
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return this.userService.getUserById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // cập nhật user
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userUpdate) {
        User updated = this.userService.updateUser(id, userUpdate);
        return ResponseEntity.ok().body(updated);
    }

    // xóa user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        this.userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // xóa user
    @DeleteMapping("/users")
    public ResponseEntity<User> deleteAllUsers() {
        this.userService.deleteAllUsers();
        return ResponseEntity.noContent().build();
    }
}
