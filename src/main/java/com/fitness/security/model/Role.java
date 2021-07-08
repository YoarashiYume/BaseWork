package com.fitness.security.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "role")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role
{
    @Id
    @SequenceGenerator(name = "roleSEQ", sequenceName = "\"roleSEQ\"", allocationSize = 1)
    @GeneratedValue(generator = "roleSEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    Long id;

    @Column(name = "role_name")
    String name;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    List<User> users;
}
