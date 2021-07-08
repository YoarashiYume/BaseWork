package com.fitness.security.controller;
import com.fitness.security.dto.AuthRequestDTO;
import com.fitness.security.model.User;
import com.fitness.security.service.UserService;
import com.fitness.security.service.jwt.token.JwtTokenProvider;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("auth")
@FieldDefaults(level = AccessLevel.PUBLIC)
public class AuthenticationController
{

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtTokenProvider provider;

    @Autowired
    private UserService service;

    @PostMapping
    ResponseEntity login(@RequestBody AuthRequestDTO request)
    {
        try
        {
            String username = request.getUsername();
            User user = service.findByUserName(username);
            manager.authenticate(new UsernamePasswordAuthenticationToken(username,request.getPassword()));
            if (user == null) throw new UsernameNotFoundException("User not found");
            String token = provider.createToken(user);
            Map<Object,Object> response = new HashMap<>();
            response.put("username",username);
            response.put("token",token);
            return ResponseEntity.ok(response);
        }catch (AuthenticationException e)
        {
            throw new BadCredentialsException("Invalid data");
        }
    }
}
