package controllers;

import entities.User;
import org.springframework.web.bind.annotation.*;
import repos.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @PutMapping("/{userId}")
    public User updateUserById(@PathVariable Long userId, @RequestBody User newUser) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setUsername(newUser.getUsername());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);

            return foundUser;
        } else {
            return null;
        }
    }

    @DeleteMapping("/{userId}")
    public void deteleUserById(@PathVariable Long userId) {
        userRepository.deleteById(userId);
    }


}
