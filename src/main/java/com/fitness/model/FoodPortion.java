package com.fitness.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@Table(name = "food_portion")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodPortion {

    @Id
    @SequenceGenerator(name = "foodPortionSEQ", sequenceName = "foodPortionSEQ", allocationSize = 1)
    @GeneratedValue(generator = "foodPortionSEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    Long id;

    @Column(name = "fdc_id")
    Long fdcId;

    @Column(name = "seq_num")
    Long seqNum;

    @Column(name = "amount")
    Long amount;

    @Column(name = "measure_unit_id")
    Long measureUnitId;

    @Column(name = "portion_description")
    String portionDescription;

    @Column(name = "modifier")
    String modifier;

    @Column(name = "gram_weight")
    Double gramWeight;

    @Column(name = "data_points")
    Long dataPoints;

    @Column(name = "footnote")
    Long footnote;

    @Column(name = "min_year_acquired")
    Long minYearAcquired;
}
