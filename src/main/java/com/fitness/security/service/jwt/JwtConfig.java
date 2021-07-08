package com.fitness.security.service.jwt;
import com.fitness.security.service.jwt.token.JwtTokenFilter;
import com.fitness.security.service.jwt.token.JwtTokenProvider;

import lombok.AllArgsConstructor;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor

public class JwtConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>
{
    private JwtTokenProvider provider;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception
    {
        JwtTokenFilter filter = new JwtTokenFilter(provider);
        httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}

