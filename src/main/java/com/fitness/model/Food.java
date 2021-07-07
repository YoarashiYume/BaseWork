package com.fitness.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "food")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Food {

    @Id
    @SequenceGenerator(name = "foodSEQ", sequenceName = "foodSEQ", allocationSize = 1)
    @GeneratedValue(generator = "foodSEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "fdc_id")
    Long id;

    @Column(name = "data_type")
    String dataType;

    @Column(name = "description")
    String description;

    @Column(name = "food_category_id")
    Long foodCategoryId;

    @Column(name = "publication_date")
    Date publicationDate;
}
