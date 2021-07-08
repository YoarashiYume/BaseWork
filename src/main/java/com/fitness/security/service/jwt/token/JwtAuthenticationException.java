package com.fitness.security.service.jwt.token;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.security.core.AuthenticationException;


@FieldDefaults(level = AccessLevel.PUBLIC)
public class JwtAuthenticationException extends AuthenticationException
{
    JwtAuthenticationException(String msg)
    {
        super(msg);
    }
}
