package com.fitness.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@Table(name = "food_nutrient")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodNutrient {

    @Id
    @SequenceGenerator(name = "foodNutrientSEQ",sequenceName = "foodNutrientSEQ", allocationSize = 1)
    @GeneratedValue(generator ="foodNutrientSEQ", strategy = GenerationType.SEQUENCE)

    @Column(name = "id")
    Long id;
    @Column(name = "fdc_id")
    Long fdcId;
    @Column(name = "nutrient_id")
    Long nutrientId;
    @Column(name = "amount")
    Long amount;
    @Column(name = "data_points")
    Long dataPoints;
    @Column(name = "derivation_id")
    Long derivationId;
    @Column(name = "min")
    Double min;
    @Column(name = "max")
    Double max;
    @Column(name = "median")
    Double median;
    @Column(name = "footnote")
    String footnote;
    @Column(name = "min_year_acqured")
    Integer minYearAcqured;

}
