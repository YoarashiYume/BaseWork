package com.fitness.security.repository;
import com.fitness.security.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{
    default Role findByRoleName(String name)
    {
        List<Role> list = this.findAll();
        Optional<Role> role = list.stream().filter(el-> name.equals(el.getName())).findFirst();
        return role.orElse(null);
    }
}