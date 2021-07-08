package com.fitness.security.repository;
import com.fitness.security.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    default User findByUsername(String username)
    {
        List<User> list = this.findAll();
        Optional<User> user = list.stream().filter(el-> username.equals(el.getName())).findFirst();
        return user.orElse(null);
    }
}
