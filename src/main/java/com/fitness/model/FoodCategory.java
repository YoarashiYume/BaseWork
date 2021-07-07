package com.fitness.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@Table(name = "food_category")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodCategory {
    @Id
    @SequenceGenerator(name = "foodCatSEQ",sequenceName = "foodCatSEQ", allocationSize = 1)
    @GeneratedValue(generator ="foodCatSEQ", strategy = GenerationType.SEQUENCE)

    @Column(name = "id")
    Long id;
    @Column(name = "code")
    Long code;
    @Column(name = "description")
    String description;

}
