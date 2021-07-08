package com.fitness.security.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User
{
    @Id
    @SequenceGenerator(name = "userSEQ", sequenceName = "\"userSEQ\"", allocationSize = 1)
    @GeneratedValue(generator = "userSEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    Long id;

    @Column(name = "user_name")
    String name;

    @Column(name = "user_password")
    String password;

    @ManyToMany(fetch = FetchType.EAGER)
            @JoinTable (name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
                    inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    List<Role> roles;
}
