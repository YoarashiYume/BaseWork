package com.fitness.model;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "food_portion")
public class FoodPortion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;
    @Column(name = "fdc_id")
    private Long fdc_id;
    @Column(name = "seq_num")
    private Long seq_num;
    @Column(name = "amount")
    private Long amount;
    @Column(name = "measure_unit_id")
    private Long measure_unit_id;
    @Column(name = "portion_description")
    private String portion_description;
    @Column(name = "modifier")
    private String modifier;
    @Column(name = "gram_weight")
    private Double gram_weight;
    @Column(name = "data_points")
    private Long data_points;
    @Column(name = "footnote")
    private Long footnote;
    @Column(name = "min_year_acquired")
    private Long min_year_acquired;

    public FoodPortion() {
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

    public Long getSeq_num() {
        return seq_num;
    }

    public void setSeq_num(Long seq_num) {
        if (seq_num != null)
            this.seq_num = seq_num;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getMeasure_unit_id() {
        return measure_unit_id;
    }

    public void setMeasure_unit_id(Long measure_unit_id) {
        this.measure_unit_id = measure_unit_id;
    }

    public String getPortion_description() {
        return portion_description;
    }

    public void setPortion_description(String portion_description) {
        if (portion_description != null)
            this.portion_description = portion_description;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        if (modifier != null)
            this.modifier = modifier;
    }

    public Double getGram_weight() {
        return gram_weight;
    }

    public void setGram_weight(Double gram_weight) {
        this.gram_weight = gram_weight;
    }

    public Long getData_points() {
        return data_points;
    }

    public void setData_points(Long data_points) {
        this.data_points = data_points;
    }

    public Long getFootnote() {
        return footnote;
    }

    public void setFootnote(Long footnote) {
        if (footnote != null)
            this.footnote = footnote;
    }

    public Long getMin_year_acquired() {
        return min_year_acquired;
    }

    public void setMin_year_acquired(Long min_year_acquired) {
        if (min_year_acquired != null)
            this.min_year_acquired = min_year_acquired;
    }
}
