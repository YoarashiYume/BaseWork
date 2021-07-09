package com.fitness.service;

import com.fitness.model.User;
import com.fitness.repository.UserRepository;
import com.fitness.utility.JwtUserFactory;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JwtUserDetailsService implements UserDetailsService {

    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> oUser = repository.findByName(name);
        if (oUser.isEmpty()) throw new UsernameNotFoundException("User Not Found");
        return JwtUserFactory.create(oUser.get());
    }
}
