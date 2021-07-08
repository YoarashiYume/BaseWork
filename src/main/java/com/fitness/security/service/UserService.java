package com.fitness.security.service;

import com.fitness.security.repository.RoleRepository;
import com.fitness.security.model.User;
import com.fitness.security.repository.UserRepository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User findByUserName(String name)
    {
        Optional<User> user= Optional.ofNullable(userRepository.findByUsername(name));
        return user.orElse(null);
    }

    User findById(Long id)
    {
        Optional<User> user= userRepository.findById(id);
        return user.orElse(null);
    }

}
