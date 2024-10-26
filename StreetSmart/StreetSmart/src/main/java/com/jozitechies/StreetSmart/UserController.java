package com.jozitechies.StreetSmart;

import com.jozitechies.StreetSmart.entities.User;
import com.jozitechies.StreetSmart.repo.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepo repo;

    public UserController(UserRepo repo) {
        this.repo = repo;
    }

    // CREATE a new user
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        repo.save(user);
        return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
    }

    // READ all users
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    // READ a specific user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = repo.findById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // UPDATE a user by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> existingUser = repo.findById(id);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(userDetails.getName()); // Update fields as necessary
            user.setEmail(userDetails.getEmail());
            // Add other fields here
            repo.save(user);
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    // DELETE a user by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
}
