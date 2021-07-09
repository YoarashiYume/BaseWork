package com.fitness.service;

import com.fitness.exception.JwtAuthenticationException;
import com.fitness.model.Role;
import com.fitness.model.User;
import io.jsonwebtoken.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JwtTokenService {

    String secret;

    long timeValidity;

    UserDetailsService service;

    private static final String header = "Authorization";

    private static final String tokenPrefix = "Token_";

    public JwtTokenService(@Value("${jwt.token.secret}") String secret, @Value("${jwt.token.expired}") long timeValidity, UserDetailsService service) {
        this.secret = Base64.getEncoder().encodeToString(secret.getBytes());
        this.timeValidity = timeValidity;
        this.service = service;
    }

    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getName());
        claims.put("roles", getRoleNames(user.getRoles()));
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims).setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + timeValidity))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = service.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        return (token != null && token.startsWith(tokenPrefix)) ? token.substring(tokenPrefix.length()) : null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException ignored) {
            throw new JwtAuthenticationException("Jwt token expire/invalid");
        }
    }

    private List<String> getRoleNames(List<Role> userRole) {
        return userRole.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }
}
