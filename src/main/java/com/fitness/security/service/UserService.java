package com.fitness.security.service;

import com.fitness.security.model.User;
import com.fitness.security.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserService {

    UserRepository userRepository;

    public User findByUserName(String name) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(name));
        return user.orElse(null);
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }
}
