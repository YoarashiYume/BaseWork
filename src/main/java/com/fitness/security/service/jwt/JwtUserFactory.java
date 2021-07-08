package com.fitness.security.service.jwt;
import com.fitness.security.model.Role;
import com.fitness.security.model.User;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public final class JwtUserFactory
{
    public static JwtUser create(User user)
    {
        return new JwtUser(user.getId(),user.getName(),user.getPassword(),toGrantedAuthority(user.getRoles()));
    }

    private static List<GrantedAuthority> toGrantedAuthority(List<Role> role)
    {
        return role.stream()
                .map(el -> new SimpleGrantedAuthority(el.getName()))
                .collect(Collectors.toList());
    }

}
