package com.dilum.resfulwebservicesaws.controller;

import com.dilum.resfulwebservicesaws.entity.User;
import com.dilum.resfulwebservicesaws.exception.ResourceNotFoundException;
import com.dilum.resfulwebservicesaws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers()
    {
        return this.userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserbyId(@PathVariable(value = "id") Long userId)
    {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found "+userId));
    }

    @PostMapping
    public User createUser(@RequestBody User user)
    {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable(value = "id") long userId)
    {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found "+userId));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        return userRepository.save(existingUser);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long userId)
    {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found "+userId));
        userRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }
}
