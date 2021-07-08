package com.fitness.security.service;
import com.fitness.security.model.User;
import com.fitness.security.service.jwt.JwtUserFactory;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user= userService.findByUserName(name);
        if (user==null)throw new UsernameNotFoundException("User Not Found");
        return JwtUserFactory.create(user);
    }
}
