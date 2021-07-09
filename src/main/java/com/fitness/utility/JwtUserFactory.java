package com.fitness.utility;

import com.fitness.model.Role;
import com.fitness.model.User;
import com.fitness.security.JwtUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class JwtUserFactory {

    public JwtUser create(User user) {
        return new JwtUser(user.getId(), user.getName(), user.getPassword(), toGrantedAuthority(user.getRoles()));
    }

    private List<GrantedAuthority> toGrantedAuthority(List<Role> role) {
        return role.stream()
                .map(el -> new SimpleGrantedAuthority(el.getName()))
                .collect(Collectors.toList());
    }
}
