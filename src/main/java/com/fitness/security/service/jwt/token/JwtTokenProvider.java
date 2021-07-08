package com.fitness.security.service.jwt.token;
import com.fitness.security.model.Role;
import com.fitness.security.model.User;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import io.jsonwebtoken.*;

@Component
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class JwtTokenProvider
{
    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private long timeValidity;

    @Autowired
    private UserDetailsService service;

    @Autowired
    private static final String header ="Authorization";

    @Autowired
    private static final String tokenPrefix ="Token_";

    @PostConstruct
    protected void init(){
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    @Bean
    BCryptPasswordEncoder encPass()
    {
        return new BCryptPasswordEncoder();
    }

    public String createToken(User user)
    {
        Claims claims = Jwts.claims().setSubject(user.getName());
        claims.put("roles",getRoleNames(user.getRoles()));
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims).setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + timeValidity))
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    Authentication getAuthentication(String token)
    {
        UserDetails userDetails = service.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

    String getUsername(String token)
    {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    String resolveToken(HttpServletRequest request)
    {
        String token = request.getHeader(header);
        return (token!=null && token.startsWith(tokenPrefix)) ? token.substring(tokenPrefix.length()) : null;
    }

    boolean validateToken(String token)
    {
        try
        {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        }
        catch (JwtException | IllegalArgumentException ignored)
        {
            throw new JwtAuthenticationException("Jwt token expire/invalid");
        }
    }

    private List<String> getRoleNames(List<Role> userRole)
    {
        return userRole.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }


}
