package com.fitness.controller;

import com.fitness.controller.request.AuthRequest;
import com.fitness.controller.response.Response;
import com.fitness.dto.AuthDto;
import com.fitness.model.User;
import com.fitness.repository.UserRepository;
import com.fitness.service.JwtTokenService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthenticationController {

    AuthenticationManager manager;

    JwtTokenService provider;

    UserRepository repository;

    @ResponseBody
    @PostMapping(value = "auth", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response login(@RequestBody AuthRequest request) {
        String username = request.getUsername();
        Optional<User> oUser = repository.findByName(username);
        if (oUser.isEmpty()) {
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));
        } catch (AuthenticationException e) {
            log.error("Invalid user password or name", e);
            throw new BadCredentialsException("Invalid data");
        }
        String token = provider.createToken(oUser.get());
        AuthDto auth = new AuthDto();
        auth.setUsername(username);
        auth.setToken(token);
        return Response.builder().code(200).data(auth).build();
    }
}
