package com.Fitnes.model;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "food_nutrient")
public class FoodNutrient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;
    @Column(name = "fdc_id")
    private Long  fdc_id;
    @Column(name = "nutrient_id")
    private Long  nutrient_id;
    @Column(name = "amount")
    private Long amount;
    @Column(name = "data_points")
    private Long data_points;
    @Column(name = "derivation_id")
    private Long derivation_id;
    @Column(name = "min")
    private Double  min;
    @Column(name = "max")
    private Double  max;
    @Column(name = "median")
    private Double median;
    @Column(name = "footnote")
    private String footnote;
    @Column(name = "min_year_acqured")
    private Integer min_year_acqured;

    public FoodNutrient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFdc_id() {
        return fdc_id;
    }

    public void setFdc_id(Long fdc_id) {
        this.fdc_id = fdc_id;
    }

    public Long getNutrient_id() {
        return nutrient_id;
    }

    public void setNutrient_id(Long nutrient_id) {
        if (nutrient_id!=null)
        this.nutrient_id = nutrient_id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        if (amount!=null)
        this.amount = amount;
    }

    public Long getData_points() {
        return data_points;
    }

    public void setData_points(Long data_points) {
        if (data_points!=null)
        this.data_points = data_points;
    }

    public Long getDerivation_id() {
        return derivation_id;
    }

    public void setDerivation_id(Long derivation_id) {
        if (derivation_id!=null)
        this.derivation_id = derivation_id;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        if (min!=null)
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        if (max!=null)
        this.max = max;
    }

    public Double getMedian() {
        return median;
    }

    public void setMedian(Double median) {
        if (median!=null)
        this.median = median;
    }

    public String getFootnote() {
        return footnote;
    }

    public void setFootnote(String footnote) {
        if (footnote!=null)
        this.footnote = footnote;
    }

    public Integer getMin_year_acqured() {
        return min_year_acqured;
    }

    public void setMin_year_acqured(Integer min_year_acqured) {
        if (min_year_acqured != null)
        this.min_year_acqured = min_year_acqured;
    }
}
